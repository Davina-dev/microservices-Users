 

![header](https://capsule-render.vercel.app/api?text=Api%_%HTTP&fontColor=d6ace6 )
#
## 🔨 Build application 
Abrir una terminal:

- Ubicarse en la raiz del proyecto 
  ```
   microservices / src / main / resource 
   ```
- Ejecutar:
  ```
  docker-compose up
  ```
 - La aplicacion esta levantada y se puede probar con postman

#
## 👩🏽‍💻 Objetivo del proyecto:
 Crear una API HTTP para gestionar los datos de los clientes de una pequeña tienda.
  - Capacidades de la api: 
    - Crear un nuevo cliente (atributos: nombre, apellido, correo electrónico y fecha de nacimiento)
    - Obtener un solo cliente 
    - Obtener todos los clientes 
    - Actualizar todos los atributos (a la vez) 
    - Eliminar un cliente existente 
#
### 🤔 PRIMERAS DUDAS:
 - Tipo de arquitectura: microservicios vs monolito 
 - Cómo usar Docker y MySQL
#
### 🧹 CLEAN CODE
  - Estructura sencilla y que sigue una lógica
  - La relación entre diferentes partes del código es clara
  - Las clases son reducidas y predecibles.
  - código con pruebas unitarias
  - se evita repetir sin motivo (lombok)
  
#
### 📖 SOLID 

  - PRINCIPIO DE RESPONSABILIDAD ÚNICA 
      - Separación de responsabilidades por clases  
      - las carpetas son específicas y acotadas 
  - PRINCIPIO ABIERTO/CERRADO
      - Las clases estan abiertas para su expensión, pero cerradas para su modificación
  - PRINCIPIO DE SUSTITUCIÓN DE LISKOV
      - Este es quizás el más complejo de los cinco principios y me cuesta ver un ejemplo en el código.
      - El principio de sustitución de Liskov dice que si la clase A es de un subtipo de la clase B, entonces deberíamos poder reemplazar B con A sin afectar el comportamiento de nuestro programa.
  - PRINCIPIO DE SEGREGACIÓN DE INTERFACES
      - UserService y UserRepository sin dos interfases que se pueden ver en el proyecto
  - PRINCIPIO DE INVERSIÓN DE DEPENDENCIAS
    - La clase UserServiceImpl  no necesita saber quién o cómo implementa la persistencia. La clase     UserServiceImpl utiliza la interfaz y desconoce su implementación.
#
### ⚠️ COSAS PENDIENTES:

  - Las dificultades han sido constantes, ya que estaba muy poco familiarizada con todo lo que he tocado en  el proyecto.
  
 -  Me he quedado con ganas de utilizar, en la versión final, dependencias como: hateoas, swagger, Logback, mapper...
  
  
  
  
 













