package tw.com.order.demo.controllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tw.com.order.demo.entities.News;
import tw.com.order.demo.service.NewsService;


@Controller
public class IndexController {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping({"/","","index","/logout"})
	public String getIndex(Model model) {
		model.addAttribute("news", newsService.getAllNews());
		
		return "index";
	}
	
	@GetMapping(value= {"/page/{pageNumber}"})
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {
		
		
		Page<News> pageNews=newsService.listAll(currentPage);
		int totalItems=pageNews.getNumberOfElements();
		int totalPages=pageNews.getTotalPages();
		List<News> listProduct=pageNews.getContent();
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("listProduct",listProduct);
		
		return "index";
	}
	

}
