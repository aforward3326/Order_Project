package tw.com.order.demo.controllor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.order.demo.entities.CustomUserDetails;
import tw.com.order.demo.entities.Member;
import tw.com.order.demo.entities.Order;
import tw.com.order.demo.entities.OrderItems;
import tw.com.order.demo.service.MemberService;
import tw.com.order.demo.service.OrderItemsService;
import tw.com.order.demo.service.OrderService;




@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrderItemsService orderItemsService;

	@GetMapping(value = { "/dashboard/order_orders/{id}" })
	public String mainOrder(@AuthenticationPrincipal CustomUserDetails loggedUser,@PathVariable(value="id") String id, Model model) {
		id = loggedUser.getMemberId();
		Member member= memberService.getMemberById(id);
		model.addAttribute("member",member);
		Order order = new Order();
		model.addAttribute("order", order);
		List<OrderItems> orderItems=orderItemsService.getAllOrderItems();
		model.addAttribute("orderItems", orderItems);

		return "dashboard/order";
	}

	@GetMapping(value = { "/all_orderlist" })
	public String allOrderList(Model model) {
		model.addAttribute("orderSearch", orderService.getAllOrder());
		return "admin/allorder_search";
	}

	@GetMapping(value = { "/new_orderlist" })
	public String newOrderList(Model model) {
		Order order = new Order();
		model.addAttribute("order", order);
		return "new_orderlist";

	}

	// 儲存訂單
	@PostMapping("/saveOrder")
	public String saveOrder(@ModelAttribute("order") Order order, Model model) {
		orderService.saveOrder(order);
		return "redirect:/dashboard";
	}

	// 更新訂單
	@GetMapping("/updateOrder/{id}")
	public String updateOrder(@PathVariable(value = "id") String orderId, Model model) {
		Order order = orderService.getOrderById(orderId);
		model.addAttribute("order", order);
		return "update_order";
	}

	// 刪除訂單
	@GetMapping("/deleteOrder/{orderid}")
	public String deleteOrder(@PathVariable(value = "orderid") String orderId) {
		this.orderService.deleteOrder(orderId);
		return "redirect:/dashboard/order_search";
	}

	//查詢單一會員訂單
	@GetMapping("/dashboard/order_search/{id}")
	public String getOrder(@AuthenticationPrincipal CustomUserDetails loggedUser,@PathVariable(value="id") String id, Model model) {
		id = loggedUser.getMemberId();
		Member member= memberService.getMemberById(id);
		model.addAttribute("orderSearch", orderService.getMemberOrder(id));
		Order order = new Order();
		model.addAttribute("order", order);
		
		return "dashboard/order_search";
	}
	
	
	
//	@GetMapping(value= {"/page/{pageNumber}"})
//	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {
//		
//		
//		Page<Order> pageOrder=orderService.listAll(currentPage);
//		int totalItems=pageProduct.getNumberOfElements();
//		int totalPages=pageProduct.getTotalPages();
//		List<Product> listProduct=pageProduct.getContent();
//		
//		model.addAttribute("currentPage",currentPage);
//		model.addAttribute("totalItems", totalItems);
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("listProduct",listProduct);
//		
//		return "index";
//		
//	}
}
