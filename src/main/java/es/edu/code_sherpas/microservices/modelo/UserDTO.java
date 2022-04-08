package es.edu.code_sherpas.microservices.modelo;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.RepresentationModel; // para que sea navegable: extends RepresentationModel

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@EqualsAndHashCode (callSuper = true) //estamos llamando a los path
@NoArgsConstructor
@RequiredArgsConstructor

public class UserDTO extends RepresentationModel<UserDTO> {

    @NonNull
    @NotNull //validatos
    private int id;
    @NonNull
    @NotBlank  //validator (al menos  1 caracter)
    private String name;
    @Size(min =5, max=20) //validator
    private String surname;
    @Email //validator
    private String email;
    @Past //validator
    private LocalDate birthdate;



}
