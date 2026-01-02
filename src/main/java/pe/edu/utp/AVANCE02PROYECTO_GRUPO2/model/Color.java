package pe.edu.utp.AVANCE02PROYECTO_GRUPO2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;



@Entity
@Table(name = "colores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idColor;

    private String nombre; // Ej: "Rojo", "Azul Marino"
    private String codigoHex; // Ej: "#FF0000"

    // Relación: Un color puede estar en muchos productos (A través de Stock).
    // ESTRATEGIA: LAZY
    @OneToMany(mappedBy = "color", fetch = FetchType.LAZY)
    private List<Stock> stockItems;
}
