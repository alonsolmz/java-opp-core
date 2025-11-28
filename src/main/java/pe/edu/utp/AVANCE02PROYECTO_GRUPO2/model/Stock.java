package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

//Entidad Stock: Detalle del inventario por combinación de Producto (modelo), Talla y Color.

@Entity
@Table(name = "stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    private Integer cantidad;

    // Relación: Referencia al Producto (modelo de zapato) (EAGER)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private String talla; // Talla específica

    // Relación: Referencia al Color (EAGER)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "color_id")
    private Color color;

    // Relación: Los movimientos de inventario que afectan a este Stock.
    // ESTRATEGIA: LAZY
    @OneToMany(mappedBy = "stockItem", fetch = FetchType.LAZY)
    private List<MovimientoInventario> movimientos;
}
