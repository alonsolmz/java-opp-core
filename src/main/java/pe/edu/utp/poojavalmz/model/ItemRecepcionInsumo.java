package pe.edu.utp.poojavalmz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "items_recepcion_insumo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRecepcionInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemRecepcion;

    private Integer cantidadRecibida;
    private Double costoUnitario;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;
}
