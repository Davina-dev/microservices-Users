package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.UserDTO;

import java.util.List;


public interface UserService{

    public UserDTO saveUser(UserDTO userDTO);

    public List<UserDTO> getAllUsers();

    public UserDTO getById(Integer id);

    public UserDTO save (UserDTO userDTO);

    public boolean delete (Integer id);

}
