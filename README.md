# Inventory & Sales Management System (Java OOP Core)

Este proyecto es un sistema integral para la gestión de inventarios, ventas y compras, diseñado bajo los principios fundamentales de la **Programación Orientada a Objetos (POO)** y una arquitectura de software multicapa.

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3.x
* **Persistencia:** Spring Data JPA
* **Base de Datos:** H2 Database (Motor en memoria, no requiere configuración externa)
* **Gestor de Dependencias:** Maven
* **Pruebas:** JUnit 5 & Mockito

## 🏗️ Arquitectura y Patrones de Diseño

El sistema implementa el patrón **Controller-Service-Repository**, garantizando una separación de responsabilidades clara y facilitando el mantenimiento:

* **Model (Entities):** Representación de los objetos de negocio como `Producto`, `Venta`, `Cliente` y `Empleado`.
* **Repository:** Capa de abstracción de datos utilizando interfaces de Spring Data.
* **Service:** Implementación de la lógica de negocio y reglas del sistema (paquete `service.impl`).
* **Controller:** Exposición de endpoints y lógica de control para la gestión de recursos.
* **Enums/Util:** Manejo profesional de estados constantes como `MetodoPago` y `EstadoVenta`.


## 🚀 Características Principales

1. **Gestión de Stock:** Control automatizado de inventarios basado en entradas por compras y salidas por ventas.
2. **Relaciones Complejas:** Implementación de relaciones JPA (OneToMany, ManyToOne) entre entidades como `Venta` y `ItemVenta`.
3. **Persistencia Dinámica:** Uso de **H2 Database**, lo que permite ejecutar el proyecto de forma inmediata tras clonarlo.
4. **Validación y Excepciones:** Manejo centralizado de errores con `ResourceNotFoundException`.
5. **Pruebas Unitarias:** Cobertura de lógica crítica en servicios para asegurar la integridad de los datos.

## 💻 Instrucciones para Ejecución Local

Para ejecutar este proyecto en tu máquina local, sigue estos pasos:

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/alonsolmz/alonsolmz-java-oop-core.git](https://github.com/alonsolmz/alonsolmz-java-oop-core.git)
