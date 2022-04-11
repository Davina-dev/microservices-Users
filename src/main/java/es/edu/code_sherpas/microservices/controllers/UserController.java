package es.edu.code_sherpas.microservices.controllers;

import es.edu.code_sherpas.microservices.modelo.User;
import es.edu.code_sherpas.microservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/users")
//@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public String add(@Valid @RequestBody User user){
        System.out.println("Creating user " + user.getName());
        userService.saveUser(user);
        return "new user is added";
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id)
    {
        Optional<User> optUser = userService.getById(id);
        try {
            Optional<User> user = userService.getById(id);
            return new ResponseEntity<User>(HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update (@RequestBody User user, @PathVariable Integer id){
        System.out.println("updating data");
        try{
            Optional existingUsers=userService.getById(id);
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public String delete (@PathVariable Integer id){
        System.out.println("delete user by id");
        userService.delete(id);
        return "Removed user with id: "+id;
    }


}
