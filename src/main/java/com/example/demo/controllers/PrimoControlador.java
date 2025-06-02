package com.example.demo.controllers;

import com.example.demo.service.Operaciones;
import org.springframework.web.bind.annotation.*;

@RestController
public class PrimoControlador {

    private final Operaciones operaciones;

    public PrimoControlador(Operaciones operaciones) {
        this.operaciones = operaciones;
    }

    @GetMapping("/servicio/{digitos}")
    public String iniciarProceso(@PathVariable int digitos) {
        if (digitos < 1 || digitos > 18) {
            return "La cantidad de dígitos debe estar entre 1 y 18.";
        }

        int pid = operaciones.iniciarProceso(digitos);
        return "Proceso iniciado con PID: " + pid;
    }

    @GetMapping("/servicio/status/{pid}")
    public String obtenerEstado(@PathVariable int pid) {
        return operaciones.obtenerEstado(pid);
    }
}
