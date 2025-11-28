package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Detalle de una venta que referencia un producto y la cantidad vendida.
 * Utiliza BigDecimal para manejar el dinero con precisión.
 */
@Entity
@Table(name = "detalles_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    // Relación ManyToOne con Venta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id")
    private Venta venta;

    // Relación ManyToOne con Producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Integer cantidad;

    // CORRECCIÓN CLAVE: Usamos BigDecimal para PRECISION MONETARIA
    @Column(precision = 10, scale = 2) // Opcional: define la precisión en la DB
    private BigDecimal precioUnitario; // Precio al momento de la venta

    // CORRECCIÓN CLAVE: Usamos BigDecimal para PRECISION MONETARIA
    @Column(precision = 10, scale = 2) // Opcional: define la precisión en la DB
    private BigDecimal subtotal; // Resultado de la multiplicación (Precio * Cantidad)

    // Nota: Es fundamental que también se use BigDecimal en la entidad Venta para el total.
}