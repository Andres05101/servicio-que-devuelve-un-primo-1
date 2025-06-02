package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class Operaciones {

    private final Map<Integer, String> estados = new ConcurrentHashMap<>();
    private final Map<Integer, Long> resultados = new ConcurrentHashMap<>();

    public int iniciarProceso(int cantidadDigitos) {
        int pid = generarPid();
        estados.put(pid, "en espera");

        new Thread(() -> {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
            long primo = generarPrimo(cantidadDigitos);
            resultados.put(pid, primo);
            estados.put(pid, "terminado");
        }).start();

        return pid;
    }

    public String obtenerEstado(int pid) {
        String estado = estados.get(pid);
        if (estado == null) {
            return "No existe un proceso con PID: " + pid;
        }

        if ("en espera".equals(estado)) {
            return "En espera...";
        }

        long numero = resultados.get(pid);
        return "El número " + numero + " generado es primo.";
    }

    private int generarPid() {
        return ThreadLocalRandom.current().nextInt(1000, 10000);
    }

    public long generarPrimo(int cantidadDigitos) {
        long numero = generarAleatorio(cantidadDigitos);
        return esPrimo(numero) ? numero : generarPrimo(cantidadDigitos);
    }

    public long generarAleatorio(int cantidadDigitos) {
        if (cantidadDigitos < 1 || cantidadDigitos > 18) {
            throw new IllegalArgumentException("La cantidad de dígitos debe estar entre 1 y 18.");
        }

        long minimo = (long) Math.pow(10, cantidadDigitos - 1);
        long maximo = (long) Math.pow(10, cantidadDigitos) - 1;

        return ThreadLocalRandom.current().nextLong(minimo, maximo + 1);
    }

    public boolean esPrimo(long numero) {
        if (numero < 2) return false;
        if (numero == 2) return true;
        if (numero % 2 == 0) return false;

        long raiz = (long) Math.sqrt(numero);
        for (long i = 3; i <= raiz; i += 2) {
            if (numero % i == 0) return false;
        }
        return true;
    }
}