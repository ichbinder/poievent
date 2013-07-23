package de.htw_berlin.opentoken.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.htw_berlin.opentoken.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

	@Query("Select u from UserModel u where u.admin = true and u.userId = ?1")
	UserModel findByAdmin(Long userid);
	
	UserModel findByEmail(String email);
}
