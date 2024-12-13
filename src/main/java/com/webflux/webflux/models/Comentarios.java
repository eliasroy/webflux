package com.webflux.webflux.models;

import java.util.List;

public class Comentarios {
    private List<String> comentarios;
    public Comentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public void addComentario(String comentario) {
        this.comentarios.add(comentario);
    }
    @Override
    public String toString() {
        return "Comentarios{" +
                "comentarios=" + comentarios +
                '}';
    }


}
