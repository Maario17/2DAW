package com.example.demo.web.controller;

import com.example.demo.model.dto.CuentaDTO;
import com.example.demo.service.ClienteService;
import com.example.demo.service.CuentaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CuentaController {

    private static final Logger log = LoggerFactory.getLogger(CuentaController.class);

    @Autowired
    private CuentaService cuentaService;
 
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/{idCliente}/cuentas")
    public ModelAndView findAllByCliente(@PathVariable Long idCliente) {
        log.info("Obteniendo todas las cuentas del cliente con ID: {}", idCliente);
        return new ModelAndView("cuentas")
                .addObject("listaCuentasDTO", cuentaService.findAllByCliente(idCliente))
                .addObject("clienteDTO", clienteService.findById(idCliente));
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{id}")
    public ModelAndView findById(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info("Obteniendo cuenta con ID: {} del cliente con ID: {}", id, idCliente);
        return new ModelAndView("cuentashow")
                .addObject("cuentaDTO", cuentaService.findById(id))
                .addObject("clienteDTO", clienteService.findById(idCliente));
    }

    @PostMapping("/clientes/{idCliente}/cuentas/save")
    public ModelAndView save(@PathVariable Long idCliente, @ModelAttribute CuentaDTO cuentaDTO) {
        log.info("Guardando cuenta: {} para cliente con ID: {}", cuentaDTO, idCliente);
        cuentaService.save(cuentaDTO, idCliente);
        return new ModelAndView("redirect:/clientes/" + idCliente + "/cuentas");
    }

    @GetMapping("/clientes/{idCliente}/cuentas/add")
    public ModelAndView add(@PathVariable Long idCliente) {
        log.info("Redirigiendo a formulario de nueva cuenta para cliente con ID: {}", idCliente);
        return new ModelAndView("cuentaform")
                .addObject("cuentaDTO", new CuentaDTO())
                .addObject("clienteDTO", clienteService.findById(idCliente))
                .addObject("add", true);
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{id}/update")
    public ModelAndView update(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info("Redirigiendo a formulario de actualización para cuenta con ID: {} del cliente con ID: {}", id, idCliente);
        return new ModelAndView("cuentaform")
                .addObject("cuentaDTO", cuentaService.findById(id))
                .addObject("clienteDTO", clienteService.findById(idCliente))
                .addObject("add", false);
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{id}/delete")
    public ModelAndView deleteById(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info("Eliminando cuenta con ID: {} del cliente con ID: {}", id, idCliente);
        cuentaService.deleteById(id);
        return new ModelAndView("redirect:/clientes/" + idCliente + "/cuentas");
    }
}
