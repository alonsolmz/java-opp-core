package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "items_compra")
@Data // Incluye Getters y Setters
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemCompra;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id")
    private Compra compra;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;

    private Integer cantidad;

    private Double precioUnitarioCompra;


}

