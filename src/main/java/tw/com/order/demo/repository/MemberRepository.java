package tw.com.order.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.order.demo.entities.Member;
import tw.com.order.demo.entities.Role;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	@Query("SELECT m FROM Member m WHERE m.userEmail =:userEmail")

	Member findByEmail(String userEmail);

	@Query("SELECT m FROM Member m WHERE m.memberId =:memberId")
	Optional<Member> findByMemberId(String memberId);

	@Query("SELECT m FROM Member m")
	List<Member> findAllMember();

	@Transactional
	@Modifying
	@Query("UPDATE Member m SET  m.name = :name, m.sex = :sex, m.unit = :unit, m.cellphone = :cellphone, m.officephone = :officephone, m.address = :address WHERE m.memberId = :memberId")
	Integer updateMember(@Param("memberId") String memberId, @Param("name") String name, @Param("sex") String sex,
			@Param("unit") String unit, @Param("cellphone") String cellphone, @Param("officephone") String officephone,
			@Param("address") String address);

	@Transactional
	@Modifying
	@Query("UPDATE Member m SET  m.password = :password WHERE m.memberId = :memberId")
	Integer updateMemberPassword(@Param("memberId") String memberId, @Param("password") String password);
	
	public List<Member> findAllByMemberId(String  memberId);
	
    public void deleteByMemberId(String memberId);
    
    @Transactional
	@Modifying
	@Query("UPDATE Member m SET  m.roles = :roles WHERE m.memberId = :memberId")
	Integer updateMemberRoles(@Param("memberId") String memberId, @Param("roles") Set<Role> roles);

	



	
	

}
