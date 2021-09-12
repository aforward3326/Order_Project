package tw.com.order.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import tw.com.order.demo.entities.News;



@Service
public interface NewsService {

	// 儲存公告
	void saveNews(News news);

	// 刪除公告
	void deleteNews(int id);
	
	// 修改公告內容
	Integer updateNews(News news);
	
	//列出所有公告
	List<News> getAllNews();
	
	News getNewsById(int id);
	
	public Page<News> listAll(int pageNumber);
	
	

}
