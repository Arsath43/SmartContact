package com.smart.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="CONTACT")
public class contact {
	
	@Id
	@GeneratedValue(strategy =   GenerationType.AUTO)
	private int CID;
	
	private String Name;

	private String email;
	private String Secondname;
	

	private String work;
	@Column(length=500)
	private String description;
	
	
	private String Image;
	
	private String phone;
	
	@ManyToOne
	private User user;
	
	public int getCID() {
		return CID;
	}
	public void setCID(int cID) {
		CID = cID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSecondname() {
		return Secondname;
	}
	public void setSecondname(String secondname) {
		Secondname = secondname;
	}

	public String getWork() {
		return work;
	}
//	
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	

}
