package pe.edu.utp.poojavalmz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;



@Data
@MappedSuperclass
public class Persona {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    private String dni;

    @Email(message = "Formato de email inválido")
    private String email;

    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String telefono;
}
