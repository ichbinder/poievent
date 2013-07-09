package de.htw_berlin.f4.ai.kbe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity 
public class UserModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue 
	Long userId;
	
	private String name;
	
	private String firstname;
	
	private String email;
	
	private Boolean admin;
	
	@OneToOne
	private PoiModel poi;
	
	public UserModel(){
	}
	
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
}
