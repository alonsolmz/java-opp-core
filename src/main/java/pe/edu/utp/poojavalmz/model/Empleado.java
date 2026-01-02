package pe.edu.utp.poojavalmz.model;

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


    @NotBlank(message = "El puesto/cargo es obligatorio")
    private String puesto;
}

