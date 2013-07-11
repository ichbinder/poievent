package de.htw_berlin.opentoken.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class PoiModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5976842760169608982L;

	@Id 
	@GeneratedValue 
	Long poiId;
	
	@OneToOne(mappedBy="poi")
	protected UserModel createdBy;
	
	protected String name;
	
	@OneToMany(mappedBy="poi")
	protected Set<TagModel> tags;
	
	@OneToMany(mappedBy="poi")
	protected Set<EventModel> events;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<TagModel> getTags() {
		return tags;
	}
	
	public void setTags(Set<TagModel> tags) {
		this.tags = tags;
	}

	public Set<EventModel> getEvents() {
		return events;
	}

	public void setEvents(Set<EventModel> events) {
		this.events = events;
	}

	public UserModel getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserModel createdBy) {
		this.createdBy = createdBy;
	}	
}