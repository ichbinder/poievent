package de.htw_berlin.opentoken.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.htw_berlin.opentoken.model.SimplePoiModel;

@Repository
public interface SimplePoiRepository extends JpaRepository<SimplePoiModel, Long> {

	public SimplePoiModel findByName(String name);
}
