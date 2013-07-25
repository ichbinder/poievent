package de.htw_berlin.f4.ai.kbe.poievent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

import de.htw_berlin.opentoken.model.PoiModel;
import de.htw_berlin.opentoken.springdatarepository.PoiRepository;

@Ignore
public class OwnTestPoiInit extends OwnUserInit{
	
	PoiModel musterPoi;
	String poiName;
	
	@Autowired
	PoiRepository poiRepository;
	
	@Before
	public void setup()
	{	
		poiName = "testPoi";		
		
		facadeImplTest.createSimplePOI(testAdmin, poiName, tagsInit(), 13.526187f, 52.457735f);
		musterPoi = poiRepository.findByName(poiName);
	}
	
	public Set<String> tagsInit()
	{
		Set<String> temp = new HashSet<String>(Arrays.asList("Tag1", "Tag2"));
		return temp;
	}
}
