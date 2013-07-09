package de.htw_berlin.f4.ai.kbe.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.htw_berlin.f4.ai.kbe.model.SimplePoiModel;

public interface SimplePoiRepository extends JpaRepository<SimplePoiModel, Long> {

}
