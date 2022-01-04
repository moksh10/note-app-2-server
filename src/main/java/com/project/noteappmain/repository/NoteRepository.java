package com.project.noteappmain.repository;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.noteappmain.entities.Note;
import com.project.noteappmain.entities.User;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {
	List<Note> findNotesByUser(User user);
}
