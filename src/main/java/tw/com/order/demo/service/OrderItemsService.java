package tw.com.order.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.com.order.demo.entities.OrderItems;

@Service
public interface OrderItemsService {

	// 儲存品項
	void saveOrderItems(OrderItems orderItems);

	// 刪除品項
	void deleteOrderItems(Long id);
	
	// 修改品項內容
	Long updateOrderItems(OrderItems orderItems);
	
	OrderItems getOrderItemsById(Long id);
	
	List<OrderItems> getAllOrderItems();
	
	public OrderItems find(int id);
	
	public List<OrderItems> findPriceByItem(int id);
	
	public Iterable<OrderItems> findAll();

}
