package com.project.noteappmain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "note")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","user"})
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="note_id")
	private int noteId;
	
	@Column(name="note_title")
	@NotEmpty(message = "Note title required")
	@Size(max=200,message = "Maximum length of note title exceeded")
	private String noteTitle;
	
	@Column(name="note_body")
	@Size(max=3500,message = "Maximum length of note body exceeded")
	private String noteBody;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;

	
	public Note() {
		
	}
	public Note(String noteTitle, String noteBody, User user) {
		this.noteTitle = noteTitle;
		this.noteBody = noteBody;
		this.user = user;
	}

	public int getNoteId() {
		return noteId;
	}

	
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteBody() {
		return noteBody;
	}

	public void setNoteBody(String noteBody) {
		this.noteBody = noteBody;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Note [noteTitle=" + noteTitle + ", noteBody=" + noteBody + ", userId=" + user + "]";
	}
	
	
	
}
