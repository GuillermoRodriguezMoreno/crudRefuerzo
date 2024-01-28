package org.iesvdm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComercialDTO {

    //Atributos Comercial
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private BigDecimal comision;

    // Atributos auxiliares de Pedido
    private double importeTotalPedidos;
    private double mediaTotalPedidos;
}
