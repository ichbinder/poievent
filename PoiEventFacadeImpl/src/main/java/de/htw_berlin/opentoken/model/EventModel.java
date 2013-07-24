package de.htw_berlin.opentoken.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name = "user_id")
	private UserModel createdBy;
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="subscriptFor")
	private Set<UserModel> subscribedBy;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="event")
	private List<MessageModel> messages;

	@ManyToOne
	@JoinColumn(name = "poi_id")
	private PoiModel howIsTheEventOfPoi;

	public EventModel() {}
	
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

	public Set<UserModel> getSubscribedBy() {
		return subscribedBy;
	}

	public void setSubscribedBy(Set<UserModel> subscribedBy) {
		this.subscribedBy = subscribedBy;
	}

	public List<MessageModel> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageModel> messages) {
		this.messages = messages;
	}

	public PoiModel getHowIsTheEventOfPoi() {
		return howIsTheEventOfPoi;
	}

	public void setHowIsTheEventOfPoi(PoiModel howIsTheEventOfPoi) {
		this.howIsTheEventOfPoi = howIsTheEventOfPoi;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
}
