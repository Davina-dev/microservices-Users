    package es.edu.code_sherpas.microservices.controllers;


    import es.edu.code_sherpas.microservices.modelo.AccountDTO;
    import es.edu.code_sherpas.microservices.modelo.UserDTO;
    import es.edu.code_sherpas.microservices.services.UserService;
    import es.edu.code_sherpas.microservices.services.UserServiceImpl;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Pageable;
    import org.springframework.hateoas.CollectionModel;
    import org.springframework.hateoas.Link;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

    import javax.validation.Valid;
    import java.net.URI;

    import java.util.List;
    import java.util.NoSuchElementException;
    import java.util.Optional;
    //navegacion de un recurso consigo mismo: los dos imports siguientes
    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

    @RestController
    @RequestMapping("/users") //unifica rutas para no repetir

    public class UsersControllerRest {

        //la capa controller tiene un obj q es la capa de servicio y es la q llama para delegar las facultades a la logica de negocio
       @Autowired
        private UserService userService;

        @GetMapping("/{id}") //enpoint dnd solo retorna info sin alterar el modelo del esquema q yo tengo de datos
        public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){ //desde el cliente me trae el id y se empareja con mi id

           System.out.println("Recovery user by id");

           Optional<UserDTO> optUserDTO = userService.getUserById(id);

            try {
                UserDTO userDTO = optUserDTO.orElseThrow(NoSuchElementException::new);
                Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
                userDTO.add(withSelfRel);
                return ResponseEntity.ok(userDTO);
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        }


        @GetMapping //a diferencia del anterior, este no recibe argumento id
        public ResponseEntity<CollectionModel<UserDTO>> listAllUsers(@RequestParam(required = false) String name,
                                                                     @RequestParam(required = false) String surname,
                                                                     Pageable pageable) {
            List<UserDTO> list = userService.listAllUsers(pageable);

            //hateos para que cada user tenga su link. y poder recuperar un usuario en concreto por su link
            for (UserDTO userDTO : list) {
                // relacion self
                Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
                userDTO.add(withSelfRel);
                //relación  accounts: nos muestra todos los links de las cuentas del usuario
                Link accountsRel = linkTo(methodOn(UsersControllerRest.class).getUserAccounts(userDTO.getId()))
                        .withRel("accounts");
                userDTO.add(accountsRel);
            }



            //hateos-> para que retorne la lista de links
            Link link = linkTo(methodOn(UsersControllerRest.class).listAllUsers("", "", pageable)).withSelfRel();
            CollectionModel<UserDTO> result = CollectionModel.of(list, link);
            return ResponseEntity.ok(result);
        }

        //getMapping de los usuatios pero con algunos filtros añadidos
        // creacion del usuario, ahora ya si que altero el modelo. Usamos el Post xq creo un recurso nuevo

        @PostMapping
        public ResponseEntity <String> createUser( @Valid @RequestBody UserDTO userDTO){ //la info la atrapo en forma de UserDTO por parametro. Transforma el json en userDTO
            System.out.println("Creating user " + userDTO.getName());

           userDTO = userService.saveUser(userDTO);
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
        public ResponseEntity<UserDTO > updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer id){
            System.out.println("Updating data");
            try{
               Optional <UserDTO> existingUser =userService.getUserById(id);
               userService.saveUser(userDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            }catch (NoSuchElementException e){
                return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Integer id){ //responseentity no devuelve nada asi q usamos el obj void
            System.out.println("Delete user by id");
            userService.deleteById(id);
            return ResponseEntity.ok(null);
        }

    //anidacion de recursos
        @GetMapping("/{id}/accounts")
        public ResponseEntity <List<AccountDTO>> getUserAccounts(@PathVariable Integer id) {

            List<AccountDTO> list = List.of(new AccountDTO("google"), new AccountDTO("twitter"), new AccountDTO("GitHub"));

            return ResponseEntity.ok(list);
        }
    //necesito solo una cuenta por id
        @GetMapping("/{id}/accounts/{idAccount}")
        public ResponseEntity<AccountDTO> getUserAccountById(@PathVariable Integer id, @PathVariable Integer idAccount) {

            return ResponseEntity.ok(new AccountDTO("google"));
        }

    }



