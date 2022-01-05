package com.project.noteappmain.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.noteappmain.entities.Note;
import com.project.noteappmain.entities.User;
import com.project.noteappmain.handlers.NoteNotFoundException;
import com.project.noteappmain.handlers.UserNotFoundException;
import com.project.noteappmain.repository.NoteRepository;
import com.project.noteappmain.repository.UserRepository;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public Note saveNote(int userId,Note note) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepository.findById(userId);
		if(!user.isPresent())
			throw new UserNotFoundException();
		note.setUser(user.get());
		return noteRepository.save(note);
	}

	@Override
	@Transactional
	public Note updateNote(int noteId, Note note) {
		// TODO Auto-generated method stub
		Optional<Note> noteToGet=noteRepository.findById(noteId);
		if(!noteToGet.isPresent())
			throw new NoteNotFoundException();
		Note noteToUpdate=noteToGet.get();
		noteToUpdate.setNoteTitle(note.getNoteTitle());
		noteToUpdate.setNoteBody(note.getNoteBody());
		return noteRepository.save(noteToUpdate);
	}

	@Override
	@Transactional
	public Note getNote(int noteId) {
		// TODO Auto-generated method stub
		
		Optional<Note> noteToGet=noteRepository.findById(noteId);
		if(!noteToGet.isPresent())
			throw new NoteNotFoundException();
		return noteToGet.get();
	}

	@Override
	@Transactional
	public void deleteNote(int noteId) {
		// TODO Auto-generated method stub
		Optional<Note> noteToGet=noteRepository.findById(noteId);
		if(!noteToGet.isPresent())
			throw new NoteNotFoundException();
		Note noteToDelete=noteToGet.get();
		noteRepository.delete(noteToDelete);
	}

	@Override
	@Transactional
	public List<Note> getAllNotes(int userId) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepository.findById(userId);
		if(!user.isPresent())
			throw new UserNotFoundException();
		return noteRepository.findNotesByUser(user.get());
	}

}
