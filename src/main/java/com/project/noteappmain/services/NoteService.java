package com.project.noteappmain.services;

import java.util.List;

import com.project.noteappmain.entities.Note;

public interface NoteService {
	public Note saveNote(int userId,Note note);
	public Note updateNote(int noteId,Note note);
	public Note getNote(int noteId);
	public void deleteNote(int noteId);
	public List<Note> getAllNotes(int userId);
}
