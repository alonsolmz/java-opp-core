package pe.edu.utp.poojavalmz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;




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


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recepcion_id")
    private List<ItemRecepcionInsumo> items;
}