
# API de Gestión de Pagos

Este proyecto es una API para gestionar pagos de usuarios utilizando Spring Boot y MongoDB. Permite crear pagos, agregar productos a un pago y consultar los pagos de un usuario. La validación de los pagos se realiza mediante la comparación del total proporcionado por el usuario con el total calculado por el sistema.

## Desarrollado por

- Mayerlly Suárez Correa

## Tecnologías Utilizadas

- **Spring Boot**: Framework para construir aplicaciones Java basadas en Spring.
- **MongoDB**: Base de datos NoSQL utilizada para almacenar los datos de los pagos.
- **CORS**
## Comandos para Inicializar el Proyecto

### Iniciar el Proyecto
1. Clonar el repositorio:
   ```
   git clone https://github.com/corrllr/payment-back
   cd payment-back
   ```

2. Compilar el proyecto
   ```
   mvn clean install
   ```

3. Ejecutar la aplicación:
   ```
   mvn spring-boot:run
   ```

4. Accede a través de 



## Endpoints
### Crear un Pago

- **Método**: `POST`
- **Ruta**: `/api/v1/pagos/{totalUsuario}`
- **Descripción**: Crea un pago, validando que el total dado por el usuario coincida con el total calculado por el sistema.
- **Body** (JSON):
  ```json
  {
    "idUser": "123456",
    "products": {
      "producto1": {
        "id": "1",
        "name": "Producto A",
        "unitPrice": 100.0,
        "units": 2
      }
    },
    "date": "27-03-2025"
  }
  ```
- **Response**:
  ```json
  {
    "id": "xyz123",
    "idUser": "123456",
    "totalPrice": 200.0,
    "status": "Aprobado",
    "products": {
      "producto1": { ... }
    }
  }
  ```

### Obtener Pagos de un Usuario

- **Método**: `GET`
- **Ruta**: `/api/v1/pagos/{idUser}`
- **Descripción**: Obtiene todos los pagos realizados por un usuario.
- **Response**:
  ```json
  [
    {
      "id": "xyz123",
      "idUser": "123456",
      "totalPrice": 200.0,
      "status": "Aprobado",
      "products": { ... }
    }
  ]
  ```

### Añadir un Producto a un Pago

- **Método**: `PUT`
- **Ruta**: `/api/v1/pagos/{idPago}/producto`
- **Descripción**: Añade un producto a un pago existente.
- **Body** (JSON):
  ```json
  {
    "id": "producto2",
    "name": "Producto B",
    "unitPrice": 150.0,
    "units": 1
  }
  ```
- **Response**:
  ```json
  {
    "id": "xyz123",
    "idUser": "123456",
    "totalPrice": 350.0,
    "status": "Aprobado",
    "products": {
      "producto1": { ... },
      "producto2": { ... }
    }
  }
  ```

### Validar Total de un Pago

- **Método**: `PUT`
- **Ruta**: `/api/v1/pagos/{idPago}/total`
- **Descripción**: Valida que el total dado por el usuario coincida con el total calculado por el sistema.
- **Parámetros**:
  - `totalUsuario`: El total proporcionado por el usuario.
- **Response**:
  ```json
  {
    "estado": "Aprobado"
  }
  ```

## Version desplegada

Actualmente existe un pipeline que se encarga de desplegar e integrar continuamente para que el proyecto se despliegue en azure. Lo puede probar mediante el siguiente link:
  "http://cvds-bqh3apgub0bcepey.canadacentral-01.azurewebsites.net/api/v1/pagos"

### Diagrama de componentes:
![Componentes](https://github.com/user-attachments/assets/03bed2a2-2496-4575-9c70-c3ea28aeb6e1)

### Diagrama de clases:
![clases](https://github.com/user-attachments/assets/9f283927-d082-4805-9381-cc14ab4f5535)


### Pruebas en Jacoco

![image](https://github.com/user-attachments/assets/e7d59637-5e96-4fe6-a454-cf1d564e43e5)

### Base de datos en Mongo ATlas
![image](https://github.com/user-attachments/assets/c946bd4c-0d6d-4ccc-ae8f-3b199b58f557)

### Datos:

Actualmente existe un usuario en la base de datos con el id: 1193204781 con este usted podrá hacer validaciones en consultas, de lo contrario es libre de crear nuevos pagos


