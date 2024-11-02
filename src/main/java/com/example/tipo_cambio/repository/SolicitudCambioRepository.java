/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tipo_cambio.repository;

import com.example.tipo_cambio.model.SolicitudCambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudCambioRepository extends JpaRepository<SolicitudCambio, Long> {
}
