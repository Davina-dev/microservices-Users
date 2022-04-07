package es.edu.code_sherpas.microservices.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //por si alguna herramienta en el camino necesita crear instancias de estos objetos a traves de un constructor vacío
public class AccountDTO {
    private  Integer id;
    private  String  name;
}
