package de.htw_berlin.f4.ai.kbe.poievent;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

@Ignore
public class OwnUserInit extends OwnTestInit {
	protected Long testAdmin;
	protected Long testUser;
	
	@Before
	public void setUp()
	{
		testAdmin = facadeImplTest.createUser("Test", "admin", "admin@test.com");
		testUser = facadeImplTest.createUser("Test", "User", "user@test.com");
		facadeImplTest.setAdminRole(testAdmin);
	}
	 
	@After 
	public void tearDown(){
		facadeImplTest.deleteUser("admin@test.com");
		facadeImplTest.deleteUser("user@test.com");
	}
}
