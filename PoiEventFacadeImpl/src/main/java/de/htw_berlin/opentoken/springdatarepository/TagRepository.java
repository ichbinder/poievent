package de.htw_berlin.opentoken.springdatarepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.htw_berlin.opentoken.model.TagModel;

@Repository
public interface TagRepository extends JpaRepository<TagModel, Long> {

	List<TagModel> findByTag(String tag);
}
