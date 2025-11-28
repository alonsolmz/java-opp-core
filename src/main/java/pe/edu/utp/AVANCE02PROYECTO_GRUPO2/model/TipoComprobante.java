package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


 //Entidad TipoComprobante (Factura, Boleta, etc.).

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

    private String nombre; // Ej: Factura, Boleta
    private String serie;
    private String descripcion;

    // Relación: Este tipo de comprobante aplica a muchas ventas.
    // ESTRATEGIA: LAZY
    @OneToMany(mappedBy = "tipoComprobante", fetch = FetchType.LAZY)
    private List<Venta> ventas;
}
