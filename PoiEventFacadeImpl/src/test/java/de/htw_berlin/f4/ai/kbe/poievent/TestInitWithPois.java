package de.htw_berlin.f4.ai.kbe.poievent;

import org.junit.After;
import org.junit.Before;


public class TestInitWithPois extends TestUserInit{

	Poi examplePoi;
	
	@Before
	public void setUp(){
		super.setUp();
		poiEvent.createSimplePOI(adminId, PoiEventTestConstants.HTW_NAME, PoiEventTestConstants.HTW_TAGS, 
				PoiEventTestConstants.HTW_LATITUDE, PoiEventTestConstants.HTW_LONGITUDE);
		examplePoi = poiEvent.getPoi(PoiEventTestConstants.HTW_NAME);
	}
	 
	@After 
	public void tearDown(){
		poiEvent.deletePOI(adminId, PoiEventTestConstants.HTW_NAME);
		super.tearDown();
	}
	
}
