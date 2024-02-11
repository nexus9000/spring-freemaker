package edu.itstep.notes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.itstep.notes.domain.User;
import edu.itstep.notes.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository useRepository;

	public List<User> listAll() {
		return useRepository.findAll();
	}

	public void save(User user) {
		useRepository.save(user);
	}

	public User get(Long id) {
		return useRepository.findById(id).get();
	}

	public void delete(Long id) {
		useRepository.deleteById(id);
	}

}
