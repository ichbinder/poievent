package de.htw_berlin.f4.ai.kbe.poievent;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
/*
public class PoiTagsTest extends TestInitWithPois{

	private static final String UAS = "university of applied science";
	
	
	@Test
	public void testAddTags(){
		poiEvent.addPoiTag(adminId, PoiEventTestConstants.HTW_NAME, UAS);	
		Poi poi = poiEvent.getPoi(PoiEventTestConstants.HTW_NAME);
		Set<String> tags = poi.getTags();
		System.out.println("Halofndjkls " + tags.contains(UAS));
		assertTrue(tags.contains(UAS));
	}
	
	@Test(expected=AuthorizationException.class)
	public void testAddTagsWithoutPermissions(){
		poiEvent.addPoiTag(userId, PoiEventTestConstants.HTW_NAME, UAS);
	}
	
	@Test 
	public void testDeletePoiTags(){
		poiEvent.deletePoiTag(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HOCHSCHULE);
		Poi poi = poiEvent.getPoi(PoiEventTestConstants.HTW_NAME);
		Set<String> tags = poi.getTags();
		assertFalse(tags.contains(PoiEventTestConstants.HOCHSCHULE));
	}
	
	@Test(expected=AuthorizationException.class)
	public void testDeleteTagsWithoutPermissions(){
		poiEvent.deletePoiTag(userId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HOCHSCHULE);
	}
	
	@Test 
	public void testGetByTags(){
		poiEvent.createSimplePOI(adminId, "AAA", new HashSet<String>(Arrays.asList("Bildung", "Hausbau")), 0f, 0f);
		poiEvent.createSimplePOI(adminId, "BBB", new HashSet<String>(Arrays.asList("Bildung", "Mauer")), 0f, 0f);
		poiEvent.createSimplePOI(adminId, "CCC", new HashSet<String>(Arrays.asList("Spiegel", "Welt")), 0f, 0f);
	
		assertEquals(2, poiEvent.getPoiByTag("Bildung").size());
		assertEquals(1, poiEvent.getPoiByTag("Spiegel").size());
		
		poiEvent.deletePOI(adminId, "AAA");
		poiEvent.deletePOI(adminId, "BBB");
		poiEvent.deletePOI(adminId, "CCC");
		
	}
	
}*/