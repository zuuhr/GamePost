# GamePost
GamePost es una página web dedicada a la difusión, discusión y puesta en común de opiniones e información acerca de videojuegos. Como punto de encuentro entre los jugadores, podrán consultar descripciones sobre todos los juegos del catálogo, así como consultar y abrir nuevas discusiones en el foro de dicho videojuego. Al registrarse, el usuario podrá crear nuevas entradas, almacenar sus juegos favoritos en listas, guardar entradas de foro para consultarlas más tarde o subscribirse a una discusión para no perder pista de esta.

- Parte Pública: Visualización de la información de juegos y discusiones. Página principal personalizada según actualidad.
- Parte Privada: Creación de nuevas entradas en el foro, almacenado de juegos y entradas de foros en listas. Página principal personalizada según gustos. Notificaciones sobre discusiones marcadas.
- Parte de administrador: Gestión de descripciones y comentarios de foros en juegos. Herramienta para añadir o retirar juegos del catálogo.
---
## Fase I
### Entidades
1. Usuario: Contiene el nombre, contraseña, referencia a la lista de listas y buzón de notificaciones.
2. Juego: Descripción y entradas de foro asociadas. La descipción incluye: Nombre, género, nº jugadores, año de publicación, plataforma, desarrolladora, publisher, motor de desarrollo, sinopsis.
5. Entradas de foro: Nombre de entrada, autor, fecha creación, fecha última entrada, nº respuestas, votos.
6. Lista: Contiene los juegos y/o entradas de foro incluidas por el usuario.

### Descripción del servicio interno

- Envío de notificaciones
- Procesamiento de gustos de usuario

### [Trello](https://trello.com/b/XFN4E5ZO)

---
## Fase II

### Diagrama de Navegación
![Navegación de Ventanas](https://user-images.githubusercontent.com/43469859/111301196-32a44a00-8652-11eb-84f9-fe3fdc67334b.png)
---
### Diagrama UML
![GamePost (UML)](https://user-images.githubusercontent.com/43469859/111301614-b5c5a000-8652-11eb-8790-044fb03b5981.png)
---
### Diagram E/R
![Gamepost_ER](https://user-images.githubusercontent.com/43469859/111310324-40ab9800-865d-11eb-9c03-f577c8b2f9c4.png)
---
### Lista de Ventanas
1. **Index**
![index](https://user-images.githubusercontent.com/43469859/111305252-fd4e2b00-8656-11eb-84f1-1feb3f1ac3a3.png)
Página principal. Muestra una lista de juegos de interés y desde esta se puede navegar por el resto de la web.

2. **Log In/Sign In**
![login](https://user-images.githubusercontent.com/43469859/111305271-02ab7580-8657-11eb-871b-b30762010ece.png)
![signin](https://user-images.githubusercontent.com/43469859/111305283-06d79300-8657-11eb-9a81-3ad7ca892412.png)
Ambas páginas contienen formularios donde el usuario podrá hacerse una cuenta creando una contraseña o verificarse si ya tiene una hecha.

3. **Notifications**
![notifications](https://user-images.githubusercontent.com/43469859/111305297-0a6b1a00-8657-11eb-91af-8e6d9ce695aa.png)
Muestra las notificaciones recibidas para el usuario actual. Un usuario recibe una notificación en cualquiera de los siguientes casos:
  - Alguien ha respondido a un comentario suyo
  - Alguien ha creado un forumEntry en un juego que sigue
  - Alguien ha creado un comentario en un forumEntry que sigue

4. **Game**
![game](https://user-images.githubusercontent.com/43469859/111305306-0ccd7400-8657-11eb-8473-d1cf78d479a2.png)
Muestra la descripción del juego, detalles de interés y una lista de enradas de foro del mismo juego. 
Además se pueden crear nuevas entradas de foro o acceder a las mismas.

5. **Forum Entry**
![forum](https://user-images.githubusercontent.com/43469859/111305343-1a82f980-8657-11eb-80e9-29603c558173.png)
Muestra los comentarios introducidos en el forumEntry así como el contenido principal del mismo.
Los comentarios aparecen ordenados de manera que una respuesta figura debajo de su comentario padre.

6. **Submit Game**
![uploadgame](https://user-images.githubusercontent.com/43469859/111305325-12c35500-8657-11eb-85e7-e1615200324c.png)
Herramienta enfocada a administradores para la subida de nuevos juegos a la página.

7. **Search Results**
![searchresults](https://user-images.githubusercontent.com/43469859/111305333-1656dc00-8657-11eb-9159-9a354d47b28b.png)
Vista del resultado de una búsqueda por nombre. Se buscan coincidencias palabra a palabra y se ordenan los resultados en función del número de coincidencias encontradas.
En la captura se puede ver el resultado al buscar "of".

8. **List**
![list](https://user-images.githubusercontent.com/43469859/111305707-91b88d80-8657-11eb-8620-2e53719c15aa.png)
Muestra los elementos almacenados por el usuario en una lista personalizada. Los elementos a guardar pueden ser comentarios, entradas de foro o juegos.

---
## Fase III

### Lista de Ventanas (actualizada)
1. **Index**
![index anonymous](https://user-images.githubusercontent.com/43469886/115697452-dffe3200-a363-11eb-9078-13f7efd99416.png)
![index user](https://user-images.githubusercontent.com/43469886/115697696-12a82a80-a364-11eb-940c-4ab914d3ccd0.png)
![index admin](https://user-images.githubusercontent.com/43469886/115703132-08892a80-a36a-11eb-974d-73012eab0523.png)
Página principal. Muestra una lista de juegos de interés y desde esta se puede navegar por el resto de la web. 
Ahora contempla si el usuario está logueado o no. En caso negativo, se muestran los 20 juegos que hayan sufrido interactividad más recientemente. En caso afirmativo, se muestran los 20 juegos que mejor coincidan con los gustos del usuario, proporcionados por la API Rest. Si este no puede procesar la operación por falta de información sobre el usuario, se devuelven los 20 juegos con interactividad más reciente como opción por defecto. También muestra campos personalizados en función de si el usuario está logueado o no y su rol asignado. 

3. **Notifications**
![notifications user](https://user-images.githubusercontent.com/43469886/115700613-25702e80-a367-11eb-9b97-b1e1968f6f1b.png)
La página ha sido restringida para que sólo se pueda accedar en caso de ser un usuario logueado. En caso de intentar acceder a la url sin estar logueado o con permisos de administrador, la página redirige al individuo a la página de login.

4. **Game**
![game anonymous](https://user-images.githubusercontent.com/43469886/115702754-99133b00-a369-11eb-9228-bb457a593881.png)
![game user](https://user-images.githubusercontent.com/43469886/115702852-b516dc80-a369-11eb-8c8b-b15cafc5ea41.png)
![game admin](https://user-images.githubusercontent.com/43469886/115703082-f8714b00-a369-11eb-809e-665fbfc46d2d.png)
Al igual que en "5. Forum entry", solo se permite crear contenido a los usuarios logueados. Tanto los administradores como los individuos no logueados pordrán visitar las páginas, solo que no contarán con estas opciones.

5. **Forum Entry**
![forumentry anonymous](https://user-images.githubusercontent.com/43469886/115703282-3bcbb980-a36a-11eb-9d91-de2d909eae6b.png)
![forumentry user](https://user-images.githubusercontent.com/43469886/115703361-53a33d80-a36a-11eb-9d30-fe1913266d12.png)
Admin?
Al igual que en "4. Game", solo se permite crear contenido a los usuarios logueados.

---
### Diagrama de clases y templates
![Diagrama de clases y templates](https://user-images.githubusercontent.com/43469886/115966843-b6920180-a52f-11eb-9e02-0b5472a2e4dd.png)

---
### Instrucciones de despliegue

---
## Integrantes del desarrollo:
- Julen Justo Neira: [j.justo.2017@alumnos.urjc.es](mailto:j.justo.2017@alumnos.urjc.es) ([GitHub](https://github.com/JulenJus))
- Mariam Baradi Del Álamo: [m.baradi.2017@alumnos.urjc.es](mailto:m.baradi.2017@alumnos.urjc.es) ([GitHub](https://github.com/zuuhr))
