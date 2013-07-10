package de.htw_berlin.f4.ai.kbe.poievent;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


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
		poiEvent.deletePOI(userId, PoiEventTestConstants.HTW_NAME);
		super.tearDown();
	}
	
}
