package de.htw_berlin.opentoken.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CoordinateModel implements Serializable{

	private static final long serialVersionUID = 6711963735464246234L;

	@Id 
	@GeneratedValue 
	Long coordinateId;
	
	private float latitude;
	
	private float longitude;
	
	@ManyToOne
	@JoinColumn(name = "polyPoi_id")
	private PolygonPoiModel howIsTheCoordinateOfPolyPoi;
	
	public CoordinateModel(float latitute, float longitude) {
		this.latitude = latitute;
		this.longitude = longitude;
	}
	
	public CoordinateModel() {}
		
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

	public PolygonPoiModel getPolygonPoi() {
		return howIsTheCoordinateOfPolyPoi;
	}

	public void setPolygonPoi(PolygonPoiModel polygonPoi) {
		this.howIsTheCoordinateOfPolyPoi = polygonPoi;
	}	
}
