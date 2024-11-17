package com.webflux.webflux.models;

public class UsuarioCOmentarios {
    private Usuario usuario;
    private Comentarios comentarios;

    public UsuarioCOmentarios() {
    }
    public UsuarioCOmentarios(Usuario usuario, Comentarios comentarios) {
        this.usuario = usuario;
        this.comentarios = comentarios;
    }
    @Override
    public String toString() {
        return "UsuarioCOmentarios{" +
                "usuario=" + usuario +
                ", comentarios=" + comentarios +
                '}';
    }
}
