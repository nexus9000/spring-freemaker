package edu.itstep.notes.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.itstep.notes.NoteProperties;
import edu.itstep.notes.domain.Notes;
import edu.itstep.notes.services.NotesService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TextNoteController {

	@Autowired
	private NotesService notesService;
	@Autowired
	private NoteProperties properties;
	private Parser parser = Parser.builder().build();
	private HtmlRenderer renderer = HtmlRenderer.builder().build();

	@GetMapping("/")
	public String index(Model model) {
		getNotes(model);
		return "index";
	}

	private void getNotes(Model model) {
		List<Notes> notes = notesService.listAll();
		Collections.reverse(notes);
		model.addAttribute("notes", notes);
	}

	private void saveNote(String description, Model model) {
		if (description != null && !description.trim().isEmpty()) {
			Node document = parser.parse(description.trim());
			String html = renderer.render(document);
			notesService.save(new Notes(null, html));
			model.addAttribute("decription", "");
		}
	}

	@PostMapping("/note")
	public String saveNotes(@RequestParam("image") MultipartFile file, @RequestParam String description,
			@RequestParam(required = false) String publish, @RequestParam(required = false) String upload, Model model)
			throws IOException {
		if (publish != null && publish.equals("Publish")) {
			saveNote(description, model);
			getNotes(model);
			return "redirect:/";
		}
		if (upload != null && upload.equals("Upload")) {
			if (file != null && file.getOriginalFilename() != null) {
				uploadImage(file, description, model);
			}
			getNotes(model);
			return "index";
		}
		return "index";
	}

	private void uploadImage(MultipartFile file, String description, Model model) throws IOException {
		File uploadDir = new File(properties.getUploadDir());
		if (!uploadDir.exists()) {
			log.info(uploadDir + " was created!");
			uploadDir.mkdir();
		}
		String fileId = UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];
		file.transferTo(new File(fileId));
		model.addAttribute("description", description + "![]/uploads" + fileId + ")");
	}

	@GetMapping(value = "/css/{cssFile}")
	public @ResponseBody byte[] getFile(@PathVariable("cssFile") String cssFile) throws IOException {
		InputStream in = getClass().getResourceAsStream("/static/css/" + cssFile);
		try {
			return in.readAllBytes();
		} catch (Exception e) {
			var error = new String("css not found");
			e.printStackTrace();
			return error.getBytes();
		}
	}

}
