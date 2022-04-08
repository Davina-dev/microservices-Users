package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


public interface UserService{
    public Optional<UserDTO> getUserById(Integer id);

    public  List<UserDTO> listAllUsers();

    public UserDTO saveUser(UserDTO userDTO);

    public  void  deleteById(Integer id);

}
