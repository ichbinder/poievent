package de.htw_berlin.opentoken.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.htw_berlin.opentoken.model.CityPoiModel;

@Repository
public interface CityPoiRepository extends JpaRepository<CityPoiModel, Long> {
	
	public CityPoiModel findByName(String name);
}