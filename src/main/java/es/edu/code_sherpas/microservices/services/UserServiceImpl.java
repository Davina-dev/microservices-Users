package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.Users;
import es.edu.code_sherpas.microservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
    }


    @Override
    public Users saveUser(Users users) {
        return userRepository.save(users);
    }


    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }

    @Override
    public boolean delete (Integer id){
        userRepository.deleteById(id);
        return false;
    }

}
