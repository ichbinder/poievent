package de.htw_berlin.opentoken.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class PoiModel implements Serializable{
	
	private static final long serialVersionUID = -5976842760169608982L;

	@Id 
	@GeneratedValue 
	Long poiId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	protected UserModel createdBy;
	
	protected String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "howIsTheTagOfPoi")
	protected Set<TagModel> tags;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="howIsTheEventOfPoi")
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