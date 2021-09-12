package tw.com.order.demo.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tw.com.order.demo.entities.Order;
import tw.com.order.demo.entities.OrderItems;
import tw.com.order.demo.repository.OrderItemsRepository;
import tw.com.order.demo.repository.OrderRepository;
import tw.com.order.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemsRepository orderItemsRepository;

	@Override
	public List<Order> getAllOrder() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	// 儲存訂單
	@Override
	public void saveOrder(Order order) {
		this.orderRepository.save(order);

	}

	// 刪除訂單
	@Override
	public void deleteOrder(String orderId) {
		this.orderRepository.deleteByOrderId(orderId);

	}

	// 查詢訂單編號
	@Override
	public Order getOrderById(String orderId) {
		Optional<Order> order = orderRepository.findByOrderId(orderId);
		if (order.isPresent()) {
			return order.get();
		}
		throw new RuntimeException("id number is error:" + orderId);
	}

	// 自動編碼
	@Override
	public String orderId() {
		Random random = new Random();
		// 随机数的量 自由定制，这是9位随机数
		Integer r = random.nextInt(90000000) + 10000000;

		// 返回 17位時間
		DateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		String timeStr = sdf.format(new Date());

		// 17位时间+9位随机数
		return timeStr + r;
	}

	@Override
	public List<Order> getMemberOrder(String memberId) {
		// TODO Auto-generated method stub
		return orderRepository.findAllByMemberId(memberId);
	}

	@Override
	public Page<Order> listAll(int pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber-1, 20);
		return orderRepository.findAll(pageable);
	}
	
	

}
