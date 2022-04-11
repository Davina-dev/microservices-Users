package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.User;
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
        User user = new User();

        Mockito.when((User)this.userRepository.save(user)).thenReturn(user);

        UserServiceImpl userService = new UserServiceImpl(this.userRepository);
        User sut = userService.save(user);
        assertEquals(sut, user);
    }

    @Test
    void TestUserServiceCanDeleteAUser() {
        User user = new User();

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        var userService = new UserServiceImpl(userRepository);
        var sut= userService.delete(1);

        Assertions.assertTrue(sut);
    }

    @Test
    void TestUserServiceCanGetUserById() {
       User user = new User();
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        //user.setName("Davina");
        //user.setId(1);
        //user.setSurname("Medina");
        //user.setBirthdate(LocalDate.parse("1997-02.03"));


        var userService = new UserServiceImpl( userRepository);
        var sut = userService.getById(1);

        assertEquals(sut, user);
    }

    @Test
    void UserServiceCantUpdateAProduct() {
        User user = new User();
        user.setName("Davina");
        user.setId(1);

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        var userService = new UserServiceImpl(userRepository);
        var sut = userService.save(user);

        assertEquals(sut, user);
    }



}