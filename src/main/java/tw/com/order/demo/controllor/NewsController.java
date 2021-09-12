package tw.com.order.demo.controllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import tw.com.order.demo.entities.CustomUserDetails;
import tw.com.order.demo.entities.Member;
import tw.com.order.demo.entities.News;
import tw.com.order.demo.service.MemberService;
import tw.com.order.demo.service.NewsService;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private MemberService memberService;

	@GetMapping(value = { "/all_newslist" })
	public String allNewsList(Model model) {
		model.addAttribute("news", newsService.getAllNews());
		return "admin/news_list";
	}

	@GetMapping(value = "/addnews/{id}")
	public String addNews(@AuthenticationPrincipal CustomUserDetails loggedUser, @PathVariable(value = "id") String id,
			Model model) {
		id = loggedUser.getMemberId();
		Member member = memberService.getMemberById(id);
		model.addAttribute("member", member);
		News news = new News();
		model.addAttribute("news", news);
		return "admin/addnews";
	}

	// 儲存品項
	@PostMapping("/savenews")
	public String saveNews(@ModelAttribute("news") News news, Model model) {
		newsService.saveNews(news);
		return "dashboard/dashboard";
	}
	
	// 更新品項
	@GetMapping("/updateNews/{id}")
	public String updateNews(@PathVariable(value = "id") int id, Model model) {
		News news = newsService.getNewsById(id);
		model.addAttribute("news", news);
		return "admin/newsedit";

	}

	// 刪除品項
	@GetMapping("/deleteNews/{id}")
	public String deleteNews(@PathVariable(value = "id") int id) {
		this.newsService.deleteNews(id);
		return "admin/news_list";
	}

	// 公告內容
	@GetMapping("/newsDetails/{id}")
	public String newsDetails(@PathVariable(value = "id") int id, Model model) {
		News news = newsService.getNewsById(id);
		model.addAttribute("news", news);
		return "newsdetails";

	}

}
