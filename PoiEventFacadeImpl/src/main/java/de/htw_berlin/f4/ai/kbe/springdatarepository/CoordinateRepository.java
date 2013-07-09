package de.htw_berlin.f4.ai.kbe.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.htw_berlin.f4.ai.kbe.model.CoordinateModel;

public interface CoordinateRepository extends JpaRepository<CoordinateModel, Long> { 
	
}