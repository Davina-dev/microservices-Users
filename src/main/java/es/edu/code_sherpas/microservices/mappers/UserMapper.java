package es.edu.code_sherpas.microservices.mappers;

import es.edu.code_sherpas.microservices.dao.entities.UserEntity;
import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {  
	
	public UserEntity getUserEntity(UserDTO userDTO);
	
	public UserDTO getUserDTO(UserEntity userEntity);
	
	public List<UserDTO> getUsersDtos(List<UserEntity> usersEntities);
	
	
}

