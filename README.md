# RenovAR 🏠
## **Servicio de Diseños (Design Service)**

El **Servicio de Diseños** es un microservicio desarrollado en **Spring Boot** que forma parte del proyecto **RenovAR**. Este servicio se encarga de gestionar los diseños creados por los usuarios, permitiendo la creación, actualización, eliminación y visualización de diseños. Además, incluye funcionalidades para gestionar cotizaciones asociadas a los diseños, lo que facilita la interacción entre usuarios y carpinteros.

---

## Tecnologías Utilizadas 🧑🏻‍💻

Para ejecutar este servicio, necesitas tener instalado lo siguiente:

- **Java 17** o superior.
- **Maven** para la gestión de dependencias y construcción del proyecto.
- **MongoDB** como base de datos.
- **Docker** (opcional, para ejecutar el servicio en un contenedor).

---

## **Configuración** ⚙️

1. **Clona el repositorio:**
    
    ```
    git clone https://github.com/IETI-RenovAR/Designs.git
    cd Designs
    ```
    
2. **Configura la base de datos:**
    - Asegúrate de tener MongoDB instalado y en ejecución.
    - Configura la conexión a MongoDB en el archivo `application.properties`
3. **Instala las dependencias:**
    
    ```
    mvn clean install
    ```
    

---

## **Ejecución** 💻

### **Ejecución Local**

1. **Compila y ejecuta el servicio:**
    
    ```
    mvn spring-boot:run
    ```
    
2. **Accede al servicio:**
    - El servicio estará disponible en `http://localhost:8080`.
      

### **Ejecución con Docker** 💻

1. **Construye la imagen de Docker:**
    
    ```
    docker build -t designs .
    ```
    
2. **Ejecuta el contenedor:**
    
    ```
    docker run -d --name designs  --network renovar --ip 10.0.0.x designs
    ```
    

---

## **Endpoints** 🎯


La documentación completa se encuentra en: http://localhost:8080/swagger-ui/index.html

![image](https://github.com/user-attachments/assets/478f3497-248e-4841-a861-700efb1dfec5)


El servicio expone los siguientes endpoints:

### **Diseños**

- **Obtener todos los diseños públicos:**
    
    `GET /v1/designs`
    
- **Obtener un diseño por ID:**
    
    `GET /v1/designs/{id}`
    
- **Obtener diseños de un usuario:**
    
    `GET /v1/designs/users/{userId}`
    
- **Crear un diseño:**
    
    `POST /v1/designs`
    
    ```
    {
      "userId": 123,
      "name": "Modern Table",
      "url": "https://example.com/designs/modern-table",
      "isPublic": true,
      "state": "CREATED",
      "searchingCarpenter": false,
      "quotations": []
    }
    ```
    
- **Actualizar un diseño:**
    
    `PUT /v1/designs/{id}`
    
    ```
    {
      "userId": 123,
      "name": "Modern Table",
      "url": "https://example.com/designs/modern-table-brown",
      "isPublic": true,
      "state": "CREATED",
      "searchingCarpenter": false,
      "quotations": []
    }
    ```
    
- **Eliminar un diseño:**
    
    `DELETE /v1/designs/{id}`
    

### **Cotizaciones** 

- **Obtener todas las cotizaciones de un diseño:**
    
    `GET /v1/designs/{id}/quotations`
    
- **Obtener una cotización por ID:**
    
    `GET /v1/designs/quotations/{idQuotation}`
    
- **Crear una cotización para un diseño:**
    
    `POST /v1/designs/{id}/quotations`
    
    ```
    {
      "carpenterId": "2",
      "price": "500.00"
    }
    ```
    
- **Actualizar una cotización:**
    
    `PUT /v1/designs/quotations/{idQuotation}`
    
    ```
    {
      "carpenterId": "2",
      "price": "450.00",
      "accepted": true
    }
    ```
    
- **Eliminar una cotización:**
    
    `DELETE /v1/designs/quotations/{idQuotation}`
    

---

## **Pruebas** ✅

El servicio incluye pruebas unitarias para garantizar su correcto funcionamiento. Para ejecutar las pruebas, usa el siguiente comando:

```
mvn test
```

### **Casos de Prueba Cubiertos** 🫡

1. Crear un diseño y verificar que se guarde correctamente.
2. Obtener un diseño por ID y validar que la respuesta es correcta.
3. Intentar obtener un diseño inexistente y verificar que se maneje adecuadamente el error (404).
4. Actualizar un diseño existente y verificar que se actualice correctamente.
5. Eliminar un diseño existente y verificar que se elimine correctamente.
6. Gestionar cotizaciones asociadas a un diseño.
