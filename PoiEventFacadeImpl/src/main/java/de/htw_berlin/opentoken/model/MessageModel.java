package de.htw_berlin.opentoken.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MessageModel implements Comparable<MessageModel>,Serializable{

	private static final long serialVersionUID = -8946294835230325509L;

	@Id 
	@GeneratedValue 
	Long messageId;
	
	private String title;
	private String content;
	
	private Calendar angelegtAm;
	
	@ManyToOne
	@JoinColumn(name = "event_message")
	private EventModel event;
	
	public int compareTo(MessageModel compareObjekt)
	{
		if(getErstellungsdatum().getTimeInMillis() < compareObjekt.getErstellungsdatum().getTimeInMillis())
			return -1;
		else if(getErstellungsdatum() == compareObjekt.getErstellungsdatum())
			return 0;
		else 
			return 1;
	}
	public MessageModel()
	{
		angelegtAm = Calendar.getInstance();
	}
	public MessageModel(String title, String content)
	{
		this.title = title;
		this.content = content;
		angelegtAm = Calendar.getInstance();
	}

	public Calendar getErstellungsdatum()
	{
		return angelegtAm;
	}
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return content;
	}
	
	public void setDescription(String description) {
		this.content = description;
	}
	public void setIstBestandteilVonEvent(EventModel event)
	{
		this.event = event;
	}
	public EventModel getIstBestandteilVonEvent()
	{
		return event;
	}
}
