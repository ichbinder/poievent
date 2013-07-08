package de.htw_berlin.f4.ai.kbe.model;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class EventModel {

	@Id 
	@GeneratedValue 
	Long eventId;
	
	private String title;
	
	private String description;
	
	private Calendar date;
	
	@OneToOne
	private UserModel createdBy;
	@ManyToMany
	private Set<UserModel> subscribedBy;
	@OneToMany
	private Set<MessageModel> messages;
	
	public EventModel(String title,String description,UserModel createdBy)
	{
		this.title = title;
		this.description = description;
		this.createdBy = createdBy;
		this.date = null;
	}
	public EventModel(UserModel createdBy)
	{
		this.createdBy = createdBy;
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
	
	public Set<MessageModel> getMessage(){
		return messages;
	}
}
