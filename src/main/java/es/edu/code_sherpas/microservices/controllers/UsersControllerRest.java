package es.edu.code_sherpas.microservices.controllers;

import es.edu.code_sherpas.microservices.modelo.AccountDTO;
import es.edu.code_sherpas.microservices.modelo.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users") //unifica rutas para no repetir
public class UsersControllerRest {


    @GetMapping ("/{id}") //enpoint dnd solo retorna info sin alterar el modelo del esquema q yo tengo de datos
    public ResponseEntity <UserDTO> getUserById(@PathVariable Integer id){ //desde el cliente me trae el id y se empareja con mi id
        System.out.println("Recovery user by id");

        UserDTO userDTO = new UserDTO(1, "Davina");
        userDTO.setSurname("Medina");
        userDTO.setEmail("davina@davina.com");
        userDTO.setBirthdate("13/04/1991");

        return ResponseEntity.ok(userDTO); //nos convierte nuestro objeto java en un json resultante en un codigo httl ok
    }

    @GetMapping //a diferencia del anterior, este no recibe argumento id
    public ResponseEntity<List <UserDTO>> listAllUsers(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String surname,
                                                       @RequestParam(required = false) String birthdate)
    {

        List<UserDTO> list = List.of(new UserDTO(1, "Davina"),
                                     new UserDTO(2, "Eida"),
                                     new UserDTO(3, "Antonio"));

        list = list.stream().filter(u -> u.getName().contains(name)).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    //getMapping de los usuatios pero con algunos filtros añadidos
    // creacion del usuario, ahora ya si que altero el modelo. Usamos el Post xq creo un recurso nuevo

    @PostMapping
    public ResponseEntity <String> createUser(@RequestBody UserDTO userDTO){ //la info la atrapo en forma de UserDTO por parametro. Transforma el json en userDTO
        System.out.println("Creating user" + userDTO.getName());

        // recupera el uri location y estamos indicando con el id path q va a ser el parametro q va a corresponder con el id del objeto
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();

        return ResponseEntity.created(location).build(); //postman: devuelve 201 y en header el location

    }


    //enpoint actualizar usuario

    @PutMapping //edita, altera un recurso
    // PATH es para modificar parcialmente un recurso. solo modifica un atributo del user y no tod el user entero
    public ResponseEntity<UserDTO > updateUser(@RequestBody UserDTO userDTO){
        System.out.println("Updating data");
        //buscar user by id
        //update
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){ //responseentity no devuelve nada asi q usamos el obj void
        System.out.println("Delete user by id");

        return ResponseEntity.ok(null);
    }

//anidacion de recursos
    @GetMapping("/{id}/accounts")
	public ResponseEntity <List<AccountDTO>> getUserAccount(@PathVariable Integer id) {

        List<AccountDTO> list = List.of(new AccountDTO("google"), new AccountDTO("twitter"), new AccountDTO("GitHub"));

        return ResponseEntity.ok(list);
	}
//necesito solo una cuenta por id
    @GetMapping("/{id}/accounts/{idAccount}")
    public ResponseEntity<AccountDTO> getUserAccountById(@PathVariable Integer id, @PathVariable Integer idAccount) {

        return ResponseEntity.ok(new AccountDTO("google"));
    }

}
