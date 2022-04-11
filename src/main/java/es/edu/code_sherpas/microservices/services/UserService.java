package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.User;

import java.util.List;
import java.util.Optional;


public interface UserService{

    public User saveUser(User userDTO);

    public List<User> getAllUsers();

    public Optional<User> getById(Integer id);

    public User save (User userDTO);

    public boolean delete (Integer id);

}
