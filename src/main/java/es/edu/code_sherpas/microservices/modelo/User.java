package es.edu.code_sherpas.microservices.modelo;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @Size(min =1, max=64) //validator
    @Column(name="last_name")
    private String surname;
    @Email //validator
    private String email;
    private LocalDate birthdate;


}
