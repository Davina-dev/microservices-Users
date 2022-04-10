//trae desde la bbdd
package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.repository.UserRepository;
import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{

	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
	}

	public Optional<UserDTO> getUserById(Integer id) {

		Optional<UserDTO> userDTO = userRepository.findById(id);
		return userDTO;
	}

	@Override
	public List<UserDTO> listAllUsers(Pageable pageable) {

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
