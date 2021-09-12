package tw.com.order.demo.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id ;
	@Column(name= "roleName" ,length = 25,unique = true,nullable = false)
	private String roleName;
	
	
	

	public Role() {
		
	}

	public Role(String roleName) {
		this.roleName=roleName;
	}
	
	public Role(Integer id) {
		this.id=id;
	}
	
	public Role(Integer id,String roleName) {
		this.id=id;
		this.roleName=roleName;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getRoleName() {
		return roleName;
	}




	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return this.roleName;
		
	}
	
	



















	

}
