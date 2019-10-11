#Sistema para servicio de alquiler de bicicletas
==================================================

En el desarrollo de este sistema se decidio utilizar una aplicacion basada en servicios, realizada con Spring Boot, por el momento la aplicacion solo dispone de un backend,  donde se pueden realizar las consultas basicas para el funcionamiento solicitado por el cliente. Se decidio utilizar esta arquitectura ya que nos da la flexibilidad para trabajar de forma independiente el Backend y el frontend, Tambien da la posibilidad del dia de mañana realizar un cambio de tecnologia para el fontend o el backend sin tener que modificar el sistema completo.

La primera accion que realizamos para el desarrollo de este sistema fue definir las entidades que estan involucradas en las acciones necesarias para llevar a cabo las funcionalidades solicitadas por el cliente. Luego se implementaron las entidades definidas, generando su CRUD al cual se accede mediante peticiones HTTP, estos metodos HTTP(GET, POST, PUT, DELETE).

##Los end points otorgados por nuestra api son:
-------------------------------------------------------
	*Clientes:
		* GET: /api/clients 
			*Retorna un JSON con todos los clientes registrados.
		* GET: /api/clients/{id}
			*Retorna un JSON con el cliente buscado por su ID.
		* POST: /api/clients
			*Crea un cliente tomando sus atributos enviados en formato JSON.
		* PUT: /api/clients/{id}
			*Edita un cliente existente, buscado por su ID, y le edita los atributos enviados por JSON.
		* DELETE: api/clients/{id}
			*Elimina un cliente existente, buscado por su ID.
	*Servicios:
		* GET: /api/services
			* Retorna un JSON con todos los servicios registrados.
		* GET: /api/services{id}
			* Retorna un JSON con un servicio buscado por su ID.
		* POST: /api/services
			* Crea un servicio tomando sus atributos enviados en formato JSON.
		* PUT: /api/services/{id}
			*Edita un servicio existente, buscado por su ID, y le edita los atributos enviados por JSON.
		* DELETE: /api/services/{id}
			*Elimina un servicio existente, buscado por su ID.
	-Facturas:
		* GET:/api/invocies 
			*Retorna un JSON con tododas las facturas registradas.
		* POST:/api/invoices
			* Crea una factura tomando sus atributos enviados en formato JSON.
		* DELETE: /api/invoices/{id}
			*Elimina una factura existente, buscado por su ID.


##Install
---------------------------------------------------

1. Para instalar la aplicacion se debe clonar el repo.
	* Abrir el IDE utilizado para trabajar e importar el proyecto Maven colnado.
	* Luego de que finalicen las descargas de dependencias se debe abrir la carpeta src/.
	* Aqui tenemos dos sub carpetas main(donde esta el codigo) y test(la carpeta a ejecutar)
	* Dentro de esa carpeta utilizando Click derecho en la carpeta test se debe elejir la opcion Run as -> jUnti aplication(si se utiliza run coverage se mostrara el porcentaje de covertura).
