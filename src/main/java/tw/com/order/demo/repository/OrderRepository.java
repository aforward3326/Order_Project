package tw.com.order.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.order.demo.entities.Order;
import tw.com.order.demo.entities.OrderItems;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	Optional<Order> findByOrderId(String orderId) ;

	@Transactional
	@Modifying
	@Query("DELETE FROM Order o WHERE o.orderId = :orderId ")
	void deleteByOrderId(String orderId);

	@Query("SELECT o FROM Order o WHERE o.memberId =:memberId")
	List<Order> findAllByMemberId(String memberId);
	
	@Query("SELECT o FROM Order o WHERE o.memberId =:memberId")
	Page<Order> findallByMemberId(String memberId, Pageable pageable);

	
	


	


	

}
