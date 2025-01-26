package com.example.demo.web.controller;

import com.example.demo.model.dto.MovimientoDTO;
import com.example.demo.service.ClienteService;
import com.example.demo.service.CuentaService;
import com.example.demo.service.MovimientoService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovimientoController {

    private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos")
    public ModelAndView findAllByCuenta(@PathVariable Long idCliente, @PathVariable Long idCuenta) {
        log.info("Obteniendo todos los movimientos de la cuenta con ID: {} del cliente con ID: {}", idCuenta, idCliente);
        return new ModelAndView("movimientos")
                .addObject("listaMovimientosDTO", movimientoService.findAll(cuentaService.findById(idCuenta)))
                .addObject("clienteDTO", clienteService.findById(idCliente))
                .addObject("cuentaDTO", cuentaService.findById(idCuenta));
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos/{id}")
    public ModelAndView findById(@PathVariable Long idCliente, @PathVariable Long idCuenta, @PathVariable Long id) {
        log.info("Obteniendo movimiento con ID: {} de la cuenta con ID: {} del cliente con ID: {}", id, idCuenta, idCliente);
        return new ModelAndView("movimientoshow")
                .addObject("movimientoDTO", movimientoService.findById(id))
                .addObject("clienteDTO", clienteService.findById(idCliente))
                .addObject("cuentaDTO", cuentaService.findById(idCuenta));
    }

    @PostMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos/save")
    public ModelAndView save(@PathVariable Long idCliente, @PathVariable Long idCuenta, @ModelAttribute MovimientoDTO movimientoDTO) {
        log.info("Guardando movimiento: {} para la cuenta con ID: {} del cliente con ID: {}", movimientoDTO, idCuenta, idCliente);
        movimientoDTO.setCuentaOrigen(cuentaService.findById(idCuenta)); 
        movimientoService.save(movimientoDTO);
        return new ModelAndView("redirect:/clientes/" + idCliente + "/cuentas/" + idCuenta + "/movimientos");
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos/add")
    public ModelAndView add(@PathVariable Long idCliente, @PathVariable Long idCuenta) {
        log.info("Redirigiendo al formulario para la cuenta con ID: {} del cliente con ID: {}", idCuenta, idCliente);
        return new ModelAndView("movimientoform")
                .addObject("movimientoDTO", new MovimientoDTO())
                .addObject("clienteDTO", clienteService.findById(idCliente))
                .addObject("cuentaDTO", cuentaService.findById(idCuenta))
                .addObject("cuentasDestino", cuentaService.findAll())
                .addObject("add", true);
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos/{id}/update")
    public ModelAndView update(@PathVariable Long idCliente, @PathVariable Long idCuenta, @PathVariable Long id) {
        log.info("Redirigiendo al formulario para movimiento con ID: {} de la cuenta con ID: {} del cliente con ID: {}", id, idCuenta, idCliente);
        return new ModelAndView("movimientoform")
                .addObject("movimientoDTO", movimientoService.findById(id))
                .addObject("clienteDTO", clienteService.findById(idCliente))
                .addObject("cuentaDTO", cuentaService.findById(idCuenta))
                .addObject("cuentasDestino", cuentaService.findAll())
                .addObject("add", false);
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos/{id}/delete")
    public ModelAndView deleteById(@PathVariable Long idCliente, @PathVariable Long idCuenta, @PathVariable Long id) {
        log.info("Eliminando movimiento con ID: {} de la cuenta con ID: {} del cliente con ID: {}", id, idCuenta, idCliente);
        movimientoService.deleteById(id);
        return new ModelAndView("redirect:/clientes/" + idCliente + "/cuentas/" + idCuenta + "/movimientos");
    }
}
