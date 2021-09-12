package tw.com.order.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Parameter;
import tw.com.order.demo.entities.seq.OrderSequenceGenerator;

@Entity
@Table(name = "orderList")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
	@GenericGenerator(name = "ORDER_SEQ", strategy = OrderSequenceGenerator.STRATEGY, parameters = {
//			@Parameter(name = OrderSequenceGenerator.PARAM_PREFIX, value = "OD"),
			@Parameter(name = OrderSequenceGenerator.PARAM_DATETIME_FORMAT, value = OrderSequenceGenerator.DATETIME_FORMAT_YYYYMMDD),
			@Parameter(name = OrderSequenceGenerator.PARAM_SEQ_LENGTH, value = "5"),
			@Parameter(name = OrderSequenceGenerator.PARAM_PADDING_CHAR, value = "0") })
	@Column(name = "orderId")
	private String orderId;
	@Column(name = "memberId")
	private String memberId;
	@Column(name = "memberName")
	private String name;
	@Column(name = "orderItem")
	private String orderItem;	
	@Column(name = "orderAmount")
	private int orderAmount;
	@Column(name = "orderTotal")
	private int orderTotal;
	@Column(name = "orderNote")
	private String orderNote;
	@Column(name = "orderDate")
	private String orderDate;
	@Column(name = "orderTime")
	private String orderTime;
	@CreationTimestamp
	@Column(name = "createAt", nullable = false, updatable = false)
	private Date createAt;
	@UpdateTimestamp
	@Column(name = "updateAt")
	private Date updateAt;

	public Order() {

	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderNumber(String orderId) {
		this.orderId = orderId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", memberId=" + memberId + ", orderItem=" + orderItem
				+ ", orderAmount=" + orderAmount + ", orderTotal=" + orderTotal + ", orderNote=" + orderNote
				+ ", orderDate=" + orderDate + ", orderTime=" + orderTime + ", createAt=" + createAt + ", updateAt="
				+ updateAt + "]";
	}

	




}
