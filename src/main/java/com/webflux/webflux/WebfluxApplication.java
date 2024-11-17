package com.webflux.webflux;

import com.webflux.webflux.models.Comentarios;
import com.webflux.webflux.models.Usuario;
import com.webflux.webflux.models.UsuarioCOmentarios;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class WebfluxApplication implements CommandLineRunner {
	private static final Logger log = Logger.getLogger(WebfluxApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ejemploUsuarioComentariosFlatMap();

	}
	public void ejemploFlatMap(){
		List<String> usuariosList = List.of("xdxd", "Pedro Fulano", "Maria Fulana", "Diego Sultano", "Juan " +
				"Mengano", "Bruce Lee");
		       Flux.fromIterable(usuariosList)
		        .map(nombre->new Usuario(nombre.toUpperCase(), ""))
				.flatMap(usuario -> {
					if (usuario.getNombre().equalsIgnoreCase("bruce lee")) {
						return Flux.just(usuario, new Usuario(usuario.getNombre().toLowerCase(), ""));
					} else {
						return Flux.just(usuario);
					}
				})
				.map(usuario -> usuario.getNombre().toLowerCase())
		       .subscribe(e -> log.info(e));
	}

	public void ejemploUsuarioComentariosFlatMap(){
		Mono<Usuario> usuarios = Mono.fromCallable(() -> crearUusuarios());
		Mono<Comentarios> comentarios = Mono.fromCallable(() -> crearComentarios());

		usuarios.flatMap(u -> comentarios.map(c -> crearUsuarioCOmentario(u, c)))
				.subscribe(uc -> log.info(uc.toString()));

	}

	public Comentarios crearComentarios(){
		List<String> comentarioss = List.of("Comentario 1", "Comentario 2", "Comentario 3");
		Comentarios comentarios = new Comentarios(comentarioss);

		return comentarios;
	}
	public Usuario crearUusuarios(){
		return new Usuario("John", "Doe");
	}
	public UsuarioCOmentarios crearUsuarioCOmentario(Usuario usuario, Comentarios comentarios){
		return new UsuarioCOmentarios(usuario, comentarios);
	}
}
