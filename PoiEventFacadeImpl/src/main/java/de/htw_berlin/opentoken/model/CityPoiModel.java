package de.htw_berlin.opentoken.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CityPoiModel")
public class CityPoiModel extends SimplePoiModel {
	
	private static final long serialVersionUID = 7005996652466494609L;

	private String street;
	
	private String city;
	
	public CityPoiModel(){
		super();
	}
	
	public CityPoiModel(String name, Set<TagModel> tags, UserModel createdBy, String street,
			String city, Float latitude, Float longitude){
		this(name, createdBy, street, city, latitude, longitude);
		super.setTags(tags);
	}
	
	public CityPoiModel(String name, UserModel createdBy, String street,
			String city, Float latitude, Float longitude){
		super(name, createdBy, longitude, latitude);
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
