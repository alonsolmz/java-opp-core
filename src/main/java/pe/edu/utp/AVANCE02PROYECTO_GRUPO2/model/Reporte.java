package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


 // Entidad Reporte, representa un informe generado por el sistema.

@Entity
@Table(name = "reportes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    private String tipoReporte; // Ej: "Ventas Mensuales", "Stock Bajo"
    private LocalDateTime fechaGeneracion;
    private String contenidoArchivo; // Podría ser un path o contenido base64 del PDF/JSON
    private String generadoPor; // Empleado que generó el reporte
}