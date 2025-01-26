package com.example.demo.web.controller;

import com.example.demo.model.dto.MetodoPagoDTO;
import com.example.demo.service.MetodoPagoService;
import com.example.demo.service.ClienteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MetodoPagoController {

    private static final Logger log = LoggerFactory.getLogger(MetodoPagoController.class);

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/{idCliente}/metodos-pago")
    public ModelAndView findAllByCliente(@PathVariable Long idCliente) {
        log.info("Obteniendo todos los métodos de pago del cliente con ID: {}", idCliente);
        return new ModelAndView("metodospago")
                .addObject("listaMetodosPagoDTO", metodoPagoService.findAllByCliente(idCliente))
                .addObject("clienteDTO", clienteService.findById(idCliente));
    }

    @GetMapping("/clientes/{idCliente}/metodos-pago/{id}")
    public ModelAndView findById(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info("Obteniendo método de pago con ID: {} para el cliente con ID: {}", id, idCliente);
        return new ModelAndView("metodospagoshow")
                .addObject("metodoPagoDTO", metodoPagoService.findById(id))
                .addObject("clienteDTO", clienteService.findById(idCliente));
    }

    @PostMapping("/clientes/{idCliente}/metodos-pago/save")
    public ModelAndView save(@PathVariable Long idCliente, @ModelAttribute MetodoPagoDTO metodoPagoDTO) {
        log.info("Guardando método de pago: {} para cliente con ID: {}", metodoPagoDTO, idCliente);
        metodoPagoService.save(metodoPagoDTO, idCliente);
        return new ModelAndView("redirect:/clientes/" + idCliente + "/metodos-pago");
    }

    @GetMapping("/clientes/{idCliente}/metodos-pago/add")
    public ModelAndView add(@PathVariable Long idCliente) {
        log.info("Redirigiendo a formulario de nuevo método de pago para cliente con ID: {}", idCliente);
        return new ModelAndView("metodopagoform")
                .addObject("metodoPagoDTO", new MetodoPagoDTO())
                .addObject("clienteDTO", clienteService.findById(idCliente))
                .addObject("add", true);
    }

    @GetMapping("/clientes/{idCliente}/metodos-pago/{id}/update")
    public ModelAndView update(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info("Redirigiendo a formulario de actualización para método de pago con ID: {} del cliente con ID: {}", id, idCliente);
        return new ModelAndView("metodopagoform")
                .addObject("metodoPagoDTO", metodoPagoService.findById(id))
                .addObject("clienteDTO", clienteService.findById(idCliente))
                .addObject("add", false);
    }

    @GetMapping("/clientes/{idCliente}/metodos-pago/{id}/delete")
    public ModelAndView deleteById(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info("Eliminando método de pago con ID: {} del cliente con ID: {}", id, idCliente);
        metodoPagoService.deleteById(id);
        return new ModelAndView("redirect:/clientes/" + idCliente + "/metodos-pago");
    }
}
