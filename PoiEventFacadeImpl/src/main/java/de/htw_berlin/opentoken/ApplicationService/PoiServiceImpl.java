package de.htw_berlin.opentoken.ApplicationService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.htw_berlin.f4.ai.kbe.poievent.CityPoi;
import de.htw_berlin.f4.ai.kbe.poievent.Coordinate;
import de.htw_berlin.f4.ai.kbe.poievent.Event;
import de.htw_berlin.f4.ai.kbe.poievent.Poi;
import de.htw_berlin.f4.ai.kbe.poievent.PolygonPoi;
import de.htw_berlin.f4.ai.kbe.poievent.SimplePoi;
import de.htw_berlin.opentoken.model.CityPoiModel;
import de.htw_berlin.opentoken.model.CoordinateModel;
import de.htw_berlin.opentoken.model.EventModel;
import de.htw_berlin.opentoken.model.PoiModel;
import de.htw_berlin.opentoken.model.PolygonPoiModel;
import de.htw_berlin.opentoken.model.SimplePoiModel;
import de.htw_berlin.opentoken.model.UserModel;
import de.htw_berlin.opentoken.springdatarepository.CityPoiRepository;
import de.htw_berlin.opentoken.springdatarepository.PoiRepository;
import de.htw_berlin.opentoken.springdatarepository.PolygonPoiRepository;
import de.htw_berlin.opentoken.springdatarepository.SimplePoiRepository;
import de.htw_berlin.opentoken.springdatarepository.UserRepository;

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
					poiModel.setTags(tags);
					poiRepository.saveAndFlush(poiModel);
				} else {
					throw new IllegalArgumentException("Tag exsistiert schon.");
				}
			} else {
				tags.add(tag);
				poiModel.setTags(tags);
				poiRepository.saveAndFlush(poiModel);
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
						poiModel.setTags(tags);
						poiRepository.saveAndFlush(poiModel);
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
		/*List<PoiModel> poiModels = poiRepository.findAll();
		Set<Poi> poiSet = new HashSet<Poi>();
		for (int i = 0; i < poiModels.size(); i++) {
			Set<String> tagsTmp = poiModels.get(i).getTags();
			for (int j = 0; j < tagsTmp.size(); j++) {
				if (tagsTmp.contains(tag)) {
					
					Poi poi = new 
					poiSet.add(poiModels.get(i));
				}
			}
		}*/
		return null;
	}

	@Override
	public Poi getPoi(String name) {
		if (simplePoiRepository.findByName(name) != null) {
			SimplePoiModel simplePoiModel = simplePoiRepository.findByName(name);
			SimplePoi simplePoi = new SimplePoi(simplePoiModel.getName(), simplePoiModel.getTags(), 
					simplePoiModel.getLongitude(), simplePoiModel.getLatitude());
			return simplePoi;
		} else if (cityPoiRepository.findByName(name) != null) {
			CityPoiModel cityPoiModel = cityPoiRepository.findByName(name);
			CityPoi cityPoi = new CityPoi(cityPoiModel.getName(), cityPoiModel.getTags(), cityPoiModel.getStreet(), 
					cityPoiModel.getCity(), cityPoiModel.getLatitude(), cityPoiModel.getLongitude());
			return cityPoi;
		} else if (polygonPoiRepository.findByName(name) != null) {
			PolygonPoiModel polygonPoiModel = polygonPoiRepository.findByName(name);
			List<Coordinate> coordinate = new ArrayList<Coordinate>();
			for (int i = 0; i < polygonPoiModel.getPolygon().size(); i++) {
				Coordinate coordinateTmp = new Coordinate(polygonPoiModel.getPolygon().get(i).getLatitude(), 
						polygonPoiModel.getPolygon().get(i).getLongitude());
				coordinate.add(coordinateTmp);
			}
			PolygonPoi polygonPoi = new PolygonPoi(polygonPoiModel.getName(), polygonPoiModel.getTags(), coordinate);
			return polygonPoi;
		} else
			return null;
	}
	
	@Override
	public void addEvent(Event event, UserModel createdBy, String poiName) {
		if (poiRepository.findByName(poiName) != null) {
			PoiModel poiModel = poiRepository.findByName(poiName);
			Set<EventModel> eventModels = new HashSet<EventModel>();
			eventModels.addAll(poiModel.getEvents());
			EventModel eventModel = new EventModel(event.getTitle(), event.getDescription(), createdBy);
			eventModels.add(eventModel);
			poiModel.setEvents(eventModels);
			poiRepository.saveAndFlush(poiModel);
		} else 
			throw new IllegalArgumentException("Poi existiert nicht.");
	}
	
	@Override
	public boolean validatePoi(String name) {
		if (getPoi(name) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Set<Event> getAllEventsByPoi(String poiName) {
		Set<Event> events = new HashSet<Event>();
		if (poiRepository.findByName(poiName) != null) {
			PoiModel poiModel = poiRepository.findByName(poiName);
			Set<EventModel> eventModels = new HashSet<EventModel>();
			eventModels = poiModel.getEvents();
			for (EventModel eventI : eventModels) {
				Event event = new Event();
				event.setTitle(eventI.getTitle());
				event.setDescription(eventI.getDescription());
				event.setDate(eventI.getDate());
				events.add(event);
			}
			
		} else
			throw new IllegalArgumentException("Poi existiert nicht.");
		return events;
	}
	
	public boolean geokoordinateOk(Float latitude, Float longitude) {
		if (latitude > -90 && latitude < 90)
			if (longitude > -180 && longitude < 180) 
				return true;
			else
				return false;
		else 
			return false;
	}
	
	public boolean geokoordinateOk(List<Coordinate> coordinates) {
		int trueCount = 0;
		for (int i = 0; i < coordinates.size(); i++) {
			if (geokoordinateOk(coordinates.get(i).getLatitude(), coordinates.get(i).getLongitude()))
				trueCount++;
		}
		if (trueCount == coordinates.size())
			return true;
		else
			return false;
	}
}
