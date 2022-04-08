package es.edu.code_sherpas.microservices.services;

//trae desde la bbdd
import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements  UserService{
public UserDTO getUserById(Integer id) {

		return  new UserDTO(1, "Davina desde capa de negocio");
		
	}
	

}
