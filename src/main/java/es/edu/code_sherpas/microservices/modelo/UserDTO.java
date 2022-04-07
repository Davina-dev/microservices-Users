package es.edu.code_sherpas.microservices.modelo;

import lombok.*;
import org.springframework.hateoas.RepresentationModel; // para que sea navegable: extends RepresentationModel

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {

    @NonNull
    private int id;
    @NonNull
    private String name;
    private String surname;
    private String email;
    private String birthdate;





}
