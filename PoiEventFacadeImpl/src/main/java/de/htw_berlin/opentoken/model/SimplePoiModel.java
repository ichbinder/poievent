package de.htw_berlin.opentoken.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="SimplePoiModel")
public class SimplePoiModel extends PoiModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4834486233127369688L;

	private Float latitude;
	
	private Float longitude;

	public SimplePoiModel(){
	}
	
	public SimplePoiModel(String name, Set<TagModel> tags, UserModel createdBy, Float longitude, Float latitude){
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
