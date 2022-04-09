//trae desde la bbdd
package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.dao.entities.UserEntity;
import es.edu.code_sherpas.microservices.dao.repositories.UserRepository;
import es.edu.code_sherpas.microservices.mappers.UserMapper;
import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
	public class UserServiceImpl implements  UserService{
	private UserMapper userMapper;

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository,UserMapper userMapper) {

		this.userRepository = userRepository;
		this.userMapper = userMapper;

	}

	public Optional<UserDTO> getUserById(Integer id) {

		Optional<UserEntity> userOptional = userRepository.findById(id);

		if (userOptional.isPresent()) {
			UserEntity userEntity = userOptional.get();
			UserDTO userDTO = userMapper.getUserDTO(userEntity);
			return Optional.of(userDTO);
		}

		return Optional.empty();

	}

	@Override
	public List<UserDTO> listAllUsers(Pageable pageable) {

		Page<UserEntity> usersEntitiesPage = userRepository.findAll(pageable);
		List<UserEntity> userEntititesList = usersEntitiesPage.getContent();
		List<UserDTO> usersDtos = userMapper.getUsersDtos(userEntititesList);

		//Minima logica de negocios.
		//Esta logica es la que se somete a testing en UserServiceImplTest
		//usersDtos.forEach(user -> user.setTitle("Developer " + user.getName()));

		return usersDtos;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) {

		UserEntity userEntity = userMapper.getUserEntity(userDTO);
		userEntity = userRepository.save(userEntity);

		return userDTO;
	}

	@Override
	public void deleteById(Integer id) {

		userRepository.deleteById(id);

	}

}
