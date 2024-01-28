package org.iesvdm;

import org.iesvdm.dao.ClienteDAOImpl;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Calendar;
import java.util.List;

import static java.util.stream.Collectors.toList;

@SpringBootTest
public class ventasStreamTest {

    @Autowired
    private PedidoDAO pedidoDAO;

    @Test
    void streamTest(){

        // Insertar en BBDD
        /*
        insert into pedido (total, fecha, id_cliente, id_comercial)
        values (100, '2024-01-2', 1, 1);
        insert into pedido (total, fecha, id_cliente, id_comercial)
        values (200, '2023-08-2', 1, 1);
        insert into pedido (total, fecha, id_cliente, id_comercial)
        values (300, '2023-03-2', 1, 2);
        insert into pedido (total, fecha, id_cliente, id_comercial)
        values (400, '2021-01-2', 1, 3);
         */

        List<Pedido> pedidoList = pedidoDAO.pedidosByCliente(1);

        // Lista comerciales de un cliente
        List<Comercial> comercialList = pedidoList.stream()
                .map(Pedido::getComercial)
                .distinct()
                .collect(toList());

        Assertions.assertEquals(4, comercialList.size());

        // Conteo pedidos por comercial
        //long conteoPedidos = pedidoList.stream()
        //        .map(Pedido::getComercial)
        //        .count();

        //Assertions.assertEquals(3, conteoPedidos);

        // Conteo pedidos en trimestre
        long pedidos_trimestre = pedidoList.stream()
                .filter(pedido ->{
                    Calendar f_pedido = Calendar.getInstance();
                    f_pedido.setTime(pedido.getFecha());

                    Calendar f_actual = Calendar.getInstance();
                    f_actual.add(Calendar.MONTH, -3);

                    return f_pedido.after(f_actual);
                })
                .count();

        Assertions.assertEquals(1, pedidos_trimestre);

        // Conteo pedidos en semestre
        long pedidos_semestre = pedidoList.stream()
                .filter(pedido ->{
                    Calendar f_pedido = Calendar.getInstance();
                    f_pedido.setTime(pedido.getFecha());

                    Calendar f_actual = Calendar.getInstance();
                    f_actual.add(Calendar.MONTH, -6);

                    return f_pedido.after(f_actual);
                })
                .count();

        Assertions.assertEquals(2, pedidos_semestre);

        // Conteo pedidos en año
        long pedidos_año = pedidoList.stream()
                .filter(pedido ->{
                    Calendar f_pedido = Calendar.getInstance();
                    f_pedido.setTime(pedido.getFecha());

                    Calendar f_actual = Calendar.getInstance();
                    f_actual.add(Calendar.YEAR, -1);

                    return f_pedido.after(f_actual);
                })
                .count();

        Assertions.assertEquals(3, pedidos_año);

        // Conteo pedidos en lustro
        long pedidos_lustro = pedidoList.stream()
                .filter(pedido ->{
                    Calendar f_pedido = Calendar.getInstance();
                    f_pedido.setTime(pedido.getFecha());

                    Calendar f_actual = Calendar.getInstance();
                    f_actual.add(Calendar.YEAR, -5);

                    return f_pedido.after(f_actual);
                })
                .count();

        Assertions.assertEquals(6, pedidos_lustro);
    }
}
