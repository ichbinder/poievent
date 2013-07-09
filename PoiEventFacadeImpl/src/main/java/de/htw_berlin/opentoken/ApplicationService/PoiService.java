package de.htw_berlin.opentoken.ApplicationService;

import java.util.List;
import java.util.Set;

import de.htw_berlin.f4.ai.kbe.model.UserModel;
import de.htw_berlin.f4.ai.kbe.poievent.Coordinate;
import de.htw_berlin.f4.ai.kbe.poievent.Event;
import de.htw_berlin.f4.ai.kbe.poievent.Poi;

public interface PoiService {

	public void createSimplePOI(Long userId, String name, Set<String> tags,
			Float latitude, Float longitude);

	public void createCityPOI(Long userId, String name, Set<String> tags,
			String street, String city, Float latitude, Float longitude);

	public void createPolygonPOI(Long userId, String name, Set<String> tags,
			List<Coordinate> polygon);

	public void deletePOI(Long userId, String name);

	public void addPoiTag(Long userId, String name, String tag);

	public void deletePoiTag(Long userId, String name, String tag);

	public Set<Poi> getPoiByTag(String tag);

	public Poi getPoi(String name);

	boolean validatePoi(String name);

	void addEvent(Event event, UserModel createdBy, String poiName);

	Set<Event> getAllEventsByPoi(String poiName);

}
