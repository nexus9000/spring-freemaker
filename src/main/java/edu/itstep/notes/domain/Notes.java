package edu.itstep.notes.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notes {
	@Id
	private String id;
	
	private String content;
	
	@Override
	public String toString() {
		return content;
	}
}
