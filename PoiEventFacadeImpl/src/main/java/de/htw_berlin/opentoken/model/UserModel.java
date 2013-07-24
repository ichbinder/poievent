package de.htw_berlin.opentoken.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity 
public class UserModel implements Serializable {

	private static final long serialVersionUID = -1230508833735133156L;

	@Id 
	@GeneratedValue 
	Long userId;
	
	private String name;
	
	private String firstname;
	
	private String email;
	
	private Boolean admin;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="createdBy")
	private Set<EventModel> createdEvent;
	
	@ManyToMany
	private Set<EventModel> subscriptFor;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="createdBy")
	private List<PoiModel> listOfManagedPois;
	
	public UserModel() {}
	
	public UserModel(String name, String firstname, String email) {
		this.createUser(name, firstname, email);
	}
	
	public Long createUser(String name, String firstname, String email) {
		this.name = name;
		this.firstname = firstname;
		this.email = email;
		this.admin = false;
		return userId;
	}
	
	public Long getUserId()
	{
		return userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public Set<EventModel> getOwnedEvents()
	{
		return createdEvent;
	}
	
	public void addOwnedEvent(EventModel event)
	{
		createdEvent.add(event);
	}
	
	public Set<EventModel> getSubscriptedEvent()
	{
		return subscriptFor;
	}
	
	public void addSubscriptionFor(EventModel event)
	{
		subscriptFor.add(event);
	}

	public List<PoiModel> getListOfManagedPois() {
		return listOfManagedPois;
	}

	public void setListOfManagedPois(List<PoiModel> listOfManagedPois) {
		this.listOfManagedPois = listOfManagedPois;
	}
}
