package de.htw_berlin.opentoken.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.htw_berlin.opentoken.model.PolygonPoiModel;

@Repository
public interface PolygonPoiRepository extends JpaRepository<PolygonPoiModel, Long> {

	public PolygonPoiModel findByName(String name);
}