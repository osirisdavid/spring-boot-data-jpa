package com.technolgyworld.springboot.app.controller;

import com.technolgyworld.springboot.app.model.dao.IClienteDAO;
import com.technolgyworld.springboot.app.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteDAO clienteDAO;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteDAO.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "form";
    }

    @RequestMapping(value="/form/{id}")
    public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {

        Cliente cliente = null;

        if(id > 0) {
            cliente = clienteDAO.findOne(id);
        } else {
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model, SessionStatus status) {
        if(result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Cliente");
            return "form";
        }

        clienteDAO.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }

    @RequestMapping(value="/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") Long id) {

        if(id > 0) {
            clienteDAO.delete(id);
        }
        return "redirect:/listar";
    }
}