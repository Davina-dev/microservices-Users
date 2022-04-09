package es.edu.code_sherpas.microservices.dao.repositories;

import es.edu.code_sherpas.microservices.dao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



    @Repository
    public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    }
