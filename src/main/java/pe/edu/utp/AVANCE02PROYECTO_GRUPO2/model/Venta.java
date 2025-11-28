package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.DetalleVenta; // Aseguramos el import
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad principal para la transacción de venta.
 * Contiene la cabecera de la venta, referencias a Cliente y Empleado, y una lista de Detalles.
 */
@Entity
@Table(name = "VENTAS")
@Data
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @CreationTimestamp
    @Column(name = "fecha_venta", updatable = false)
    private LocalDateTime fechaVenta;

    // CORRECTO: BigDecimal para el total.
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    // Relación OneToMany con DetalleVenta.
    // Usamos CascadeType.ALL para que al guardar la Venta, se guarden automáticamente sus detalles.
    // El 'mappedBy' apunta al campo 'venta' en la clase DetalleVenta.
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalleVenta = new ArrayList<>();

    // Método auxiliar para mantener la consistencia bidireccional si se añade un detalle
    public void addDetalle(DetalleVenta detalle) {
        detalleVenta.add(detalle);
        detalle.setVenta(this);
    }
}