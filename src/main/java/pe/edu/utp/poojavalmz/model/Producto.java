package pe.edu.utp.poojavalmz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTOS")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;


    @Column(nullable = false, precision = 10, scale = 2)
    @Min(value = 0, message = "El precio no puede ser negativo")
    private BigDecimal precio;


    @Column(nullable = false, precision = 10, scale = 2)
    @Min(value = 0, message = "El precio no puede ser negativo")
    private BigDecimal preciounitario;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public long compareTo() {
        return 0;
    }


}