package de.htw_berlin.f4.ai.kbe.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="SimplePoi")
public class SimplePoi extends Poi {

	private Float latitude;
	
	private Float longitude;

	public SimplePoi(){
	}
	
	public SimplePoi(String name, Set<String> tags, Float longitude, Float latitude){
		this.name =name;
		this.tags = tags;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Float getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
	public Float getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
}
