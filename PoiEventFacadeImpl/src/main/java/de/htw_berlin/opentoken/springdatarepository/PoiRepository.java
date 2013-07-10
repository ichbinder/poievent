package de.htw_berlin.opentoken.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.htw_berlin.opentoken.model.PoiModel;

@Repository
public interface PoiRepository extends JpaRepository<PoiModel, Long> {
	
	public PoiModel findByName(String name);
	public PoiModel findByCreatedByAndName(Long userId, String name);
	
}
