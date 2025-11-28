package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;


 // Entidad que registra la recepción de insumos de un proveedor.

@Entity
@Table(name = "recepciones_insumo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecepcionInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecepcion;

    private LocalDateTime fechaRecepcion;
    private Double costoTotal;

    // Relación: Los items recibidos (EAGER)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recepcion_id")
    private List<ItemRecepcionInsumo> items;
}