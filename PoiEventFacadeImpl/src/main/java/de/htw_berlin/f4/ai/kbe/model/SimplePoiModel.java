package de.htw_berlin.f4.ai.kbe.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="SimplePoi")
public class SimplePoiModel extends PoiModel {

	private Float latitude;
	
	private Float longitude;

	public SimplePoiModel(){
	}
	
	public SimplePoiModel(String name, Set<String> tags, UserModel createdBy, Float longitude, Float latitude){
		this.name = name;
		this.tags = tags;
		this.createdBy = createdBy;
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
