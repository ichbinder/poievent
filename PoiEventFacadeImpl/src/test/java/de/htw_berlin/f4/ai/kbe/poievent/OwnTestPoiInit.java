package de.htw_berlin.f4.ai.kbe.poievent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

import de.htw_berlin.opentoken.model.PoiModel;
import de.htw_berlin.opentoken.model.SimplePoiModel;
import de.htw_berlin.opentoken.model.TagModel;
import de.htw_berlin.opentoken.springdatarepository.PoiRepository;
import de.htw_berlin.opentoken.springdatarepository.SimplePoiRepository;
import de.htw_berlin.opentoken.springdatarepository.UserRepository;

@Ignore
public class OwnTestPoiInit extends OwnUserInit{
	
	PoiModel musterPoi;
	String poiName;
	
	
	@Autowired
	PoiRepository poiRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SimplePoiRepository simplePoiRepository;
	
	@Before
	public void setUp()
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
	
	
	public SimplePoiModel createSimplePoi() {
		SimplePoiModel simplePoiModel = new SimplePoiModel("UM", userRepository.findOne(testAdmin), 13.526187f, 52.457735f);
		Set<TagModel> tagModels  = tagModelsInit();
		for (TagModel tagModel : tagModels)
			tagModel.setPoi(simplePoiModel);
		simplePoiModel.setTags(tagModels);
		return simplePoiModel;
	}
	
	public Set<TagModel> tagModelsInit()
	{
		Set<TagModel> temp = new HashSet<TagModel>();
		TagModel tagModel1 = new TagModel("Hallo");
		TagModel tagModel2 = new TagModel("Hallo");
		temp.add(tagModel1);
		temp.add(tagModel2);
		return temp;
	}
}
