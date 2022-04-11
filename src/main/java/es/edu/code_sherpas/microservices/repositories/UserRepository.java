package es.edu.code_sherpas.microservices.repositories;

import es.edu.code_sherpas.microservices.modelo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository <Users, Integer> {

}
