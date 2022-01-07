package com.project.noteappmain.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.noteappmain.dto.SuccessMessage;
import com.project.noteappmain.entities.Note;
import com.project.noteappmain.services.NoteServiceImpl;

@RestController
@RequestMapping(path = "/notes")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class NoteController {
	
	@Autowired
	NoteServiceImpl noteService;

	@GetMapping("/{noteId}")
	public ResponseEntity<?> getNote(@PathVariable int noteId)
	{
		
		Note note= noteService.getNote(noteId);
		return new ResponseEntity<>(new SuccessMessage("Note fetched",true,note),HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/allNotes")
	public ResponseEntity<?> getNotes(HttpServletRequest request)
	{
		int userId=(int)request.getAttribute("userId");
		List<Note> notes= noteService.getAllNotes(userId);
		return new ResponseEntity<>(new SuccessMessage("Notes fetched",true,notes),HttpStatus.ACCEPTED);
		
	}
	@PostMapping
	public ResponseEntity<?> saveNote(HttpServletRequest request,@Valid @RequestBody Note note)
	{
		int userId=(int)request.getAttribute("userId");
		Note newNote=noteService.saveNote(userId,note);
		return new ResponseEntity<>(new SuccessMessage("Note saved",true,newNote),HttpStatus.CREATED);
		
	}
	@PutMapping
	public ResponseEntity<?> updateNote(@Valid @RequestBody Note note)
	{
		Note updatedNote=noteService.updateNote(note.getNoteId(), note);
		return new ResponseEntity<>(new SuccessMessage("Note updated",true,updatedNote),HttpStatus.ACCEPTED);
		
	}
	@DeleteMapping("/{noteId}")
	public ResponseEntity<?> deleteNote(@PathVariable int noteId)
	{
		noteService.deleteNote(noteId);
		return new ResponseEntity<>(new SuccessMessage("Note deleted",true),HttpStatus.ACCEPTED);
		
	}
	
}
