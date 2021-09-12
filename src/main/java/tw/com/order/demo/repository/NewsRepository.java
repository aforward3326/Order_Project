package tw.com.order.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.order.demo.entities.Member;
import tw.com.order.demo.entities.News;
import tw.com.order.demo.entities.Order;
import tw.com.order.demo.entities.OrderItems;

@Repository
public interface NewsRepository extends  JpaRepository<News, Integer>{
	
	
	@Transactional
	@Modifying
	@Query("UPDATE News n SET  n.title = :title, n.context = :context")
	Integer updateNews(@Param("title") String title, @Param("context") String context);

	

	
	

	


	


	

}
	