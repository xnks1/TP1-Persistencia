package com.utn.practico1.entidades;
import jakarta.persistence.*;
import com.utn.practico1.emuns.TipoProducto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "producto")
public class Producto extends BaseEntidad {
    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioVenta;
    private double precioCompra;
    private int stockActual;
    private int stockMinimo;
    private String unidadMedida;
    private String receta;
}
