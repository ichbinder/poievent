package de.htw_berlin.f4.ai.kbe.poievent;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


public class TestUserInit extends TestInit{

	protected Long adminId;
	protected Long userId;
	
	@Before
	public void setUp(){

		//when
		adminId = poiEvent.createUser("admin", "ober", "admin@htw-berlin.de");
		poiEvent.setAdminRole(adminId);
		userId = poiEvent.createUser("user", "normal", "user@htw-berlin.de");
	}
	 
	@After 
	public void tearDown(){
		poiEvent.deleteUser("admin@htw-berlin.de");
		poiEvent.deleteUser("user@htw-berlin.de");
	}
	
}
