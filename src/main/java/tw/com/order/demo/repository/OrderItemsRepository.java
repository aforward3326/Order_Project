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
import tw.com.order.demo.entities.Order;
import tw.com.order.demo.entities.OrderItems;

@Repository
public interface OrderItemsRepository extends  JpaRepository<OrderItems, Integer>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM OrderItems o WHERE o.id = :id ")
	void deleteById(Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE OrderItems o SET  o.item = :item, o.price = :price, o.startTime = :startTime, o.endTime = :endTime")
	Integer updateOrderItems(@Param("item") String item, @Param("price") int price, @Param("startTime") String startTime,
			@Param("endTime") String endTime);
	
	@Query("SELECT o FROM OrderItems o WHERE o.id =:id")
	Optional<OrderItems> findByOrderItemsId(Long id);
	
	@Query("SELECT o FROM OrderItems o WHERE o.id =:id")
	public List<OrderItems> findPriceById(@Param("id") int id);
	


	


	

}
	