/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tipo_cambio.service;

import com.example.tipo_cambio.client.BancoGuatemalaClient;
import com.example.tipo_cambio.model.SolicitudCambio;
import com.example.tipo_cambio.repository.SolicitudCambioRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudCambioService {

    @Autowired
    private SolicitudCambioRepository repository;

    @Autowired
    private BancoGuatemalaClient bancoGuatemalaClient;

    public SolicitudCambio guardarCambioDesdeSOAP() {
        // Llama al cliente SOAP para obtener el tipo de cambio
        String tipoCambioStr = bancoGuatemalaClient.obtenerTipoCambio();
        Double tipoCambio = Double.valueOf(tipoCambioStr);

        // Crea y guarda una nueva solicitud
        SolicitudCambio solicitud = new SolicitudCambio();
        solicitud.setCambioMoneda(tipoCambio);
        solicitud.setFechaSolicitud(LocalDateTime.now());
        return repository.save(solicitud);
    }

    public List<SolicitudCambio> obtenerSolicitudes() {
        return repository.findAll();
    }
}
