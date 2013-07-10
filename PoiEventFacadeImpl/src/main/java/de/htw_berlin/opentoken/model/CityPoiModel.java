package de.htw_berlin.opentoken.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CityPoiModel")
public class CityPoiModel extends SimplePoiModel {
	
	private String street;
	
	private String city;
	
	public CityPoiModel(){
		super();
	}
	
	public CityPoiModel(String name, Set<TagModel> tags, UserModel createdBy, String street,
			String city, Float latitude, Float longitude){
		super(name, tags, createdBy, longitude, latitude);
		this.street = street;
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
}
