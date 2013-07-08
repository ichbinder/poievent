package de.htw_berlin.f4.ai.kbe.model;

import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="PolygonPoi")
public class PolygonPoiModel extends PoiModel {
	
	private List<CoordinateModel> polygon;

	public PolygonPoiModel(){	
		super();
	}
	
	public PolygonPoiModel(String name, Set<String> tags, List<CoordinateModel> polygon){
		this.name =name;
		this.tags = tags;
		this.polygon = polygon;
	}
	
	public List<CoordinateModel> getPolygon() {
		return polygon;
	}

	public void setPolygon(List<CoordinateModel> polygon) {
		this.polygon = polygon;
	}
	
	
	
}
