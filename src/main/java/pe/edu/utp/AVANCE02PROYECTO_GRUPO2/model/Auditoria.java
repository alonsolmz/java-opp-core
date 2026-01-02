package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;




@Entity
@Table(name = "auditoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuditoria;

    private LocalDateTime fechaCambio;
    private String entidadAfectada;
    private Long idEntidad;
    private String operacion; // INSERT, UPDATE, DELETE
    private String usuarioResponsable;
    private String detalles;
}
