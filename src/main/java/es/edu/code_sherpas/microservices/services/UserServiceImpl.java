package es.edu.code_sherpas.microservices.services;

//trae desde la bbdd
import es.edu.code_sherpas.microservices.dao.repositories.UserRepository;
import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
	public class UserServiceImpl implements  UserService{
	//hace una instancia de la interfaz repository
		@Autowired
		private  UserRepository userRepository;                 //llamamos a capa inferior de repository

		public Optional<UserDTO> getUserById(Integer id) {
			Optional<UserDTO> userDTO = userRepository.findById(id);
			return userDTO;
		}

		@Override
		public List<UserDTO> listAllUsers() {
			List<UserDTO> users= userRepository.findAll();
			return users;
		}

		@Override
		public UserDTO saveUser(UserDTO userDTO) {
			userDTO = userRepository.save(userDTO);
			return userDTO;
		}

		@Override
		public void deleteById(Integer id) {
			userRepository.deleteById(id);
		}
}
