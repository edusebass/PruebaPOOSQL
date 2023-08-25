# JavaAppTienda

Realizado por: Eduardo Almachi

Aplicacion creada con Java con y conectada a SQL SERVER

La aplicacion realiza un CRUD con productos y proveedores.

---
## Pasos para conexion y creacion de aplicacion CRUD JAVA

 - Configuracion SQL SERVER
   - Debemos habilitar el puerto de conexion, realizado esto se habilitara la conexion.

  | ![img_2.png](img/img_2.png)                                 | ![img_1.png](img/img_1.png) | ![img_3.png](img/img_3.png)                                             |
  |---------------------------------------------------------|-------------------------|---------------------------------------------------------------------|
  | Entramos SQL SERVER CONFIGURATION y vamos a esta opcion | Habilitamos todo en yes | Nuevamente aqui habilatamos todo si y que los puertos esten en 1433 |
  

  - Configuracion SQL SERVER MANAGEMENT

  | ![img_7.png](img/img_7.png)                                                                  | ![img_4.png](img/img_4.png)                | ![img_5.png](img/img_5.png)                 |
  |------------------------------------------------------------------------------------------|----------------------------------------|-----------------------------------------|
  | Creamos un usuario para la conexion y seleccionamos la base con la que queremos trabajar | Configuramos al usuario de esta manera | Damos los permisos necesarios señalados |

- Conexion JAVA SQL SERVER
  - Para conectar a SQL SERVER debemos descargar el driver e instalarlo en nuestro proyecto.
  ![img.png](img/img.png)
  
  - Realizado esto debemos configurar las variables en el proyecto.
    - Especificamos puertos y usuarios como contraseña creados previamente.
  ![img.png](img/imgg.png)
  
- Aplicacion
  - Seccion Principal
  ![img_8.png](img/img_8.png)
  - Ver producto
  
  | ![img_9.png](img/img_9.png)            |
  |------------------------------------|
  | Si se ingresa el id se lee el dato |

  - Eliminar producto
  
  | ![img_10.png](img/img_10.png)   | ![img_11.png](img/img_11.png)                             |
  |-----------------------------|-------------------------------------------------------|
  | Se elimina por medio del id | Se elimina el producto y el proveedor al mismo tiempo |

  - Actualizar producto
  
  | ![img_12.png](img/img_12.png)                                               | ![img_13.png](img/img_13.png)      | ![img_14.png](img/img_14.png)        |
  |-------------------------------------------------------------------------|--------------------------------|----------------------------------|
  | Se actualiza por medio del id y se procede a hacer el respectivo cambio | Se realizo el cambio con exito | Se actualiza en la base de datos |                         |

  - Añadir producto
  
  | ![img_15.png](img/img_15.png)            | ![img_16.png](img/img_16.png) | ![img_17.png](img/img_17.png)     |
  |--------------------------------------|---------------------------|-------------------------------|
  | Se añade los campos para el producto | Se confirma el producto   | Se muestra el cambio en la BD |


  