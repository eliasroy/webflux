package com.webflux.webflux;

import com.webflux.webflux.models.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

@SpringBootApplication
public class WebfluxApplication implements CommandLineRunner {
	private static final Logger log = Logger.getLogger(WebfluxApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Flux<String> nombres = Flux.just("xdxd", "Pedro Fulano", "Maria Fulana", "Diego Sultano", "Juan " +
				"Mengano", "Bruce Lee")
				.map(nombre->new Usuario(nombre.toUpperCase(), ""))
				.filter(usuario -> usuario.getNombre().toLowerCase().contains("a"))
				.doOnNext(usuario -> {
					if (usuario == null) {
						throw new RuntimeException("Nombres no pueden ser vacíos");
					}
					System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido()));
				}).map(usuario -> usuario.getNombre().toLowerCase());

		nombres.subscribe(e -> log.info(e), error -> log.info(error.getMessage()), new Runnable() {
			@Override
			public void run() {
				log.info("Ha finalizado la ejecución del observable con éxito");
			}
		});

	}
}
