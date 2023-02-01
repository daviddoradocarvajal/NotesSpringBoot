package com.indra.notesspringboot.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.indra.notesspringboot.entity.Note;
import com.indra.notesspringboot.model.GestorNote;
import com.indra.notesspringboot.persistence.NoteRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class NotesController {

	@Autowired
	GestorNote noteService;

	@Autowired
	NoteRepository noteRepository;

	public NotesController() {
		System.out.println("Creando controller");
	}

	@GetMapping(path = "/listNotes", headers = "Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> listNotes() {
		System.out.println("POR AQUI");
		List<Note> noteList = noteRepository.findAll();		
		String listString = writeListToJsonArray(noteList);
		return ResponseEntity.ok(listString);

	}
	
	@PostMapping(path = "/insertNote", headers = "Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> insertNote(@RequestBody String json) {		
		Note note = jsonNoteReturner(json);
		note.setTimestamp(LocalDateTime.now());		
		noteService.insert(note);
		String jsonStr = jsonTransformer(note);
		return ResponseEntity.ok(jsonStr);
	}
	@PostMapping(path = "/modifyNote", headers = "Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> modifyNote(@RequestBody String json) {
		Note note = jsonNoteReturner(json);		
		note.setTimestamp(LocalDateTime.now());
		noteService.modify(note);
		String jsonStr = jsonTransformer(note);
		return ResponseEntity.ok(jsonStr);
	}
	@PostMapping(path = "/deleteNote", headers = "Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> deleteNote(@RequestBody String id) {
		Integer idToDelete = Integer.valueOf(id);		
		noteService.delete(idToDelete);		
		return ResponseEntity.ok("deleted");
	}
	@PostMapping(path = "/switchNoteFavorite", headers = "Accept=*/*", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> switchNoteFavorite(@RequestBody String id) {
		Integer idToSwitch = Integer.valueOf(id);
		noteService.switchFavorite(idToSwitch);
		return ResponseEntity.ok("ok");
	}
	

	private static String writeListToJsonArray(List<Note> list) {
		try {
			final StringWriter sw = new StringWriter();
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.writeValue(sw, list);
			return sw.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	private static String jsonTransformer(Object object) {
		String jsonStr = "";

		try {
			ObjectMapper obj = new ObjectMapper();
			obj.registerModule(new JavaTimeModule());
			jsonStr = obj.writeValueAsString(object);			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return jsonStr;
	}
	
	private static Note jsonNoteReturner(String json) {	

		try {
			ObjectMapper obj = new ObjectMapper();
			obj.registerModule(new JavaTimeModule());
			Note note = obj.readValue(json, Note.class);			
			return note;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
}
