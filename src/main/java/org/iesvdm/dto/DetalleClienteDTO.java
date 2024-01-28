package org.iesvdm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesvdm.modelo.Comercial;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleClienteDTO {

    private long idCliente;
    private List<Comercial> listaComerciales;
    Map<Comercial, Long> conteoPedidosPorComercial;
    private long conteoPedidosTrimestre;
    private long conteoPedidosSemestre;
    private long conteoPedidosAño;
    private long conteoPedidosLustro;

}
