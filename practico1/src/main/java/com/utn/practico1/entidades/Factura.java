package com.utn.practico1.entidades;

import com.utn.practico1.emuns.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura extends BaseEntidad {

    private int numero;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Double descuento;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;
    private int total;

}
