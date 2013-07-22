package de.htw_berlin.opentoken.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class EventModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2628919549657024832L;

	@Id 
	@GeneratedValue 
	Long eventId;
	
	private String title;
	
	private String description;
	
	private Calendar date;
	
	@ManyToOne
	private UserModel createdBy;
	
	@ManyToMany(mappedBy="subscriptFor")
	private Set<UserModel> subscribedBy;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="event")
	private List<MessageModel> messages;

	@ManyToOne
	private PoiModel poi;

	public EventModel() {
		// TODO Auto-generated constructor stub
	}
	
	public EventModel(String title,String description,UserModel createdBy)
	{
		this.title = title;
		this.description = description;
		this.createdBy = createdBy;
		this.date = Calendar.getInstance();
	}
	public EventModel(UserModel createdBy)
	{
		this.createdBy = createdBy;
		this.date = Calendar.getInstance();
		this.title = "";
		this.description = "";
	}
	public UserModel getOwner()
	{
		return createdBy;
	}
	public Long getEventId()
	{
		return eventId; 
	}
	public Calendar getDate()
	{
		return date;
	}
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addSubscribtion(UserModel user)
	{
		subscribedBy.add(user);
	}
	
	public Set<UserModel> getSubscribted(){
		return subscribedBy;
	}
	public void addMessage(MessageModel message)
	{
		messages.add(message);
	}
	
	public List<MessageModel> getMessage(){
		return messages;
	}
	public UserModel getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserModel createdBy) {
		this.createdBy = createdBy;
	}
}
