package tw.com.order.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tw.com.order.demo.entities.CustomUserDetails;
import tw.com.order.demo.entities.Member;
import tw.com.order.demo.entities.Role;
import tw.com.order.demo.repository.MemberRepository;
import tw.com.order.demo.repository.RoleRepository;
import tw.com.order.demo.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;


	@Override
	public List<Member> getAllMember() {

		return memberRepository.findAll();
	}

	@Override
	public String saveMember(Member member) {
		//密碼加密
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
		member.setPassword(passwordEncoder.encode(member.getPassword())); 
		//儲存使用者
		memberRepository.saveAndFlush(member);
		return member.getMemberId();
	}

	@Override
	public Member getMemberById(String memberId) {
		Optional<Member> optional = memberRepository.findByMemberId(memberId);
		Member member = null;
		if (optional.isPresent()) {
			member = optional.get();
		} else {
			throw new RuntimeException("找不到ID" + memberId);
		}
		return member;
	}

	@Override
	public void deleteMember(Long id) {
		this.memberRepository.deleteById(id);

	}

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Member user = memberRepository.findByEmail(userEmail);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomUserDetails(user);
	}

	@Override
	public String updateMember(Member member) {
		//儲存使用者
		memberRepository.updateMember(member.getMemberId(),member.getName(),member.getSex(),member.getUnit(),member.getCellphone(),member.getOfficephone(),member.getAddress());
				
		return member.getMemberId();
	}

	@Override
	public String updateMemberPassword(Member member) {
		
		if(member.getPassword() == null) {
			return null;
		}else {
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
		member.setPassword(passwordEncoder.encode(member.getPassword())); 
		
		memberRepository.updateMemberPassword(member.getMemberId(), member.getPassword());
		return member.getMemberId();
		}
	}



	@Override
	public void saveMemberWithDefaultRole(Member member) {
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
		member.setPassword(passwordEncoder.encode(member.getPassword())); 
		Role rolemember= roleRepository.findByName("Member");
		member.addRole(rolemember);
		memberRepository.save(member);
		//儲存使用者
		memberRepository.saveAndFlush(member);
		
	}
	

	@Override
	public List<Role> getRoles() {
		
		return roleRepository.findAll();
	}

	@Override
	public String updateMemberRoles(Member member) {
		Role rolemember= roleRepository.findById(member.getRoles());
		member.addRole(rolemember);
		memberRepository.save(member);
		
		memberRepository.updateMemberRoles(member.getMemberId(),member.getRoles());
		
		return member.getMemberId();
		
	}

	

	

}
