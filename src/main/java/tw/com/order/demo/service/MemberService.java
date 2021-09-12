package tw.com.order.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tw.com.order.demo.entities.Member;
import tw.com.order.demo.entities.Role;

@Service
public interface MemberService {
	
	List<Member> getAllMember();
	
	String saveMember(Member member);
	
	Member getMemberById(String id) ;
	
	void deleteMember(Long id);
	
	String updateMember(Member member);
	
	String updateMemberPassword(Member member);
	
	void saveMemberWithDefaultRole(Member member);
	
	List<Role> getRoles();
	
	String updateMemberRoles(Member member);
	

	
	
	
	


	

}
