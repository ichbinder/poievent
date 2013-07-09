package de.htw_berlin.f4.ai.kbe.springdatarepository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.htw_berlin.f4.ai.kbe.model.EventModel;
import de.htw_berlin.f4.ai.kbe.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

	@Query("Select u from User u where u.email = ?1")	
	UserModel findUserByEmail(String email);
}
