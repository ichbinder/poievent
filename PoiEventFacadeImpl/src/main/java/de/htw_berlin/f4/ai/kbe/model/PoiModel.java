package de.htw_berlin.f4.ai.kbe.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class PoiModel {
	
	@Id 
	@GeneratedValue 
	Long poiId;
	
	protected String name;
	
	protected Set<String> tags;
	
	@OneToMany(mappedBy="poi")
	protected Set<EventModel> events;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<String> getTags() {
		return tags;
	}
	
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<EventModel> getEvents() {
		return events;
	}

	public void setEvents(Set<EventModel> events) {
		this.events = events;
	}	
}