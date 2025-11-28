package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Detalle de la recepción de insumos (qué insumo y qué cantidad llegó).

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

    // Relación: El insumo recibido (EAGER)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;
}
