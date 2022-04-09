package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService{
    public Optional<UserDTO> getUserById(Integer id);

    public  List<UserDTO> listAllUsers(Pageable pageable);

    public UserDTO saveUser(UserDTO userDTO);

    public  void  deleteById(Integer id);

}
