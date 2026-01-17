package com.smart.entities;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy =   GenerationType.AUTO)
	private int ID;
	
	@NotBlank(message ="Name field is Required")
	@Size(min=3 ,max=16 ,message="min 3 and max 16 characters can be allowed!!" )
	private String name;
	@Column(unique= true)
	private String email;

	private String Password;
	private String role;
	@Column(length=500)
	private String about;
	private String Imageurl;
	private boolean enabled;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="user")
	private List<contact> contacts = new ArrayList<>();

	public List<contact> getContacts() {
		return contacts;
	}


	public void setContacts(List<contact> contacts) {
		this.contacts = contacts;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
	this.	name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public String getImageurl() {
		return Imageurl;
	}


	public void setImageurl(String imageurl) {
		Imageurl = imageurl;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	@Override
	public String toString() {
		return "User [ID=" + ID + ", Name=" + name + ", Email=" + email + ", Password=" + Password + ", role=" + role
				+ ", about=" + about + ", Imageurl=" + Imageurl + ", enabled=" + enabled + ", contacts=" + contacts
				+ "]";
	}
	

}
