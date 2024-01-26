package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dao.PedidoDAOImpl;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.toList;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private PedidoDAO pedidoDAO;
	
	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired
	// inyeccion por constructor
	//public ClienteService(ClienteDAO clienteDAO) {
	//	this.clienteDAO = clienteDAO;
	//}
	
	public List<Cliente> listAll() {
		
		return clienteDAO.getAll();
		
	}

	public Cliente one(Integer id){

		Optional<Cliente> optCliente = clienteDAO.find(id);

		if (optCliente.isPresent())
			return optCliente.get();

		else
			return null;
	}

	public void newCliente(Cliente cliente){

		clienteDAO.create(cliente);
	}

	public void replaceCliente(Cliente cliente){

		clienteDAO.update(cliente);
	}

	public void deleteCliente(Integer id){

		clienteDAO.delete(id);
	}

	public List<Pedido> pedidosByCliente(int idCliente){

		return pedidoDAO.pedidosByCliente(idCliente);
	}

	public List<Comercial> comercialesByCliente(int idCliente){

		// Settear a DTO
		List<Comercial> comercialList;
		int conteoPedidos, pedidos_trimestre, pedidos_semestre, pedidos_año, pedidos_lustro;

		// Obtengo lista de pedidos de un cliente
		List<Pedido> pedidoList = this.pedidosByCliente(idCliente);

		// Obtener datos
		comercialList = pedidoList.stream()
				.

		return pedidoList;
	}
}
