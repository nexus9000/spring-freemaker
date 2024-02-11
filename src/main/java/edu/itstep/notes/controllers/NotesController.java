package edu.itstep.notes.controllers;

import java.util.List;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.itstep.notes.Javacourse2024Application;
import edu.itstep.notes.domain.Notes;
import edu.itstep.notes.domain.User;
import edu.itstep.notes.services.NotesService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class NotesController {
	@Autowired
	private NotesService notesService;
   
	@GetMapping("/notes")
	public ResponseEntity<List<Notes>> listAllNotes() {
		List<Notes> note = this.notesService.listAll();
		return new ResponseEntity<List<Notes>>(note, HttpStatus.OK);
	}
}
