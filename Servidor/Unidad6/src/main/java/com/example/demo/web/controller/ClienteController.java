package com.example.demo.web.controller;

import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.service.ClienteService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ModelAndView findAll() {
        log.info("Obteniendo todos los clientes");
        List<ClienteDTO> clientes = clienteService.findAll();
        return new ModelAndView("clientes").addObject("listaClientesDTO", clientes);
    }

    @GetMapping("/clientes/{id}")
    public ModelAndView findById(@PathVariable Long id) {
        log.info("Obteniendo cliente con ID: {}", id);
        ClienteDTO cliente = clienteService.findById(id);
        if (cliente == null) {
            log.warn("Cliente con ID: {} no encontrado", id);
        }
        return new ModelAndView("clienteshow").addObject("clienteDTO", cliente);
    }

    @PostMapping("/clientes/save")
    public ModelAndView save(@ModelAttribute ClienteDTO clienteDTO) {
        log.info("Guardando cliente: {}", clienteDTO);
        clienteService.save(clienteDTO);
        return new ModelAndView("redirect:/clientes");
    }

    @GetMapping("/clientes/add")
    public ModelAndView add() {
        log.info("Redirigiendo a formulario de nuevo cliente");
        return new ModelAndView("clienteform").addObject("clienteDTO", new ClienteDTO()).addObject("add", true);
    }

    @GetMapping("/clientes/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        log.info("Redirigiendo a formulario de actualización para cliente con ID: {}", id);
        ClienteDTO cliente = clienteService.findById(id);
        if (cliente == null) {
            log.warn("Cliente con ID: {} no encontrado", id);
        }
        return new ModelAndView("clienteform").addObject("clienteDTO", cliente).addObject("add", false);
    }

    @GetMapping("/clientes/delete/{id}")
    public ModelAndView deleteById(@PathVariable Long id) {
        log.info("Eliminando cliente con ID: {}", id);
        clienteService.deleteById(id);
        return new ModelAndView("redirect:/clientes");
    }
}
