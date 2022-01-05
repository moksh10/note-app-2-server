package com.project.noteappmain.handlers;

@SuppressWarnings("serial")
public class NoteNotFoundException extends RuntimeException{

	public NoteNotFoundException() {
		// TODO Auto-generated constructor stub
		super("Note not found");
	}
}
