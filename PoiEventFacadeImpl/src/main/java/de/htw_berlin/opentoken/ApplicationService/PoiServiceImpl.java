package de.htw_berlin.opentoken.ApplicationService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.htw_berlin.f4.ai.kbe.poievent.AuthorizationException;
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
import de.htw_berlin.opentoken.model.TagModel;
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
	@Transactional
	public void createSimplePOI(Long userId, String name, Set<String> tags,
			Float latitude, Float longitude) {
		if (userId != null) {
			if (userRepository.findOne(userId) != null) {
				if (userRepository.findByAdmin(userId) != null) {
					if (geokoordinateOk(latitude, longitude)) {
						UserModel user = userRepository.findOne(userId);
						Set<TagModel> tagModels = new HashSet<TagModel>();
						for (String tag : tags) {
							TagModel tagModel = new TagModel(tag);
							tagModels.add(tagModel);
						}				
						SimplePoiModel simplePoiModel = new SimplePoiModel(name, tagModels, user, longitude, latitude);
						simplePoiRepository.saveAndFlush(simplePoiModel);
					} else {
						throw new IllegalArgumentException("Geokoordinaten liegen nicht im Bereich.");
					}
				} else {
					throw new AuthorizationException(userId);
				}
			} else {
				throw new IllegalArgumentException("Nutzer existiert nicht.");
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	@Transactional
	public void createCityPOI(Long userId, String name, Set<String> tags,
			String street, String city, Float latitude, Float longitude) {
		if (userId != null) {
			if (userRepository.findOne(userId) != null) {
				if (userRepository.findByAdmin(userId) != null) {
					if (geokoordinateOk(latitude, longitude)) {
						UserModel user = userRepository.findOne(userId);
						Set<TagModel> tagModels = new HashSet<TagModel>();
						for (String tag : tags) {
							TagModel tagModel = new TagModel(tag);
							tagModels.add(tagModel);
						}	
						CityPoiModel cityPoiModel = new CityPoiModel(name, tagModels, user, street, city, latitude, longitude);
						cityPoiRepository.saveAndFlush(cityPoiModel);
					} else {
						throw new IllegalArgumentException("Geokoordinaten liegen nicht im Bereich.");
					}
				} else {
					throw new AuthorizationException(userId);
				}
			} else {
				throw new IllegalArgumentException("Nutzer existiert nicht.");
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	@Transactional
	public void createPolygonPOI(Long userId, String name, Set<String> tags,
			List<Coordinate> polygon) {
		if (userId != null) {
			if (userRepository.findOne(userId) != null) {
				if (userRepository.findByAdmin(userId) != null) {
					if (geokoordinateOk(polygon)) {
						List<CoordinateModel> coordinateModels = new ArrayList<CoordinateModel>();
						for (int i = 0; i < polygon.toArray().length; i++) {
							CoordinateModel coordinateModelTmp = new CoordinateModel(polygon.get(i).getLatitude(), polygon.get(i).getLongitude());
							coordinateModels.add(coordinateModelTmp);
						}
						UserModel user = userRepository.findOne(userId);
						Set<TagModel> tagModels = new HashSet<TagModel>();
						for (String tag : tags) {
							TagModel tagModel = new TagModel(tag);
							tagModels.add(tagModel);
						}	
						PolygonPoiModel polygonPoiModel = new PolygonPoiModel(name, tagModels, user, coordinateModels);
						polygonPoiRepository.saveAndFlush(polygonPoiModel);
					} else {
						throw new IllegalArgumentException("Geokoordinaten liegen nicht im Bereich.");
					}
				} else {
					throw new AuthorizationException(userId);
				}
			} else {
				throw new IllegalArgumentException("Nutzer existiert nicht.");
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	@Transactional
	public void deletePOI(Long userId, String name) {
		if (userId != null) {
			if (userRepository.findOne(userId) != null) {
				if (userRepository.findByAdmin(userId) != null) {
					PoiModel poiModel = poiRepository.findByName(name);
					System.out.println(poiRepository.count());
					poiRepository.delete(poiModel);
				} else {
					throw new AuthorizationException(userId);
				}
			} else {
				throw new IllegalArgumentException("Nutzer existiert nicht.");
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	@Transactional
	public void addPoiTag(Long userId, String name, String tag) {
		if (userId != null) {
			if (userRepository.findOne(userId) != null) {
				if (userRepository.findByAdmin(userId) != null) {
					PoiModel poiModel = poiRepository.findByName(name);
					Set<TagModel> tags = poiModel.getTags();
					TagModel tagModel = new TagModel(tag);
					if (!tags.isEmpty()) {
						if (!tags.contains(tagModel)) {
							tags.add(tagModel);
							poiModel.setTags(tags);
							poiRepository.saveAndFlush(poiModel);
						} else {
							throw new IllegalArgumentException("Tag exsistiert schon.");
						}
					} else {
						tags.add(tagModel);
						poiModel.setTags(tags);
						poiRepository.saveAndFlush(poiModel);
					}
				} else {
					throw new AuthorizationException(userId);
				}
			} else {
				throw new IllegalArgumentException("Nutzer existiert nicht.");
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	@Transactional
	public void deletePoiTag(Long userId, String name, String tag) {
		if (userId != null) {
			if (userRepository.findOne(userId) != null) {
				if (userRepository.findByAdmin(userId) != null) {
					if (poiRepository.findByName(name) != null) {
						PoiModel poiModel = poiRepository.findByName(name);
						//Hibernate.initialize(poiModel.getTags());
						Set<TagModel> tags = poiModel.getTags();
						TagModel tagModel = new TagModel(tag);
						if (!tags.isEmpty()) {
							if (tags.contains(tagModel)) {
								tags.remove(tagModel);
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
					throw new AuthorizationException(userId);
				}
			} else {
				throw new IllegalArgumentException("Nutzer existiert nicht.");
			}
		} else {
			throw new IllegalArgumentException("Nutzer existiert nicht.");
		}
	}

	@Override
	@Transactional
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
	@Transactional
	public Poi getPoi(String name) {
		if (cityPoiRepository.findByName(name) != null) {
			CityPoiModel cityPoiModel = cityPoiRepository.findByName(name);
			Set<String> tags = new HashSet<String>();
			for (TagModel tagModel : cityPoiModel.getTags())
				tags.add(tagModel.getTag());
			CityPoi cityPoi = new CityPoi(cityPoiModel.getName(), tags, cityPoiModel.getStreet(), 
					cityPoiModel.getCity(), cityPoiModel.getLatitude(), cityPoiModel.getLongitude());
			return cityPoi;
		} else if (simplePoiRepository.findByName(name) != null) {
			SimplePoiModel simplePoiModel = simplePoiRepository.findByName(name);
			Set<String> tags = new HashSet<String>();
			for (TagModel tagModel : simplePoiModel.getTags())
				tags.add(tagModel.getTag());
			SimplePoi simplePoi = new SimplePoi(simplePoiModel.getName(), tags, 
					simplePoiModel.getLongitude(), simplePoiModel.getLatitude());
			return simplePoi;
		} else if (polygonPoiRepository.findByName(name) != null) {
			PolygonPoiModel polygonPoiModel = polygonPoiRepository.findByName(name);
			List<Coordinate> coordinate = new ArrayList<Coordinate>();
			for (int i = 0; i < polygonPoiModel.getPolygon().size(); i++) {
				Coordinate coordinateTmp = new Coordinate(polygonPoiModel.getPolygon().get(i).getLatitude(), 
						polygonPoiModel.getPolygon().get(i).getLongitude());
				coordinate.add(coordinateTmp);
			}
			Set<String> tags = new HashSet<String>();
			for (TagModel tagModel : polygonPoiModel.getTags())
				tags.add(tagModel.getTag());
			PolygonPoi polygonPoi = new PolygonPoi(polygonPoiModel.getName(), tags, coordinate);
			return polygonPoi;
		} else
			return null;
	}
	
	@Override
	@Transactional
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
	@Transactional
	public boolean validatePoi(String name) {
		if (getPoi(name) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
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
		if (latitude != null) {
			if (longitude != null) {
				if (latitude > -90 && latitude < 90)
					if (longitude > -180 && longitude < 180) 
						return true;
					else
						return false;
				else 
					return false;
			} else
				return false;
		} else
			return false;
	}
	
	public boolean geokoordinateOk(List<Coordinate> coordinates) {
		int trueCount = 0;
		if (coordinates != null) {
			for (int i = 0; i < coordinates.size(); i++) {
				if (geokoordinateOk(coordinates.get(i).getLatitude(), coordinates.get(i).getLongitude()))
					trueCount++;
			}
			if (trueCount == coordinates.size())
				return true;
			else
				return false;
		} else
			return false;
	}
}
