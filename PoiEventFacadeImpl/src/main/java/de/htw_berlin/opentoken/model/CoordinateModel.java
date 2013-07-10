package de.htw_berlin.opentoken.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CoordinateModel implements Serializable{

	@Id 
	@GeneratedValue 
	Long coordinateId;
	
	private float latitude;
	
	private float longitude;
	
	@ManyToOne
	private PolygonPoiModel polygonPoi;
	
	public CoordinateModel(float latitute, float longitude) {
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
