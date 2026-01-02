package pe.edu.utp.poojavalmz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



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


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Stock stockItem;

    private Integer cantidad;
    private Double precioUnitarioVenta;


    public Double getSubTotal() {
        if (precioUnitarioVenta == null || cantidad == null) return 0.0;
        return precioUnitarioVenta * cantidad;
    }
}
