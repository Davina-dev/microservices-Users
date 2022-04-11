package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.User;
import es.edu.code_sherpas.microservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
    }


    @Override
    public User saveUser(User user) {

        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Integer id) {
        User user= new User();
        return  Optional.ofNullable(user);
       // return userRepository.findById(id).get();
    }

    @Override
    public User save(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public boolean delete (Integer id){
        userRepository.deleteById(id);
        return false;
    }

}
