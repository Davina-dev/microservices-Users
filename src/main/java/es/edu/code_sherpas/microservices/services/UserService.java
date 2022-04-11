package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.Users;

import java.util.List;


public interface UserService{

    public Users saveUser(Users users);

    public List<Users> getAllUsers();

    public Users getById(Integer id);

    public Users save (Users users);

    public boolean delete (Integer id);

}
