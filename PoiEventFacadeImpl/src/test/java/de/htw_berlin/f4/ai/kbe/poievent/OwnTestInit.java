package de.htw_berlin.f4.ai.kbe.poievent;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
classes =  de.htw_berlin.f4.ai.kbe.config.SpringConfig.class)

@Ignore
public class OwnTestInit {
	
	
	@Autowired
	protected PoiEventFacade facadeImplTest;
	

	
}
