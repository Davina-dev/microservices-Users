package es.edu.code_sherpas.microservices.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor //por si alguna herramienta en el camino necesita crear instancias de estos objetos a traves de un constructor vacío
@RequiredArgsConstructor
public class AccountDTO {
    private  Integer id;
    @NonNull
    private  String  name;
}
