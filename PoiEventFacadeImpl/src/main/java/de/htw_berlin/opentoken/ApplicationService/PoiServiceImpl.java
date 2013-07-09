package de.htw_berlin.opentoken.ApplicationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.htw_berlin.f4.ai.kbe.model.CityPoiModel;
import de.htw_berlin.f4.ai.kbe.model.CoordinateModel;
import de.htw_berlin.f4.ai.kbe.model.PoiModel;
import de.htw_berlin.f4.ai.kbe.model.PolygonPoiModel;
import de.htw_berlin.f4.ai.kbe.model.SimplePoiModel;
import de.htw_berlin.f4.ai.kbe.model.UserModel;
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
		if (userRepository.findOne(userId) != null) {
			if (geokoordinateOk(latitude, longitude)) {
				UserModel user = userRepository.findOne(userId);
				SimplePoiModel simplePoiModel = new SimplePoiModel(name, tags, user, longitude, latitude);
				simplePoiRepository.save(simplePoiModel);
			} else {
				throw new IllegalArgumentException("Geokoordinaten liegen nicht im Bereich.");
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	public void createCityPOI(Long userId, String name, Set<String> tags,
			String street, String city, Float latitude, Float longitude) {
		if (userRepository.findOne(userId) != null) {
			if (geokoordinateOk(latitude, longitude)) {
				UserModel user = userRepository.findOne(userId);
				CityPoiModel cityPoiModel = new CityPoiModel(name, tags, user, street, city, latitude, longitude);
				cityPoiRepository.save(cityPoiModel);
			} else {
				throw new IllegalArgumentException("Geokoordinaten liegen nicht im Bereich.");
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	public void createPolygonPOI(Long userId, String name, Set<String> tags,
			List<Coordinate> polygon) {
		if (userRepository.findOne(userId) != null) {
			if (geokoordinateOk(polygon)) {
				List<CoordinateModel> coordinateModels = new ArrayList<CoordinateModel>();
				for (int i = 0; i < polygon.toArray().length; i++) {
					CoordinateModel coordinateModelTmp = new CoordinateModel(polygon.get(i).getLatitude(), polygon.get(i).getLongitude());
					coordinateModels.add(coordinateModelTmp);
				}
				UserModel user = userRepository.findOne(userId);
				PolygonPoiModel polygonPoiModel = new PolygonPoiModel(name, tags, user, coordinateModels);
				polygonPoiRepository.save(polygonPoiModel);
			} else {
				throw new IllegalArgumentException("Geokoordinaten liegen nicht im Bereich.");
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	public void deletePOI(Long userId, String name) {
		if (userRepository.findOne(userId) != null) {
			PoiModel poiModel = poiRepository.findByCreatedByAndName(userId, name);
			poiRepository.delete(poiModel);
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	public void addPoiTag(Long userId, String name, String tag) {
		if (userRepository.findOne(userId) != null) {
			PoiModel poiModel = poiRepository.findByCreatedByAndName(userId, name);
			Set<String> tags = poiModel.getTags();
			if (!tags.isEmpty()) {
				if (!tags.contains(tag)) {
					tags.add(tag);
				} else {
					throw new IllegalArgumentException("Tag exsistiert schon.");
				}
			} else {
				tags.add(tag);
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	public void deletePoiTag(Long userId, String name, String tag) {
		if (userRepository.findOne(userId) != null) {
			if (poiRepository.findByName(name) != null) {
				PoiModel poiModel = poiRepository.findByCreatedByAndName(userId, name);
				Set<String> tags = poiModel.getTags();
				if (!tags.isEmpty()) {
					if (tags.contains(tag)) {
						tags.remove(tag);
					} else {
						throw new IllegalArgumentException("Tag exsistiert nicht.");
					}
				} else {
					throw new IllegalArgumentException("Tag exsistiert nicht.");
				}
			} else {
				throw new IllegalArgumentException("Poi existiert nicht.");
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
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
	public boolean geokoordinateOk(List<Coordinate> coordinates) {
		return true;
	}
}
