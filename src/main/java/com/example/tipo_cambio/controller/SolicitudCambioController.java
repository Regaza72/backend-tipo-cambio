/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tipo_cambio.controller;

import com.example.tipo_cambio.model.SolicitudCambio;
import com.example.tipo_cambio.service.SolicitudCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class SolicitudCambioController {

    @Autowired
    private SolicitudCambioService service;

//    @GetMapping("/solicitudes")
//    public List<SolicitudCambio> obtenerSolicitudes() {
//        return service.obtenerSolicitudes();
//    }

 
    
    
    @GetMapping("/obtener-tipo-cambio")
    public SolicitudCambio obtenerYGuardarTipoCambio() {
    return service.guardarCambioDesdeSOAP();
    }

}
