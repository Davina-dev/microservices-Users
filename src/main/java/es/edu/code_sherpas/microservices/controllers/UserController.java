package es.edu.code_sherpas.microservices.controllers;

import es.edu.code_sherpas.microservices.modelo.Users;
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
    public String add(@Valid @RequestBody Users users){
        userService.saveUser(users);
        return "new user is added";
    }

    @GetMapping
    public List<Users> getAllUsers(){

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> get(@PathVariable Integer id)
    {
        try {
            Users users = userService.getById(id);
            return new ResponseEntity<Users>(users, HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> update (@RequestBody Users users, @PathVariable Integer id){
        try{
            Users existingUsers=userService.getById(id);
            userService.save(users);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public String delete (@PathVariable Integer id){
        userService.delete(id);
        return "Removed user with id: "+id;
    }


}
