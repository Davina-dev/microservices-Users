package es.edu.code_sherpas.microservices.services;

import es.edu.code_sherpas.microservices.modelo.UserDTO;
import es.edu.code_sherpas.microservices.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;
import java.util.Optional;



@SpringBootTest
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    private static List<UserDTO> usersDtos;

    private static PodamFactory factory;

    private static Pageable pageable;

    @InjectMocks
	private UserServiceImpl userServiceImpl;
    UserServiceImplTest() {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void UserServiceCanCreateAUser() {
        UserDTO product = new UserDTO();
        UserDTO userDTO = new UserDTO();
        Mockito.when((UserDTO)this.userRepository.save(product)).thenReturn(userDTO);

       UserServiceImpl productService = new UserServiceImpl(this.userRepository);
        UserDTO sut = productService.saveUser(product);
        Assertions.assertEquals(sut, product);
    }

    @Test
    void UserServiceCanDeleteAUser() {
        UserDTO userDTO = new UserDTO();
        Mockito.when(this.userRepository.findById(1)).thenReturn(Optional.of(userDTO));
        UserServiceImpl productService = new UserServiceImpl(this.userRepository);
        boolean sut = productService.deleteById(1);
        Assertions.assertTrue(sut);
    }

    @Test
    void ProductServiceCantDeleteWhenProductDoesNotExist() {
        Mockito.when(this.userRepository.findById(1)).thenReturn(Optional.empty());
       UserServiceImpl productService = new UserServiceImpl(this.userRepository);
        userServiceImpl.deleteById(1);
        Assertions.assertAll();
    }


}
