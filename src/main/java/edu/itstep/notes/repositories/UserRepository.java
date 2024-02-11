package edu.itstep.notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.itstep.notes.domain.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
