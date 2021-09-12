package tw.com.order.demo.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;

import tw.com.order.demo.entities.seq.MemberSequenceGenerator;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
	@GenericGenerator(name = "MEMBER_SEQ", strategy = MemberSequenceGenerator.STRATEGY, parameters = {
//			@Parameter(name = MemberSequenceGenerator.PARAM_PREFIX, value = "M"),
			@Parameter(name = MemberSequenceGenerator.PARAM_DATETIME_FORMAT, value = MemberSequenceGenerator.DATETIME_FORMAT_YYYYMMDD),
			@Parameter(name = MemberSequenceGenerator.PARAM_SEQ_LENGTH, value = "5"),
			@Parameter(name = MemberSequenceGenerator.PARAM_PADDING_CHAR, value = "0") })
	@Column(name = "memberId")
	private String memberId;
	@Column(name = "name")
	private String name;
	@Column(name = "sex")
	private String sex;
	@Column(name = "userPassword", length = 256, nullable = false)
	private String password;
	@Column(name = "unit")
	private String unit;
	@Column(name = "address")
	private String address;
	@Column(name = "userEmail", length = 256, nullable = false)
	private String userEmail;
	@Column(name = "cellphone")
	private String cellphone;
	@Column(name = "officephone")
	private String officephone;
	@CreationTimestamp
	@Column(name = "createAt", nullable = false, updatable = false)
	private Date createAt;
	@UpdateTimestamp
	@Column(name = "updateAt")
	private Date updateAt;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "member_role", joinColumns = @JoinColumn(name = "memberId"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public Member() {

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getOfficephone() {
		return officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

}
