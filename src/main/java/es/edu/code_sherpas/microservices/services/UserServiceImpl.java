//trae desde la bbdd
package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.repositories.UserRepository;
import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
	public class UserServiceImpl implements  UserService{

		@Autowired
		private  UserRepository userRepository;

		public Optional<UserDTO> getUserById(Integer id) {
			return userRepository.findById(id);
		}

		@Override
		public List<UserDTO> listAllUsers() {
			return userRepository.findAll();
		}

		@Override
		public UserDTO saveUser(UserDTO userDTO) {
			return userRepository.save(userDTO);
		}

		@Override
		public void deleteById(Integer id) {
			userRepository.deleteById(id);
		}
}
