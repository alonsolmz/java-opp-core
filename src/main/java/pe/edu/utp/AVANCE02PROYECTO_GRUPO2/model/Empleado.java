package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "EMPLEADOS")
@Data
public class Empleado extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    // CAMPO AÑADIDO: Este campo era el que faltaba en la entidad Empleado
    // y es requerido por la lógica de las pruebas unitarias.
    @NotBlank(message = "El puesto/cargo es obligatorio")
    private String puesto;
}

