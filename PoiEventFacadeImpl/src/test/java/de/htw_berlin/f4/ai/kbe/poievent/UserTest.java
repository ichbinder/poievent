package de.htw_berlin.f4.ai.kbe.poievent;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest extends TestInit{

	@Test
	public void testRoles() {
		long id = poiEvent.createUser("admin", "ober", "admin@htw-berlin.de");
		assertFalse(poiEvent.hasAdminRole(id));
		poiEvent.setAdminRole(id);
		assertTrue(poiEvent.hasAdminRole(id));
		poiEvent.removeAdminRole(id);
		assertFalse(poiEvent.hasAdminRole(id));
		poiEvent.deleteUser(id);
	}

	@Test
	public void testDeleteById() {
		Long id = poiEvent.createUser("admin", "ober", "admin@htw-berlin.de");
		assertNotNull(poiEvent.getUserId("admin@htw-berlin.de"));
		poiEvent.deleteUser(id);
		assertNull(poiEvent.getUserId("admin@htw-berlin.de"));
	}
	
	@Test
	public void testDeleteByEmail() {
		Long id = poiEvent.createUser("admin", "ober", "admin@htw-berlin.de");
		assertEquals(id, poiEvent.getUserId("admin@htw-berlin.de"));
		poiEvent.deleteUser("admin@htw-berlin.de");
		assertNull(poiEvent.getUserId("admin@htw-berlin.de"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteByIllegalEmail() {
		poiEvent.deleteUser("admin@htw-berlin.de");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteByIllegalId() {
		poiEvent.deleteUser(2l);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHasAdminRoleOfNoExistingUser() {
		poiEvent.hasAdminRole(1l);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetAdminRoleOfNoExistingUser() {
		poiEvent.setAdminRole(0l);
	}
	
	@Test
	public void testUserExists() {
		poiEvent.createUser("admin", "ober", "admin@htw-berlin.de");
		try{
			poiEvent.createUser("huber", "Klaus", "admin@htw-berlin.de");
		}
		catch(IllegalArgumentException e){
			return;
		}
		finally{
			poiEvent.deleteUser("admin@htw-berlin.de");
		}
		
	}
	
	
}
