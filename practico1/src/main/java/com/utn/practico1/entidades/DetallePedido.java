package com.utn.practico1.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedido extends BaseEntidad {
    private int cantidad;
    private Double subtotal;

    @ManyToOne()
    @JoinColumn (name = "producto_id")
    private Producto producto;





}




