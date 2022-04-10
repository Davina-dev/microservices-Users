    package es.edu.code_sherpas.microservices.controllers;


    import es.edu.code_sherpas.microservices.modelo.AccountDTO;
    import es.edu.code_sherpas.microservices.modelo.UserDTO;
    import es.edu.code_sherpas.microservices.services.UserService;
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
    import java.util.stream.Collectors;
    //navegacion de un recurso consigo mismo: los dos imports siguientes
    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

    @RestController
    @RequestMapping("/users") //unifica rutas para no repetir

    public class UsersControllerRest {

       @Autowired
       private UserService userService;

        @GetMapping("/{id}")
        public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){

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


        @GetMapping
        public ResponseEntity<CollectionModel<UserDTO>> listAllUsers(@RequestParam(required = false) String name,
                                                                     @RequestParam(required = false) String surname,
                                                                     int id, Pageable pageable) {
            List<UserDTO> list = userService.listAllUsers(pageable);
                //hateos
            for (UserDTO userDTO : list) {
                // relacion self
                Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
                userDTO.add(withSelfRel);
                //relación  accounts: nos muestra todos los links de las cuentas del usuario
                Link accountsRel = linkTo(methodOn(UsersControllerRest.class).getUserAccounts(userDTO.getId()))
                        .withRel("accounts");
                userDTO.add(accountsRel);
            }
            if (name != null) {
                list = list.stream().filter(u -> u.getName().contains(name)).collect(Collectors.toList());
            }
            //hateos (retorne la lista de links)
            Link link = linkTo(methodOn(UsersControllerRest.class).listAllUsers("", "", 0,pageable)).withSelfRel();
            CollectionModel<UserDTO> result = CollectionModel.of(list, link);
            return ResponseEntity.ok(result);

        }



        @PostMapping
        public ResponseEntity <String> createUser( @Valid @RequestBody UserDTO userDTO){

            System.out.println("Creating user " + userDTO.getName());

           userDTO = userService.saveUser(userDTO);

            // id path  corresponder con el id del objeto
            URI location = ServletUriComponentsBuilder.
                    fromCurrentRequest().path("/{id}")
                    .buildAndExpand(userDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }



        @PutMapping
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
        public ResponseEntity<UserDTO> delete(@PathVariable Integer id){ //responseentity no devuelve nada asi q usamos el obj void
            System.out.println("delete user by id");

            userService.deleteById(id);

            return ResponseEntity.ok(null);
        }



    //anidacion de recursos (añadido)

        @GetMapping("/{id}/accounts")
        public ResponseEntity <List<AccountDTO>> getUserAccounts(@PathVariable Integer id) {

            List<AccountDTO> list = List.of(new AccountDTO("google"), new AccountDTO("twitter"), new AccountDTO("GitHub"));

            return ResponseEntity.ok(list);
        }

        @GetMapping("/{id}/accounts/{idAccount}")
        public ResponseEntity<AccountDTO> getUserAccountById(@PathVariable Integer id, @PathVariable Integer idAccount) {

            return ResponseEntity.ok(new AccountDTO( "google"));
        }

    }



