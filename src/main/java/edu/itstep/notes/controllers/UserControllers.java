package edu.itstep.notes.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.itstep.notes.Javacourse2024Application;
import edu.itstep.notes.domain.User;
import edu.itstep.notes.services.UserService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserControllers {
	@Autowired
	private UserService userService;

	// URI - /api/users
	@GetMapping(value = "users")
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = this.userService.listAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// URI -/api/user/save
	@PostMapping(value = "users/save", consumes = { "application/json" })
	public ResponseEntity<User> save(@RequestBody User user) {
		this.userService.save(user);
		log.info(user.getDescription()+" was added!");
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	//URI - /api/user/delete/id/1
		@DeleteMapping("users/delete/id/{id}")
		public ResponseEntity<User> delete(@PathVariable(name="id") final Long userId){
			this.userService.delete(userId);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		@GetMapping("users/id/{id}")
		public ResponseEntity<User> getUserById(@PathVariable(name="id") Long userId){
			try {
				final User user = this.userService.get(userId);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			
			}catch(NoSuchElementException e){
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	   }
}
