package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.UserDTO;
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
    public UserDTO saveUser(UserDTO userDTO) {
        return userRepository.save(userDTO);
    }


    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return userRepository.save(userDTO);
    }

    @Override
    public boolean delete (Integer id){
        userRepository.deleteById(id);
        return false;
    }

}
