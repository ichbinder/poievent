package de.htw_berlin.f4.ai.kbe.poievent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;

public class OwnTestPoiInit extends OwnUserInit{
	
	Poi musterPoi;
	
	@Before
	public void setup()
	{
		super.setUp();
		
		facadeImplTest.createSimplePOI(testAdmin, "testPoi", tagsInit(), 13.526187f, 52.457735f);
		musterPoi = facadeImplTest.getPoi("testPoi");
		
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
