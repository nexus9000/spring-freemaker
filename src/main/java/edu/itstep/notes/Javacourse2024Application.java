package edu.itstep.notes;


import java.util.List;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import edu.itstep.notes.domain.Notes;
import edu.itstep.notes.services.NotesService;
import lombok.extern.slf4j.Slf4j;




@SpringBootApplication
@Slf4j
public class Javacourse2024Application {

	public static void main(String[] args) {
		SpringApplication.run(Javacourse2024Application.class, args);
	}
	@Bean
	public CommandLineRunner run(NotesService note) {
		return (String[] arg) -> {
			List<Notes> notes = note.listAll();
			if(notes.size() == 0) {
			 Notes noteObject = new Notes("123@","message notes");	
			 note.save(noteObject);
			 log.info("default note added");
			}
		};
	}
}
