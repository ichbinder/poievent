package de.htw_berlin.f4.ai.kbe.poievent;

import org.junit.After;
import org.junit.Before;


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
