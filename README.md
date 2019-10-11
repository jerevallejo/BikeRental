Sistema para servicio de alquiler de bicicletas
==================================================

En el desarrollo de este sistema se decidió utilizar una aplicación basada en servicios, realizada con Spring Boot, por el momento la aplicación solo dispone de un backend, donde se pueden realizar las consultas básicas para el funcionamiento solicitado por el cliente. Se decidió utilizar esta arquitectura ya que nos da la flexibilidad para trabajar de forma independiente el Backend y el frontend, También da la posibilidad del día de mañana realizar un cambio de tecnología para el fontend o el backend sin tener que modificar el sistema completo.

La primera acción que realizamos para el desarrollo de este sistema fue definir las entidades que están involucradas en las acciones necesarias para llevar a cabo las funcionalidades solicitadas por el cliente. Luego se implementaron las entidades definidas, generando su CRUD al cual se accede mediante peticiones HTTP, estos métodos HTTP (GET, POST, PUT, DELETE).

Los end points otorgados por nuestro api son:
-------------------------------------------------------
1. Clientes:
	* GET: /api/clients 
		* Retorna un JSON con todos los clientes registrados.
	* GET: /api/clients/{id}
		* Retorna un JSON con el cliente buscado por su ID.
	* POST: /api/clients
		* Crea un cliente tomando sus atributos enviados en formato JSON.
	* PUT: /api/clients/{id}
		* Edita un cliente existente, buscado por su ID, y le edita los atributos enviados por JSON.
	* DELETE: api/clients/{id}
		* Elimina un cliente existente, buscado por su ID.
2. Servicios:
	* GET: /api/services
		* Retorna un JSON con todos los servicios registrados.
	* GET: /api/services{id}
		* Retorna un JSON con un servicio buscado por su ID.
	* POST: /api/services
		* Crea un servicio tomando sus atributos enviados en formato JSON.
	* PUT: /api/services/{id}
		* Edita un servicio existente, buscado por su ID, y le edita los atributos enviados por JSON.
	* DELETE: /api/services/{id}
		* Elimina un servicio existente, buscado por su ID.
3. Facturas:
	* GET:/api/invocies 
		* Retorna un JSON con todas las facturas registradas.
	* POST:/api/invoices
		* Crea una factura tomando sus atributos enviados en formato JSON.
	* DELETE: /api/invoices/{id}
		* Elimina una factura existente, buscado por su ID.

Install
---------------------------------------------------

1. Para instalar la aplicación se debe clonar el repo.
	* Abrir el IDE utilizado para trabajar e importar el proyecto Maven clonado.
	* Luego de que finalicen las descargas de dependencias se debe abrir la carpeta src/.
	* Aquí tenemos dos sub carpetas main (donde está el código) y test (la carpeta a ejecutar)
	* Dentro de esa carpeta utilizando Click derecho en la carpeta test se debe elegir la opción Run as -> jUnti aplication (si se utiliza run coverage se mostrará el porcentaje de cobertura).
2. Observación, por el momento se está usando una base de datos H2(en memoria) para facilitar la ejecución de los tests, esta base se carga a partir del archivo *import.sql* encontrado en /src/main/resources/, en caso de querer usar una base de datos convencional debemos des comentar en el archivo *application.properties* encontrado en /src/main/resources/. En este archivo se debe configurar lo siguiente: 
	* spring.datasource.url=jdbc:mysql://localhost/db_bike_rental
		* Se debe asignar la url de la base de datos y el nombre de la base a utilizar
	* spring.datasource.username= NameDataBase
		* Usuario de la base de datos
	* spring.datasource.password= PassDataBase
		* Contraseña de la base de datos
	* spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
	* spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
	
