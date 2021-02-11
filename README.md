# MiFlix
Miflix es un servicio de streaming que ofrece una amplia variedad de series, películas, títulos de anime, documentales y otros contenidos premiados en dispositivos conectados a internet. Puedes ver todo el contenido que quieras, cuando quieras y sin un solo anuncio por una tarifa mensual reducida.

- Parte Pública: El usuario podrá acceder a la página de registro donde elegirá un plan de suscripción y se podrá crear una cuenta de usuario, así como iniciar sesión.
- Parte Privada: Una vez registrado y logueado, el usuario puede acceder al catálogo de series y películas, su lista de deseados, reproducir el media por streaming y recibir sugerencias según el contenido visualizado.
- Parte de administrador: Los administradores del servicio podrán acceder a una página donde se habilita la gestión de películas y series incluidas en el catálogo, así como la categorización según etiquetas y géneros de los mismos. 

# Entidades
1. Usuario: Contiene el nombre, plan de suscripción y contraseña.
2. Serie: Contiene una pequeña descripción y se relaciona con sus capítulos. Tendrá asignados uno o varios géneros y tags.
3. Capítulos: Contiene el video multimedia a retransmitir.
4. Película: Contiene una pequeña descripción y el video multimedia. Tendrá asignados uno o varios géneros y tags.
5. Género: Se relacionará con las series y películas de cara a facilitar el sistema de sugerencias.
6. Tags: Se relacionará con las series y películas de cara a facilitar el sistema de sugerencias. Más bajo nivel que los géneros.
7. Lista deseados: El usuario podrá seleccionar una serie o película para verla más tarde y añadirla así a esta lista.
8. Lista seguir viendo: El usuario tendrá una lista de series empezadas, la cual se usará para mostrar una sección con las mismas en su menú principal, con el fin de tener un acceso rápido a una serie a la que esté enganchado. En la relación entre esta clase y serie/película se guardará el dato "capítulo" y "segundo".

# Descripción del servicio interno

- Visualización por streaming con calidad variable según el estado de conexión.
- Generación de sugerencias según visualizaciones previas y popularidad.
- Gestión de usuarios y planes de suscripción.
- Generación de listas de deseados y seguir viendo.
- Subida de películas y series para los administradores.

# [Trello](https://trello.com/b/XFN4E5ZO)

# Integrantes del desarrollo:
- Julen Justo Neira: [j.justo.2017@alumnos.urjc.es](mailto:j.justo.2017@alumnos.urjc.es) ([GitHub](https://github.com/JulenJus))
- Mariam Baradi Del Álamo: [m.baradi.2017@alumnos.urjc.es](mailto:m.baradi.2017@alumnos.urjc.es) ([GitHub](https://github.com/zuuhr))
