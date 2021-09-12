package tw.com.order.demo.repository;



import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.order.demo.entities.Member;
import tw.com.order.demo.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query("SELECT r FROM Role r WHERE r.roleName =:roleName ")
	Role findByName(String roleName);
	
	@Query("SELECT r FROM Role r WHERE r.id =:id ")
	Role findById(Set<Role> id);
	
	
				
	
	
	
}
