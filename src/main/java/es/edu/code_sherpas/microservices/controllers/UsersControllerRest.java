package es.edu.code_sherpas.microservices.controllers;

import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersControllerRest {


    @GetMapping ("/{id}") //enpoint dnd solo retorna info sin alterar el modelo del esquema q yo tengo de datos
    public UserDTO getUserById(@PathVariable Integer id){ //desde el cliente me trae el id y se empareja con mi id
        System.out.println("Recovery user by id");

        UserDTO userDTO = new UserDTO(1, "Davina");
        userDTO.setSurname("Medina");
        userDTO.setEmail("davina@davina.com");
        userDTO.setBirthdate("13/04/1991");

        return userDTO; //nos convierte nuestro objeto java en un json resultante
    }

    @GetMapping //a diferencia del anterior, este no recibe argumento id
    public List<UserDTO> listAllUsers() {
        List<UserDTO> list = List.of(new UserDTO(1, "Davina"),
                                     new UserDTO(2, "Eida"),
                                     new UserDTO(3, "Antonio"));

        return list;
    }

    //getMapping de los usuatios pero con algunos filtros añadidos

    // creacion del usuario, ahora ya si que altero el modelo. Usamos el Post xq creo un recurso nuevo
    @PostMapping  //por buena practica se empareja con los get
    public String createUser(@RequestBody UserDTO userDTO){ //la info la atrapo en forma de UserDTO por parametro. Transforma el json en userDTO
        System.out.println("Creating user" + userDTO.getName());

        return  "http://localhost:8080/" + userDTO.getId();
    }
    //enpoint actualizar usuario
    @PutMapping //edita, altera un recurso
    // PATH es para modificar parcialmente un recurso. solo modifica un atributo del user y no tod el user entero
    public  UserDTO updateUser(@RequestBody UserDTO userDTO){
        System.out.println("Updating data");
        //buscar user by id
        //update
        return userDTO;
    }

    @DeleteMapping("/{id}")
    public void  deleteUser(@PathVariable Integer id){
        System.out.println("Delete user by id");
    }



}
