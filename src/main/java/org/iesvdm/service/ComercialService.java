package org.iesvdm.service;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;

    //public ComercialService(ComercialDAO comercialDAO) {
      //  this.comercialDAO = comercialDAO;
    //}

    public List<Comercial> listAll() {

        return comercialDAO.getAll();

    }
}
