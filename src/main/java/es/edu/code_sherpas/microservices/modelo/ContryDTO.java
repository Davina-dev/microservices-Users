package es.edu.code_sherpas.microservices.modelo;

import lombok.Data;

@Data
public class ContryDTO {

    private String isoCode;
    private String name;
    private String flag;
}
