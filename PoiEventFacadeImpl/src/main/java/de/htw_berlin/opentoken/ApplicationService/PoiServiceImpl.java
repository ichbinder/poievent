package de.htw_berlin.opentoken.ApplicationService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.htw_berlin.f4.ai.kbe.poievent.AuthorizationException;
import de.htw_berlin.f4.ai.kbe.poievent.Coordinate;
import de.htw_berlin.f4.ai.kbe.poievent.Poi;
import de.htw_berlin.f4.ai.kbe.springdatarepository.CityPoiRepository;
import de.htw_berlin.f4.ai.kbe.springdatarepository.PoiRepository;
import de.htw_berlin.f4.ai.kbe.springdatarepository.PolygonPoiRepository;
import de.htw_berlin.f4.ai.kbe.springdatarepository.SimplePoiRepository;
import de.htw_berlin.f4.ai.kbe.springdatarepository.UserRepository;

@Service
public class PoiServiceImpl implements PoiService {
	
	@Autowired
	PoiRepository poiRepository;
	@Autowired
	SimplePoiRepository simplePoiRepository;
	@Autowired
	CityPoiRepository cityPoiRepository;
	@Autowired
	PolygonPoiRepository polygonPoiRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void createSimplePOI(Long userId, String name, Set<String> tags,
			Float latitude, Float longitude) {
		if(userRepository.findOne(userId) != null) {
			if(geokoordinateOk(latitude, longitude)) {
				
			} else {
				throw new IllegalArgumentException("Geokoordinaten liegen nicht im Bereich.");
			}			
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	public void createCityPOI(Long userId, String name, Set<String> tags,
			String street, String city, Float latitude, Float longitude)
			throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPolygonPOI(Long userId, String name, Set<String> tags,
			List<Coordinate> polygon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePOI(Long userId, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPoiTag(Long userId, String name, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePoiTag(Long userId, String name, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Poi> getPoiByTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Poi getPoi(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean geokoordinateOk(Float latitude, Float longitude) {
		return true;
	}
}
