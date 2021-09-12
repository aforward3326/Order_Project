package tw.com.order.demo.controllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import tw.com.order.demo.entities.CustomUserDetails;
import tw.com.order.demo.entities.Member;
import tw.com.order.demo.entities.OrderItems;
import tw.com.order.demo.service.MemberService;
import tw.com.order.demo.service.OrderItemsService;

@Controller
public class OrderItemsController {
	
	@Autowired
	private OrderItemsService orderItemsService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value= {"/all_orderitemslist"})
	public String allItemsList(Model model) {
		model.addAttribute("orderItems", orderItemsService.getAllOrderItems());
		return "admin/orderitems_list";
	}
	
	@GetMapping(value="/addOrderItem/{id}")
	public String addOrderIten(@AuthenticationPrincipal CustomUserDetails loggedUser,@PathVariable(value="id") String id,Model model) {
		id = loggedUser.getMemberId();
		Member member= memberService.getMemberById(id);
		model.addAttribute("member",member);
		OrderItems orderItems=new OrderItems();
		model.addAttribute("orderItems", orderItems);
		return "admin/additems";
	}
	
	// 儲存品項
	@PostMapping("/saveOrderItem")
	public String saveOrderItem(@ModelAttribute("orderItems") OrderItems orderItems, Model model) {
		orderItemsService.saveOrderItems(orderItems);
		return "redirect:/dashboard";
	}
	
	// 更新品項
	@GetMapping("/updateOrderItems/{id}")
	public String updateOrderItems(@PathVariable(value = "id") Long id,Model model) {
		OrderItems orderItems=orderItemsService.getOrderItemsById(id);
		model.addAttribute("orderItems",orderItems);
		return "/dashboard";
		
	}
	
	//刪除品項
	@GetMapping("/deleteOrderItems/{id}")
	public String deleteOrderItems(@PathVariable(value = "id") Long id) {
		this.orderItemsService.deleteOrderItems(id);
		return "/dashboard";
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public String orderItems(ModelMap modelMap) {
		modelMap.put("orderItems", orderItemsService.findAll());
		return "dashboard/order";
	}
	
	@ResponseBody
	@RequestMapping(value= "loadPriceByItems/{id}", method= RequestMethod.GET)
	public String loadPriceByItems(@PathVariable("id") int id) {
		Gson gson= new Gson();
		return gson.toJson(orderItemsService.findPriceByItem(id));
	}

	
}
