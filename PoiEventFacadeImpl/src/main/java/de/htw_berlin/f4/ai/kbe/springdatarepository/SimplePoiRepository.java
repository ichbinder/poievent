package de.htw_berlin.f4.ai.kbe.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.htw_berlin.f4.ai.kbe.model.SimplePoiModel;

@Repository
public interface SimplePoiRepository extends JpaRepository<SimplePoiModel, Long> {

	public SimplePoiModel findByName(String name);
}
