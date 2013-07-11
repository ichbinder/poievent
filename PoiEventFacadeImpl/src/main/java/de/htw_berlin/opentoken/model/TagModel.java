package de.htw_berlin.opentoken.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TagModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1291742894137432368L;

	@Id 
	@GeneratedValue 
	Long tagId;
	
	public TagModel() {
		// TODO Auto-generated constructor stub
	}
	
	private String tag;
	
	@ManyToOne
	private PoiModel poi;
	
	public TagModel(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getTagId() {
		return tagId;
	}
}
