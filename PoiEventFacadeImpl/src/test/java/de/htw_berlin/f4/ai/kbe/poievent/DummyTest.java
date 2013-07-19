package de.htw_berlin.f4.ai.kbe.poievent;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


public class DummyTest extends TestInit {

	
	@Ignore
	@Test
	public void dummy(){
		poiEvent.getPoi(null);
	}
}
