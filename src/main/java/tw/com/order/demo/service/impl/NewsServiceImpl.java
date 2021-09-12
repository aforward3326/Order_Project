package tw.com.order.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tw.com.order.demo.entities.News;
import tw.com.order.demo.entities.OrderItems;
import tw.com.order.demo.repository.NewsRepository;
import tw.com.order.demo.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;

	// 儲存公告
	@Override
	public void saveNews(News news) {
		this.newsRepository.save(news);

	}

	// 刪除公告
	@Override
	public void deleteNews(int id) {
		this.newsRepository.deleteById(id);

	}

	// 修改公告內容
	@Override
	public Integer updateNews(News news) {
		newsRepository.updateNews(news.getTitle(), news.getContext());
		return news.getId();
	}

	// 列出所有公告
	@Override
	public List<News> getAllNews() {

		return newsRepository.findAll();
	}

	@Override
	public News getNewsById(int id) {
		Optional<News> optional = newsRepository.findById(id);
		News news = null;
		if (optional.isPresent()) {
			news = optional.get();
		} else {
			throw new RuntimeException("找不到ID" + id);
		}
		return news;
	}

	@Override
	public Page<News> listAll(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 5); // 調用項目

		return newsRepository.findAll(pageable);
		
	}

}
