package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Item de una venta que referencia un ítem de Stock (color, talla, producto) y la cantidad vendida.

@Entity
@Table(name = "items_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    // Relación: El ítem de stock vendido (EAGER)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Stock stockItem; // Referencia al Stock (producto + talla + color)

    private Integer cantidad;
    private Double precioUnitarioVenta;

    /**
     * Calcula el subtotal de este ítem (precio unitario * cantidad).
     * NOTA: Este método es aceptable, pero la lógica de cálculo total de la Venta debe ir en el Service.
     */
    public Double getSubTotal() {
        if (precioUnitarioVenta == null || cantidad == null) return 0.0;
        return precioUnitarioVenta * cantidad;
    }
}
