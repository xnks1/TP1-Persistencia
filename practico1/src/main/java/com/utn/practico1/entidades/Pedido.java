package com.utn.practico1.entidades;

import com.utn.practico1.emuns.EstadoPedido;
import com.utn.practico1.emuns.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends BaseEntidad {

    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;
    private Double total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn (name = "factura_id")
    private Factura factura;


    public void agregarDetallePedido(DetallePedido detallePedido){
        detallePedidos.add(detallePedido);
    }




}
