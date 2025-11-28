package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Entidad Marca, consistente con el uso de JPA y Lombok (@Data) en el resto del proyecto.
 * Define el fabricante de un producto.
 */
@Entity
@Table(name = "MARCAS")
@Data
public class Marca {

    // Usamos Long para el ID, consistente con otras entidades y JPA.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long idMarca;

    @NotBlank(message = "El nombre de la marca es obligatorio")
    private String nombre;

    private String descripcion;
}