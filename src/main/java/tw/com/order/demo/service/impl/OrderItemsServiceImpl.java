package tw.com.order.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.order.demo.entities.OrderItems;
import tw.com.order.demo.repository.OrderItemsRepository;
import tw.com.order.demo.service.OrderItemsService;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

	@Autowired
	private OrderItemsRepository orderItemsRepository;

	// 儲存品項
	@Override
	public void saveOrderItems(OrderItems orderItems) {
		this.orderItemsRepository.save(orderItems);

	}

	// 刪除品項
	@Override
	public void deleteOrderItems(Long id) {
		this.orderItemsRepository.deleteById(id);

	}

	// 修改品項內容
	@Override
	public Long updateOrderItems(OrderItems orderItems) {
		orderItemsRepository.updateOrderItems(orderItems.getItem(), orderItems.getPrice(), orderItems.getStartTime(),
				orderItems.getEndTime());
		return orderItems.getId();
	}

	@Override
	public OrderItems getOrderItemsById(Long id) {
		Optional<OrderItems> optional=orderItemsRepository.findByOrderItemsId(id);
		OrderItems orderItems=null;
		if(optional.isPresent()) {
			orderItems=optional.get();
		}else {
			throw new RuntimeException("找不到ID" + id);
		}
		return orderItems;
	}

	@Override
	public List<OrderItems> getAllOrderItems() {
		
		return orderItemsRepository.findAll();
	}

	@Override
	public OrderItems find(int id) {
		
		return orderItemsRepository.findById(id).get();
	}

	@Override
	public List<OrderItems> findPriceByItem(int id) {
		
		return orderItemsRepository.findPriceById(id);
	}

	@Override
	public Iterable<OrderItems> findAll() {
		
		return orderItemsRepository.findAll();
	}

}
