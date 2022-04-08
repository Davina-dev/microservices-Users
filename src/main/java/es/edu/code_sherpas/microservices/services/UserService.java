package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


public interface UserService{
    public UserDTO getUserById(Integer id);



}
