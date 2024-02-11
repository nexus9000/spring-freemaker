package edu.itstep.notes.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import edu.itstep.notes.domain.Notes;

public interface NotesRepository extends MongoRepository<Notes,String>{

}
