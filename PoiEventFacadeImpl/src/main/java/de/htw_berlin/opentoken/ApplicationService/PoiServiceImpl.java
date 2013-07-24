package de.htw_berlin.opentoken.ApplicationService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
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
import de.htw_berlin.opentoken.springdatarepository.EventRepository;
import de.htw_berlin.opentoken.springdatarepository.PoiRepository;
import de.htw_berlin.opentoken.springdatarepository.PolygonPoiRepository;
import de.htw_berlin.opentoken.springdatarepository.SimplePoiRepository;
import de.htw_berlin.opentoken.springdatarepository.TagRepository;
import de.htw_berlin.opentoken.springdatarepository.UserRepository;

@Service
public class PoiServiceImpl implements PoiService {

	private final static Logger log = Logger.getLogger(PoiServiceImpl.class);

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
	@Autowired
	TagRepository tagRepository;
	@Autowired
	EventRepository eventRepository;
	
	@Override
	@Transactional
	public void createSimplePOI(Long userId, String name, Set<String> tags,
			Float latitude, Float longitude) {
		if (userId != null) {
			if (userRepository.findOne(userId) != null) {
				if (userRepository.findByAdmin(userId) != null) {
					if (poiRepository.findByName(name) == null) {
						if (geokoordinateOk(latitude, longitude)) {
							UserModel createdBy = userRepository.findByAdmin(userId);
							SimplePoiModel simplePoiModel = new SimplePoiModel(name, createdBy, longitude, latitude);
							List<PoiModel> poiModels = createdBy.getListOfManagedPois();
							poiModels.add(simplePoiModel);
							createdBy.setListOfManagedPois(poiModels);
							Set<TagModel> tagModels = new HashSet<TagModel>();
							for (String tag : tags) {
								TagModel tagModel = new TagModel(tag);
								tagModel.setPoi(simplePoiModel);
								tagModels.add(tagModel);
							}
							simplePoiModel.setTags(tagModels);
							simplePoiRepository.save(simplePoiModel);
						} else {
							log.warn("createSimplePOI: Geokoordinaten liegen nicht im vorgegebenen Bereich.");
							throw new IllegalArgumentException("Geokoordinaten liegen nicht im vorgegebenen Bereich.");
						}
					} else {
						log.warn("createSimplePOI: SimplePoi existiert schon.");
						throw new IllegalArgumentException("SimplePoi existiert schon.");
					}
				} else {
					log.warn("createSimplePOI: Nutzer hat keine Admin-Rechte.");
					throw new AuthorizationException(userId);
				}
			} else {
				log.warn("createSimplePOI: Nutzer existiert nicht.");
				throw new IllegalArgumentException("Nutzer existiert nicht.");
			}
		} else {
			log.warn("createSimplePOI: Nutzer existiert nicht.");
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
					if (poiRepository.findByName(name) == null) {
						if (geokoordinateOk(latitude, longitude)) {
							UserModel createdBy = userRepository.findByAdmin(userId);
							CityPoiModel cityPoiModel = new CityPoiModel(name, createdBy, street, city, latitude, longitude);
							List<PoiModel> poiModels = createdBy.getListOfManagedPois();
							poiModels.add(cityPoiModel);
							createdBy.setListOfManagedPois(poiModels);
							Set<TagModel> tagModels = new HashSet<TagModel>();
							for (String tag : tags) {
								TagModel tagModel = new TagModel(tag);
								tagModel.setPoi(cityPoiModel);
								tagModels.add(tagModel);
							}
							cityPoiModel.setTags(tagModels);
							simplePoiRepository.save(cityPoiModel);
						} else {
							log.warn("createCityPOI: Geokoordinaten liegen nicht im vorgegebenen Bereich.");
							throw new IllegalArgumentException("Geokoordinaten liegen nicht im vorgegebenen Bereich.");
						}
					} else {
						log.warn("createCityPOI: CityPoi existiert schon.");
						throw new IllegalArgumentException("CityPoi existiert schon.");
					}
				} else {
					log.warn("createCityPOI: Nutzer hat keine Admin-Rechte.");
					throw new AuthorizationException(userId);
				}
			} else {
				log.warn("createCityPOI: Nutzer existiert nicht.");
				throw new IllegalArgumentException("Nutzer existiert nicht.");
			}
		} else {
			log.warn("createCityPOI: Nutzer existiert nicht.");
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
					if (poiRepository.findByName(name) == null) {
						if (geokoordinateOk(polygon)) {
							UserModel createdBy = userRepository.findOne(userId);
							PolygonPoiModel polygonPoiModel = new PolygonPoiModel(name, createdBy);
							List<PoiModel> poiModels = createdBy.getListOfManagedPois();
							poiModels.add(polygonPoiModel);
							createdBy.setListOfManagedPois(poiModels);
							List<CoordinateModel> coordinateModels = new ArrayList<CoordinateModel>();
							for (Coordinate coordi : polygon) {
								CoordinateModel coordinateModel = new CoordinateModel(coordi.getLatitude(), coordi.getLongitude());
								coordinateModel.setPolygonPoi(polygonPoiModel);
								coordinateModels.add(coordinateModel);
							}
							Set<TagModel> tagModels = new HashSet<TagModel>();
							for (String tag : tags) {
								TagModel tagModel = new TagModel(tag);
								tagModel.setPoi(polygonPoiModel);
								tagModels.add(tagModel);
							}	
							polygonPoiModel.setPolygon(coordinateModels);
							polygonPoiModel.setTags(tagModels);
							polygonPoiRepository.saveAndFlush(polygonPoiModel);
						} else {
							log.warn("createPolygonPOI: Geokoordinaten liegen nicht im vorgegebenen Bereich.");
							throw new IllegalArgumentException("Geokoordinaten liegen nicht im vorgegebenen Bereich.");
						}
					} else {
						log.warn("createPolygonPOI: PolygonPoi existiert schon.");
						throw new IllegalArgumentException("PolygonPoi existiert schon.");
					}
				} else {
					log.warn("createPolygonPOI: Nutzer hat keine Admin-Rechte.");
					throw new AuthorizationException(userId);
				}
			} else {
				log.warn("createPolygonPOI: Nutzer existiert nicht.");
				throw new IllegalArgumentException("Nutzer existiert nicht.");
			}
		} else {
			log.warn("createPolygonPOI: Nutzer existiert nicht.");
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
					poiRepository.delete(poiModel);
					UserModel userModel = userRepository.findOne(userId);
					userModel.getListOfManagedPois().remove(userModel.getListOfManagedPois().indexOf(poiModel));
					userRepository.saveAndFlush(userModel);
					
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
					TagModel tagModel = new TagModel(tag, poiModel);
					if (!tags.isEmpty()) {
						boolean tagExists = false;
						for (TagModel tagModelTmp : tags) {
							if (tagModelTmp.getTag().equals(tagModel.getTag())) {
								tagExists = true;
								break;
							}
						}
						if (!tagExists) {
							tags.add(tagModel);
							poiModel.setTags(tags);
							poiRepository.save(poiModel);
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
						Set<TagModel> tags = poiModel.getTags();
						TagModel tagModel = new TagModel(tag, poiModel);
						if (!tags.isEmpty()) {
							boolean tagExists = false;
							for (TagModel tagModelTmp : tags) {
								if (tagModelTmp.getTag().equals(tagModel.getTag())) {
									tags.remove(tagModelTmp);
									tagRepository.delete(tagModelTmp);
									tagExists = true;
									break;
								}
							}
							if (tagExists) {
								poiModel.setTags(tags);
								poiRepository.save(poiModel);
							} else {
								throw new IllegalArgumentException("Tag exsistiert nicht.");
							}
						} else {
							throw new IllegalArgumentException("Es existieren keine Tags in dem Poi.");
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
		List<TagModel> tagModels =  tagRepository.findByTag(tag);
		Set<Poi> returnPois = new HashSet<Poi>();
		for (TagModel tagModel : tagModels)
			returnPois.add(this.getPoi(tagModel.getPoi().getName()));
		if (returnPois.isEmpty())
			return null;
		else
			return returnPois;
	}

	@Override
	@Transactional
	public Poi getPoi(String name) {
		Poi returnPoi = null;
		if (poiRepository.findByName(name) != null) {
			PoiModel poiModel = poiRepository.findByName(name);
			if (poiModel instanceof CityPoiModel) {
				CityPoiModel cityPoiModel = (CityPoiModel)poiModel;
				Set<String> tags = new HashSet<String>();
				for (TagModel tagModel : cityPoiModel.getTags())
					tags.add(tagModel.getTag());
				Poi cityPoi = new CityPoi(cityPoiModel.getName(), tags, cityPoiModel.getStreet(), cityPoiModel.getCity(), 
											cityPoiModel.getLatitude(), cityPoiModel.getLongitude());
				returnPoi = cityPoi;
			} else if (poiModel instanceof SimplePoiModel) {
				SimplePoiModel simplePoiModel = (SimplePoiModel)poiModel;
				Set<String> tags = new HashSet<String>();
				for (TagModel tagModel : simplePoiModel.getTags())
					tags.add(tagModel.getTag());
				Poi simplePoi = new SimplePoi(simplePoiModel.getName(), tags, simplePoiModel.getLatitude(), simplePoiModel.getLongitude());
				returnPoi = simplePoi;
			} else if (poiModel instanceof PolygonPoiModel) {
				PolygonPoiModel polygonPoiModel = (PolygonPoiModel)poiModel;
				Set<String> tags = new HashSet<String>();
				for (TagModel tagModel : polygonPoiModel.getTags())
					tags.add(tagModel.getTag());
				List<Coordinate> coordinates = new ArrayList<Coordinate>();
				for (CoordinateModel coModel : polygonPoiModel.getPolygon())
					coordinates.add(new Coordinate(coModel.getLatitude(), coModel.getLongitude()));
				Poi polygonPoi = new PolygonPoi(polygonPoiModel.getName(), tags, coordinates);
				returnPoi = polygonPoi;
			}
		}
		return returnPoi;
	}
	
	@Override
	@Transactional
	public void addEvent(Event event, UserModel createdBy, String poiName) {
		if (poiRepository.findByName(poiName) != null) {
			PoiModel poiModel = poiRepository.findByName(poiName);
			Set<EventModel> eventModels = new HashSet<EventModel>();
			eventModels.addAll(poiModel.getEvents());
			EventModel eventModel = new EventModel(event.getTitle(), event.getDescription(), createdBy);
			eventModel.setHowIsTheEventOfPoi(poiModel);
			eventRepository.save(eventModel);
			eventModels.add(eventModel);
			poiModel.setEvents(eventModels);
			poiRepository.save(poiModel);
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
