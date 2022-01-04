package com.project.noteappmain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="note_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","notes"})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private int userId;
	
	@Column(name="email")
	@Email(message = "Not a valid email")
	@NotEmpty(message = "User email required")
	@Size(max=200,message = "Email length exceeded: 200")
	private String email;
	
	@Column(name="password")
	@NotEmpty(message = "Password required")
	@Size(min=8,message = "Password must be minimum of 8 characters")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name="user_name")
	@NotEmpty(message = "Username cannot be empty")
	@Size(max=200,message = "Username length exceeded: 200")
	private String userName;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Note> notes;

	public User() {
		
	}
	public User(String email, String password,String userName) {
		this.email = email;
		this.password = password;
		this.userName = userName; 
	}

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "User [email=" + email + "]";
	}
	
}
