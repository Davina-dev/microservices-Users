package es.edu.code_sherpas.microservices.repositories;

import es.edu.code_sherpas.microservices.modelo.UserDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {
    /*
	jpa... interfaz para hacer crud de aquella identidad q yo le suministre x ese parametro user dto y de q tipo sera esa entidad (id)
	guardar y levantar  usuarios de la bbdd
	Lo usaremso desde service
	*/


}

