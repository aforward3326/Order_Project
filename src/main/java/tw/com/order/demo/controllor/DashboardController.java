package tw.com.order.demo.controllor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tw.com.order.demo.entities.CustomUserDetails;
import tw.com.order.demo.entities.Member;
import tw.com.order.demo.entities.Role;
import tw.com.order.demo.service.MemberService;
import tw.com.order.demo.service.OrderService;


@Controller
public class DashboardController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping({"/dashboard"})
	public String getDashBoard(@AuthenticationPrincipal CustomUserDetails loggedUser,Model model) {
	Member member=new Member();
	model.addAttribute("member",member);
		return "dashboard/dashboard";
	}
	
	@GetMapping({"/updatemember/{id}"})
	public String updateMember(@AuthenticationPrincipal CustomUserDetails loggedUser,@PathVariable(value="id") String id ,Model model) {		
		id = loggedUser.getMemberId();
		Member member= memberService.getMemberById(id);
		model.addAttribute("member",member);
		Map<String, Object> sexes = new HashMap<String, Object>();
		sexes.put("其他", 2);
	    sexes.put("女", 1);
	    sexes.put("男", 0);
	    model.addAttribute("sexes", sexes);
	    System.out.println(id);
		return "dashboard/update_member";
	}
	
	@GetMapping({"/updatememberpassword/{id}"})
	public String updateMemberPassword(@AuthenticationPrincipal CustomUserDetails loggedUser,@PathVariable(value="id") String id ,Model model) {
		id = loggedUser.getMemberId();
		Member member= memberService.getMemberById(id);
		model.addAttribute("member",member);
		System.out.println(id);
		return "dashboard/update_member_password";
	}
	
	@PostMapping("/updateMember")
	public String saveUpdateMember( Member member, RedirectAttributes redirectAttributes,@AuthenticationPrincipal CustomUserDetails loggedUser)throws IOException {		
		memberService.updateMember(member);
		loggedUser.setName(member.getName());
		loggedUser.setUnit(member.getUnit());
		loggedUser.setAddress(member.getAddress());
		loggedUser.setCellphone(member.getCellphone());
		loggedUser.setOfficephone(member.getOfficephone());
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/dashboard";
		
	}
	
	@PostMapping("/updateMemberPassword")
	public String saveUpdateMemberPassword( Member member, RedirectAttributes redirectAttributes,@AuthenticationPrincipal CustomUserDetails loggedUser)throws IOException {
		
		
		
		memberService.updateMemberPassword(member);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		
		return "redirect:/login";
				
	}
	
	@GetMapping({"/updatememberrolesbyadmin/{id}"})
	public String updateMemberRolesByAdmin(@PathVariable(value="id") String id ,Model model) {
		Member member= memberService.getMemberById(id);
		model.addAttribute("member",member);
		System.out.println(id);
		return "admin/update_member_password";
		
	}
	
	@PostMapping("/updateMemberPasswordbyadmin")
	public String saveUpdateMemberPasswordByAdmin( Member member)throws IOException {
		
		if(member.getPassword()==null) {
		return "admin/allmember";
		}
		memberService.updateMemberPassword(member);
		return "redirect:/login";
				
	}
	
	@GetMapping({"/updatememberroles/{id}"})
	public String updateMemberRoles(@AuthenticationPrincipal CustomUserDetails loggedUser,@PathVariable(value="id") String id ,Model model) {
		id = loggedUser.getMemberId();
		Member member= memberService.getMemberById(id);
		List<Role> listRoles=memberService.getRoles();
		model.addAttribute("member",member);
		model.addAttribute("listRoles",listRoles);
		System.out.println(id);
		return "admin/update_member_roles";
	}
	
	@PostMapping("/updateMemberRoles")
	public String saveUpdateMemberRoles( Member member, RedirectAttributes redirectAttributes,@AuthenticationPrincipal CustomUserDetails loggedUser)throws IOException {
		memberService.updateMemberRoles(member);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/all_memberlist";
	}
	


}
