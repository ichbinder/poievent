package de.htw_berlin.f4.ai.kbe.model;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class CityPoiModel extends SimplePoiModel{

	@Id 
	@GeneratedValue 
	Long cityPoiId;
	
	private String street;
	
	private String city;
	
	public CityPoiModel(){
		super();
	}
	
	public CityPoiModel(String name, Set<String> tags, String street,
			String city, Float latitude, Float longitude){
		super(name, tags, longitude, latitude);
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
