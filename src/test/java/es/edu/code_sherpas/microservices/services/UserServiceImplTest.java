package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.UserDTO;
import es.edu.code_sherpas.microservices.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
        UserDTO userDTO = new UserDTO();

        Mockito.when((UserDTO)this.userRepository.save(userDTO)).thenReturn(userDTO);

        UserServiceImpl userService = new UserServiceImpl(this.userRepository);
        UserDTO sut = userService.save(userDTO);
        assertEquals(sut, userDTO);
    }

    @Test
    void UserServiceCanDeleteAUser() {
        UserDTO userDTO = new UserDTO();

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(userDTO));

        var userService = new UserServiceImpl(userRepository);
        var sut= userService.delete(1);

        Assertions.assertTrue(sut);
    }

    @Test
    void TestUserServiceCanGetUserById() {
       UserDTO product = new UserDTO();
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
        UserDTO userDTO = new UserDTO();


        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(userDTO));
        Mockito.when(userRepository.save(userDTO)).thenReturn(userDTO);

        userDTO.setName("Davina");
        userDTO.setId(1);

        var userService = new UserServiceImpl(userRepository);

        var sut = userService.save(userDTO);

        assertEquals(sut, userDTO);
    }



}