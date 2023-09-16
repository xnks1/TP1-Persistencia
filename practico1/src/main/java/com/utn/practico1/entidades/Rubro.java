package com.utn.practico1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rubro extends BaseEntidad {
    private String denominacion;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();


    public void agregarProducto(Producto produ){
        productos.add(produ);

    }

    public void mostrarProductos(){
        System.out.println("Los productos de este rubro son :");
        for (Producto producto : productos) {
            System.out.println("Denominacion: " + producto.getDenominacion() + "Receta: " + producto.getReceta() + "UnidadMedida: " + producto.getUnidadMedida() + "PrecioVenta: " + producto.getPrecioVenta() + "PrecioCompra: " + producto.getPrecioCompra()+
            "StockActual: " + producto.getStockActual() + "StockMinimo: " + producto.getStockMinimo()+ "TiempoEstimadoCocina: " + producto.getTiempoEstimadoCocina()+
            "TipoProducto: " + producto.getTipoProducto());



        }
    }



}
