package de.htw_berlin.f4.ai.kbe.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.htw_berlin.f4.ai.kbe.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

	@Query("Select u from User u where u.admin = true and u.userid = ?1")
	UserModel findByAdminTrue(Long userid);
	
	UserModel findByEmail(String email);
}
