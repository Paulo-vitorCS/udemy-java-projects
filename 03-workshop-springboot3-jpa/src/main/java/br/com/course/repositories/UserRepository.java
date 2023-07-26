package br.com.course.repositories;

import br.com.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long> {



}
