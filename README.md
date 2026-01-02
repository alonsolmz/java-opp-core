# Inventory & Sales Management System (Java OOP Core)

Este proyecto es un sistema integral para la gestión de inventarios, ventas y compras, diseñado bajo los principios de la **Programación Orientada a Objetos (POO)** y una arquitectura multicapa. Provee una solución robusta para el control de stock y transacciones comerciales.

## 🛠️ Tecnologías y Dependencias

Basado en la configuración del `pom.xml`, el sistema utiliza:

* **Lenguaje:** Java 19
* **Framework:** Spring Boot 3.2.0
* **Documentación API:** Swagger / OpenAPI 3 (SpringDoc 2.5.0)
* **Persistencia:** Spring Data JPA
* **Base de Datos:** H2 Database (Runtime)
* **Productividad:** Project Lombok (para limpieza de boilerplate)
* **Validación:** Spring Boot Starter Validation
* **Pruebas:** Spring Boot Starter Test

## 🏗️ Arquitectura del Software

El proyecto se organiza siguiendo el patrón **Controller-Service-Repository**, lo que permite una separación clara de responsabilidades:

* **Controller:** Capa de exposición de servicios web y endpoints.
* **Service:** Contiene la lógica de negocio central (Interfaces e Implementaciones).
* **Repository:** Gestión de la persistencia de datos mediante Spring Data.
* **Model:** Entidades del dominio que representan los activos del negocio (Producto, Cliente, Venta, etc.).
* **Util:** Tipos enumerados y constantes para estandarizar procesos (Metodos de Pago, Estados).



## 🚀 Características Destacadas

1. **Documentación Interactiva:** Gracias a SpringDoc OpenAPI, puedes visualizar y probar los endpoints del sistema de forma sencilla.
2. **Gestión de Stock:** Control de inventario dinámico basado en las operaciones de compra y venta.
3. **Desarrollo Ágil:** Uso de Lombok para mantener las clases de modelo limpias de getters, setters y constructores manuales.
4. **Base de Datos Integrada:** Configuración con H2 que facilita el despliegue inmediato sin dependencias externas de BD.
5. **Validación de Datos:** Uso de anotaciones de validación para asegurar la integridad de la información ingresada al sistema.

