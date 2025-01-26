package com.example.demo.web.controller;

import com.example.demo.model.dto.ClienteDTO;
import com.example.demo.model.dto.ClienteDireccionDTO;
import com.example.demo.model.dto.DireccionDTO;
import com.example.demo.service.ClienteService;
import com.example.demo.service.DireccionService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DireccionController {

    private static final Logger log = LoggerFactory.getLogger(DireccionController.class);

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/{idCliente}/direcciones")
    public ModelAndView findAllByCliente(@PathVariable Long idCliente) {
        log.info("Listando direcciones del cliente: " + idCliente);
        
        ClienteDTO clienteDTO = clienteService.findById(idCliente);
        List<ClienteDireccionDTO> listaClientesDireccionesDTO = direccionService.findAllByCliente(clienteDTO);
        
        return new ModelAndView("direcciones")
                .addObject("listaClientesDireccionesDTO", listaClientesDireccionesDTO)
                .addObject("clienteDTO", clienteDTO);
    }

    @GetMapping("/clientes/{idCliente}/direcciones/add")
    public ModelAndView add(@PathVariable Long idCliente) {
        log.info("Añadiendo nueva dirección al cliente: " + idCliente);
        
        ClienteDTO clienteDTO = clienteService.findById(idCliente);
        
        return new ModelAndView("direccionform")
                .addObject("direccionDTO", new DireccionDTO())
                .addObject("clienteDTO", clienteDTO);
    }

    @PostMapping("/clientes/{idCliente}/direcciones/save")
    public ModelAndView save(@PathVariable Long idCliente,
                             @ModelAttribute("direccionDTO") DireccionDTO direccionDTO) {
        log.info("Guardando dirección para el cliente: " + idCliente);
        
        ClienteDTO clienteDTO = clienteService.findById(idCliente);
        direccionDTO.getListaClientesDTO().add(clienteDTO);
        direccionService.save(direccionDTO, idCliente);
        
        return new ModelAndView("redirect:/clientes/{idCliente}/direcciones");
    }

    @GetMapping("/clientes/{idCliente}/direcciones/eliminar/{idDireccion}")
    public ModelAndView deleteById(@PathVariable Long idCliente, @PathVariable Long idDireccion) {
        log.info("Eliminando dirección con ID: " + idDireccion);
        
        direccionService.deleteById(idDireccion);
        
        return new ModelAndView("redirect:/clientes/{idCliente}/direcciones");
    }
    
    @GetMapping("/clientes/{idCliente}/direcciones/asignar")
    public ModelAndView asignar(@PathVariable Long idCliente) {
        log.info("Mostrando formulario para asignar dirección al cliente: " + idCliente);
        
        ClienteDTO clienteDTO = clienteService.findById(idCliente);
        List<DireccionDTO> direccionesDisponibles = direccionService.findAllDireccionesDisponibles();
        
        return new ModelAndView("asignarDireccion")
                .addObject("clienteDTO", clienteDTO)
                .addObject("direccionesDisponibles", direccionesDisponibles);
    }
    
    
    @PostMapping("/clientes/{idCliente}/direcciones/asignar")
    public ModelAndView asignarDireccionACliente(@PathVariable Long idCliente, @RequestParam Long direccionId) {
        log.info("Asignando dirección con ID: " + direccionId + " al cliente con ID: " + idCliente);
        
        direccionService.asignarDireccionACliente(idCliente, direccionId);
        
        return new ModelAndView("redirect:/clientes/{idCliente}/direcciones");
    }
}
