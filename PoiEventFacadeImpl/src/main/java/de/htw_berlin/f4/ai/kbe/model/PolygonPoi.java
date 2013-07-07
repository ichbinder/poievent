package de.htw_berlin.f4.ai.kbe.model;

import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="PolygonPoi")
public class PolygonPoi extends Poi {
	
	private List<Coordinate> polygon;

	public PolygonPoi(){	
		super();
	}
	
	public PolygonPoi(String name, Set<String> tags, List<Coordinate> polygon){
		this.name =name;
		this.tags = tags;
		this.polygon = polygon;
	}
	
	public List<Coordinate> getPolygon() {
		return polygon;
	}

	public void setPolygon(List<Coordinate> polygon) {
		this.polygon = polygon;
	}
	
	
	
}
