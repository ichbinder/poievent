package de.htw_berlin.f4.ai.kbe.poievent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import de.htw_berlin.opentoken.model.PoiModel;
import de.htw_berlin.opentoken.springdatarepository.PoiRepository;

public class OwnTestPoiInit extends OwnUserInit{
	
	PoiModel musterPoi;
	String poiName;
	
	@Autowired
	PoiRepository poiRepository;
	
	@Before
	public void setup()
	{
		super.setUp();
		
		poiName = "testPoi";		
		
		facadeImplTest.createSimplePOI(testAdmin, poiName, tagsInit(), 13.526187f, 52.457735f);
		musterPoi = poiRepository.findByName(poiName);
	}
	
	public Set<String> tagsInit()
	{
		Set<String> temp = new HashSet<String>(Arrays.asList("Tag1", "Tag2"));
		return temp;
	}
	@After
	public void tearDown()
	{
		facadeImplTest.deletePOI(testAdmin, "testPoi");
	}
}
