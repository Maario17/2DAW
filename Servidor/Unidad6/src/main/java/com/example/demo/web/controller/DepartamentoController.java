package com.example.demo.web.controller;

import com.example.demo.model.dto.DepartamentoDTO;
import com.example.demo.service.DepartamentoService;
import com.example.demo.service.ClienteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DepartamentoController {

    private static final Logger log = LoggerFactory.getLogger(DepartamentoController.class);

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/{idCliente}/departamentos")
    public ModelAndView findAllByCliente(@PathVariable Long idCliente) {
        log.info("Obteniendo todos los departamentos del cliente con ID: {}", idCliente);
        return new ModelAndView("departamentos")
                .addObject("listaDepartamentosDTO", departamentoService.findAllByCliente(idCliente))
                .addObject("clienteDTO", clienteService.findById(idCliente));
    }

    @GetMapping("/clientes/{idCliente}/departamentos/{id}")
    public ModelAndView findById(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info("Obteniendo departamento con ID: {} para el cliente con ID: {}", id, idCliente);
        return new ModelAndView("departamentoshow")
                .addObject("departamentoDTO", departamentoService.findById(id))
                .addObject("clienteDTO", clienteService.findById(idCliente));
    }

    @PostMapping("/clientes/{idCliente}/departamentos/save")
    public ModelAndView save(@PathVariable Long idCliente, @ModelAttribute DepartamentoDTO departamentoDTO) {
        log.info("Guardando departamento: {} para cliente con ID: {}", departamentoDTO, idCliente);
        departamentoService.save(departamentoDTO, idCliente);
        return new ModelAndView("redirect:/clientes/" + idCliente + "/departamentos");
    }

    @GetMapping("/clientes/{idCliente}/departamentos/add")
    public ModelAndView add(@PathVariable Long idCliente) {
        log.info("Redirigiendo a formulario de nuevo departamento para cliente con ID: {}", idCliente);
        return new ModelAndView("departamentoform")
                .addObject("departamentoDTO", new DepartamentoDTO())
                .addObject("clienteDTO", clienteService.findById(idCliente))
                .addObject("add", true);
    }

    @GetMapping("/clientes/{idCliente}/departamentos/{id}/update")
    public ModelAndView update(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info("Redirigiendo a formulario de actualización para departamento con ID: {} del cliente con ID: {}", id, idCliente);
        return new ModelAndView("departamentoform")
                .addObject("departamentoDTO", departamentoService.findById(id))
                .addObject("clienteDTO", clienteService.findById(idCliente))
                .addObject("add", false);
    }

    @GetMapping("/clientes/{idCliente}/departamentos/{id}/delete")
    public ModelAndView deleteById(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info("Eliminando departamento con ID: {} del cliente con ID: {}", id, idCliente);
        departamentoService.deleteById(id);
        return new ModelAndView("redirect:/clientes/" + idCliente + "/departamentos");
    }
}
