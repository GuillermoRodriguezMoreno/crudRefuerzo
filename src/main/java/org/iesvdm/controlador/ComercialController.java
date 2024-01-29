package org.iesvdm.controlador;

import jakarta.validation.Valid;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.iesvdm.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Controller

public class ComercialController {

    @Autowired
    private ComercialService comercialService;

    @GetMapping("/comerciales") //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa
    public String listar(Model model) {

        List<Comercial> listaComerciales =  comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);

        return "comerciales";
    }

    @GetMapping("/comerciales/{id}")
    public String detalle(Model model, @PathVariable Integer id){

        // Comercial
        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        // Pedidos del comercial
        List<Pedido> listPedidos = comercialService.findPedidosByComercial(id);
        model.addAttribute("pedidos_comercial", listPedidos);

        // Estadisticas comercial
        ComercialDTO comercialDTO = comercialService.importeTotalPedidos(id);
        model.addAttribute("estadisticas_comercial", comercialDTO);

        // Pedido minimo
        model.addAttribute("pedido_min", comercialService.pedidoMin(id));

        // Pedido maximo
        model.addAttribute("pedido_max", comercialService.pedidoMax(id));

        // Clientes comercial
        List<Map.Entry<Cliente,Double>> listaClientes = comercialService.listaClientesOrdenados(id);
        model.addAttribute("lista_clientes", listaClientes);

        return "detalle-comercial";
    }

    @GetMapping("/comerciales/crear")
    public String crear(Model model){

        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "crear-comercial";
    }

    @PostMapping("/comerciales/crear")
    public String submitCrear(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){

            model.addAttribute("comercial", comercial);
            return "crear-comercial";
        }

        comercialService.newComercial(comercial);

        return "redirect:/comerciales" ;

    }
    @GetMapping("comerciales/editar/{id}")
    public String editar(Model model, @PathVariable Integer id){

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "editar-comercial";
    }

    @PostMapping("/comerciales/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            model.addAttribute("comercial", comercial);
            return "editar-comercial";
        }

        comercialService.replaceComercial(comercial);
        List<Comercial> listaComerciales =  comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);

        return "redirect:/comerciales" ;
    }

    @PostMapping("comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id){

        comercialService.deleteComercial(id);

        return new RedirectView("/comerciales");
    }
}
