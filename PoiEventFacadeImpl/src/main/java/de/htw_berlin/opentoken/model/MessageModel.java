package de.htw_berlin.opentoken.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MessageModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8946294835230325509L;

	@Id 
	@GeneratedValue 
	Long messageId;
	
	private String title;
	private String content;
	
	@ManyToOne
	private EventModel event;
	
	public MessageModel(String title, String content)
	{
		this.title = title;
		this.content = content;
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
	
}
