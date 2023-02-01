package com.indra.notesspringboot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indra.notesspringboot.entity.Note;
import com.indra.notesspringboot.persistence.NoteRepository;

@Service
public class GestorNote {
	
	@Autowired
	private NoteRepository noteRepository;	

	@Transactional
	public void insert(Note note) {
		noteRepository.save(note);
	}

	@Transactional
	public void modify(Note note) {
		noteRepository.save(note);
	}

	@Transactional
	public void delete(Integer id) {
		Note note = noteRepository.getReferenceById(id);
		noteRepository.delete(note);
	}

	@Transactional
	public void switchFavorite(Integer id) {
		Note note = noteRepository.getReferenceById(id);
		noteRepository.switchFavorite(!note.getIsFavorite(), note.getId()); 
		
		
	}
}
