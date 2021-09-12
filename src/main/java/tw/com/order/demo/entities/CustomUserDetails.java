package tw.com.order.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private Member member;
	
	public CustomUserDetails(Member member) {
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
//		//Role
		Set<Role>  roles=member.getRoles();
		List<SimpleGrantedAuthority> authority=new ArrayList<>();
		
		for(Role role:roles) {
			authority.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		
		return authority;
	}
	
	

	@Override
	public String getPassword() {
		
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return member.getUserEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getFullName() {
		return this.member.getName();
	}
	
	public String getMemberId() {
		return this.member.getMemberId();
	}
	
	public void setName(String name) {
		this.member.setName(name);	
	}
	
	public void setUnit(String unit) {
		this.member.setUnit(unit);	
	}
	
	public void setAddress(String address) {
		this.member.setAddress(address);	
	}
	
	public void setCellphone(String cellphone) {
		this.member.setAddress(cellphone);		
	}
	
	public void setOfficephone(String officephone) {
		this.member.setAddress(officephone);	
	}

}
