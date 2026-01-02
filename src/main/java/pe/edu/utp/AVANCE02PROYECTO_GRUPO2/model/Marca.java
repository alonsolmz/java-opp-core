package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Table(name = "MARCAS")
@Data
public class Marca {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Long idMarca;

    @NotBlank(message = "El nombre de la marca es obligatorio")
    private String nombre;

    private String descripcion;
}