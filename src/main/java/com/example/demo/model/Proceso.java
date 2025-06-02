package com.example.demo.model;

/**
 *
 * @author Micha
 */
public class Proceso {
    private boolean terminado;
    private long resultado;

    public Proceso() {
        this.terminado = false;
        this.resultado = 0;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public long getResultado() {
        return resultado;
    }

    public void setResultado(long resultado) {
        this.resultado = resultado;
    }
}