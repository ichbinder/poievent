package de.htw_berlin.opentoken.springdatarepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.htw_berlin.opentoken.model.EventModel;
import de.htw_berlin.opentoken.model.TagModel;
import de.htw_berlin.opentoken.model.UserModel;

@Repository
public interface TagRepository extends JpaRepository<TagModel, Long> {

	//@Query("Select e from TagModel e where e.tag = ?1")	
	List<TagModel> findByTag(String tag);
}
