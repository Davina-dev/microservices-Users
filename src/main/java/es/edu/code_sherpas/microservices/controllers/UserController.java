package es.edu.code_sherpas.microservices.controllers;

import es.edu.code_sherpas.microservices.modelo.UserDTO;
import es.edu.code_sherpas.microservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public String add(@Valid @RequestBody UserDTO userDTO){
        userService.saveUser(userDTO);
        return "new user is added";
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Integer id)
    {
        try {
            UserDTO userDTO = userService.getById(id);
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update (@RequestBody UserDTO userDTO, @PathVariable Integer id){
        try{
            UserDTO existingUsers=userService.getById(id);
            userService.save(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public String delete (@PathVariable Integer id){
        userService.delete(id);
        return "Removed user with id: "+id;
    }


}
