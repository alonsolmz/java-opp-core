package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model.Enums.TipoMovimiento;

 //Entidad que registra la entrada o salida de stock.

@Entity
@Table(name = "movimientos_inventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimiento;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo; // ENTRADA o SALIDA

    private Integer cantidad;
    private LocalDateTime fechaMovimiento;
    private String razon;

    // Relación: El ítem de stock afectado (EAGER)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Stock stockItem;
}