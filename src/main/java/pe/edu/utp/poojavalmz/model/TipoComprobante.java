package pe.edu.utp.poojavalmz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;




@Entity
@Table(name = "tipos_comprobante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoComprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprobante;

    private String nombre;
    private String serie;
    private String descripcion;


    @OneToMany(mappedBy = "tipoComprobante", fetch = FetchType.LAZY)
    private List<Venta> ventas;
}
