package es.edu.code_sherpas.microservices.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController //cobra vida, esta clase ya es un controlador de tipo rest
public class HolaMundoRest {

   @GetMapping("/holaMundo")
    public String saludo(){
        return "Hola Mundo Servivio Rest Java";
    }

}
