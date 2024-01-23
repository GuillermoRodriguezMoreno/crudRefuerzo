package org.iesvdm.service;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
