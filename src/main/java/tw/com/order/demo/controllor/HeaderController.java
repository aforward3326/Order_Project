package tw.com.order.demo.controllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tw.com.order.demo.entities.Order;
import tw.com.order.demo.service.NewsService;
import tw.com.order.demo.service.OrderService;

@Controller
public class HeaderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/header")
	public String getHeader(Model model) {
		
		return "header";
	}
	
	@GetMapping({"/login"})
	public String getLogin(Model model) {
		
		return "login";
	}
	
	@GetMapping({"/order"})
	public String getOrder(Model model) {
		
		return "order";
	}
	
	@GetMapping({"/order_search"})
	public String getOrderSearch(Model model) {
		
//		Order order=orderService.getOrderById(orderid);
//		model.addAttribute("order", order);
//		if(order==null) {
//		
		return "order_search";
//		}else {
//			return "/order_search/{id}";
//		}
	}
	
	@GetMapping({"/news"})
	public String getNews(Model model) {
		model.addAttribute("news", newsService.getAllNews());
		return "news";
	}

}
