package de.htw_berlin.f4.ai.kbe.springdatarepository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.htw_berlin.f4.ai.kbe.model.EventModel;
import de.htw_berlin.f4.ai.kbe.model.UserModel;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Long> {
	
	@Query("Select e from EventModel e where e.subscribedBy = ?1")	
	Set<EventModel> findSubscriptionFor(UserModel userModel);
	
	@Query("Select e from EventModel e where e.createdBy = ?1")	
	Set<EventModel> findOwnerFor(UserModel userModel);
}
