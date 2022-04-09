package es.edu.code_sherpas.microservices.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
//capa de obj de entidades para persistir en bbdd

    @Data
    @RequiredArgsConstructor
    @NoArgsConstructor
    @Entity(name = "users")
    public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @NonNull
        private Integer id;
        @NonNull
        private String name;
        @Column(name = "surname")
        private String lastname;
        private String email;
        private LocalDate birthdate;

    }

