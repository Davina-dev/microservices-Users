package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.Users;
import es.edu.code_sherpas.microservices.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class UserServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @Mock
    UserRepository userRepository;



    @Test
    void TestUserServiceCanCreateAUser() {
        Users users = new Users();

        Mockito.when((Users)this.userRepository.save(users)).thenReturn(users);

        UserServiceImpl userService = new UserServiceImpl(this.userRepository);
        Users sut = userService.save(users);
        assertEquals(sut, users);
    }

    @Test
    void UserServiceCanDeleteAUser() {
        Users users = new Users();

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(users));

        var userService = new UserServiceImpl(userRepository);
        var sut= userService.delete(1);

        Assertions.assertTrue(sut);
    }

    @Test
    void TestUserServiceCanGetUserById() {
       Users product = new Users();
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(product));

        var productService = new UserServiceImpl( userRepository);
        var sut = productService.getById(1);

        assertEquals(sut, product);
    }
    //@Test
    //void ProductServiceCantGetAllProducts() {
    //   var listProducts = List.of(new UserDTO(), new UserDTO());
    //  Mockito.when(userRepository.findAll()).thenReturn(...);
    //  var userService = new UserServiceImpl(userRepository);
    //  var sut = userService.getAll(...);
    //  assertEquals(sut, ...);
    //}

    @Test
    void ProductServiceCantUpdateAProduct() {
        Users users = new Users();


        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(users));
        Mockito.when(userRepository.save(users)).thenReturn(users);

        users.setName("Davina");
        users.setId(1);

        var userService = new UserServiceImpl(userRepository);

        var sut = userService.save(users);

        assertEquals(sut, users);
    }



}