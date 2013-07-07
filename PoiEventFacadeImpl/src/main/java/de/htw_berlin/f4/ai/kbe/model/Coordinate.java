package de.htw_berlin.f4.ai.kbe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Coordinate {

	@Id 
	@GeneratedValue 
	Long coordinateId;
	
	private float latitude;
	
	private float longitude;
	
	public Coordinate(float latitute, float longitude) {
		this.latitude = latitute;
		this.longitude = longitude;
	}
		
	public float getLongitude() {
		return longitude;
	}
	
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	public float getLatitude() {
		return latitude;
	}
	
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	
}
