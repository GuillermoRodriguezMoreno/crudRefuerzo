package org.iesvdm;

import org.iesvdm.dao.ClienteDAOImpl;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Calendar;
import java.util.List;

@SpringBootTest
public class ProyectoVentasApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ClienteDAOImpl clienteDAOImpl;

    @Test
    void test_recargar_id_auto_increment_por_ps() {

        Cliente cliente = new Cliente(0
                , "José M."
                , "Martín"
                , "Tejero"
                , "Málaga"
                , 1
                , "ejemplo@ejemplo.es");


        this.clienteDAOImpl.create(cliente);
        Assertions.assertTrue(cliente.getId()>0);
        System.out.println("ID AUTO_INCREMENT:" + cliente.getId());
    }
}


