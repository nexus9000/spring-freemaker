package edu.itstep.notes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.itstep.notes.domain.Notes;
import edu.itstep.notes.domain.User;
import edu.itstep.notes.repositories.NotesRepository;

@Service

public class NotesService {

	@Autowired
	private NotesRepository notesRepository;

	public List<Notes> listAll() {
		return notesRepository.findAll();
	}
	public void save(Notes note) {
		notesRepository.save(note);
	}
}
