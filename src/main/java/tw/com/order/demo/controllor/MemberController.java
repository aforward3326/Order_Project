package tw.com.order.demo.controllor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import tw.com.order.demo.entities.Member;
import tw.com.order.demo.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value= {"/member_signup"})
	public String memberSignUp(Model model) {
		Member member=new Member();
		model.addAttribute("member",member);
		Map<String, Object> sexes = new HashMap<String, Object>();
		sexes.put("其他", 2);
	    sexes.put("女", 1);
	    sexes.put("男", 0);
	    model.addAttribute("sexes", sexes);
		return "signup";
	}
	
	@GetMapping(value= {"/all_memberlist"})
	public String allMemberList(Model model) {
		model.addAttribute("listMember", memberService.getAllMember());
		return "admin/allmember";
	}
	
	@GetMapping(value={"/new_memberlist"})
	public String newMemberList(Model model) {
		Member member=new Member();
		model.addAttribute("member",member);
		return "new_memberlist";
		
	}
	
	@PostMapping("/saveMember")
	public String saveMember(@ModelAttribute("member") Member member) {
		memberService.saveMemberWithDefaultRole(member);
		return "redirect:/login";
	}
	
	@GetMapping("/updateMember/{id}")
	public String updateMember(@PathVariable(value = "id") String memberId,Model model) {
		Member member=memberService.getMemberById(memberId);
		model.addAttribute("Member",member);
		return "update_Member";
	}
	
	@GetMapping("/deleteMember/{id}")
	public String deleteMember(@PathVariable(value="id") Long id) {
		this.memberService.deleteMember(id);
		return "redirect:/all_memberlist";
	}

}
