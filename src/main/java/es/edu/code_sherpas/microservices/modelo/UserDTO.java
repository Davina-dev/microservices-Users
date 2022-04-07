package es.edu.code_sherpas.microservices.modelo;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    @NonNull
    private int id;
    @NonNull
    private String name;
    private String surname;
    private String email;
    private String birthdate;





}
