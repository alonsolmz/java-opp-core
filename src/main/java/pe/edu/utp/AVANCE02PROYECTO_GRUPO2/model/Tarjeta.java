package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.utp.AVANCE02PROYECTO_GRUPO2.Util.MetodoPago; // Usamos el ENUM de método de pago



@Entity
@Table(name = "tarjetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarjeta;

    private String numeroTarjeta;
    private String nombreTitular;
    private String fechaVencimiento; // MM/YY

    @Enumerated(EnumType.STRING)
    private MetodoPago tipoTarjeta;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}