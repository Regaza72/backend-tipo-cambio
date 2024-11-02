/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tipo_cambio.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SolicitudCambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaSolicitud;

    private Double cambioMoneda;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Double getCambioMoneda() {
        return cambioMoneda;
    }

    public void setCambioMoneda(Double cambioMoneda) {
        this.cambioMoneda = cambioMoneda;
    }
}
