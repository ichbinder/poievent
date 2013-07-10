package de.htw_berlin.f4.ai.kbe.poievent;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest extends TestInit{

	@Test
	public void testRoles() {
		long id = poiEvent.createUser("admin", "ober", "admin@htw-berlin.de");
		assertFalse(poiEvent.hasAdminRole(id));
		long id2 = poiEvent.createUser("admin", "ober", "admin@htw-berlin.de");
		assertFalse(poiEvent.hasAdminRole(id2));
		/*poiEvent.setAdminRole(id);
		assertTrue(poiEvent.hasAdminRole(id));
		poiEvent.removeAdminRole(id);
		assertFalse(poiEvent.hasAdminRole(id));
		poiEvent.deleteUser(id);*/
	}	
}
