package org.iesvdm.service;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;
    @Autowired
    private PedidoDAO pedidoDAO;
    // inyeccion por constructor
    //public ComercialService(ComercialDAO comercialDAO) {
      //  this.comercialDAO = comercialDAO;
    //}

    public List<Comercial> listAll() {

        return comercialDAO.getAll();
    }

    public Comercial one(Integer id){

        Optional<Comercial> optComercial = comercialDAO.find(id);

        if(optComercial.isPresent())
            return optComercial.get();

        else
            return null;
    }

    public void newComercial(Comercial comercial){

        comercialDAO.create(comercial);
    }

    public void replaceComercial(Comercial comercial){

        comercialDAO.update(comercial);
    }

    public void deleteComercial(Integer id){

        comercialDAO.delete(id);
    }

    public List<Pedido> findPedidosByComercial(int idComercial){

        return pedidoDAO.pedidosByComercial(idComercial);
    }

    public ComercialDTO importeTotalPedidos(int idComercial){

        // Obtengo lista pedidos de un comercial por ID
        List<Pedido> pedidoList = pedidoDAO.pedidosByComercial(idComercial);

        // Obtengo total y media
        double total = pedidoList.stream()
                .mapToDouble(Pedido::getTotal)
                .sum();

        double media = pedidoList.stream()
                .mapToDouble(Pedido::getTotal)
                .average().getAsDouble();

        // Redondeo
        BigDecimal bDTotal = new BigDecimal(Double.toString(total)).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal bDMedia = new BigDecimal(Double.toString(media)).setScale(2, RoundingMode.HALF_EVEN);

        total = bDTotal.doubleValue();
        media = bDMedia.doubleValue();

        // Obtengo comercial
        Comercial comercial = comercialDAO.find(idComercial).get();

        // Setteo DTO
        ComercialDTO comercialDTO = new ComercialDTO(comercial.getId(),
                comercial.getNombre(),
                comercial.getApellido1(),
                comercial.getApellido2(),
                comercial.getComision(),
                total,
                media);

        return comercialDTO;
    }
}
