package de.htw_berlin.f4.ai.kbe.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Event {

	@Id 
	@GeneratedValue 
	Long eventId;
	
	private String title;
	
	private String description;
	@ManyToOne
	private User createdBy;
	@ManyToMany
	private Set<User> subscribedBy;
	@OneToMany
	private Set<Message> messages;
	
	public Event(String title,String description,User createdBy)
	{
		this.title = title;
		this.description = description;
		this.createdBy = createdBy;
	}
	public Event(User createdBy)
	{
		this.createdBy = createdBy;
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
	
	public void addSubscribtion(User user)
	{
		subscribedBy.add(user);
	}
	
	public Set<User> getSubscribted(){
		return subscribedBy;
	}
	public void addMessage(Message message)
	{
		messages.add(message);
	}
	
	public Set<Message> getMessage(){
		return messages;
	}
}
