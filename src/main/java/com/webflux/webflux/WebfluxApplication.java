package com.webflux.webflux;

import com.webflux.webflux.models.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

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

		// Ejemplo de uso de Flux
		ejemploFlatMap();

	}
	public void ejemploFlatMap(){
		List<String> usuariosList = List.of("xdxd", "Pedro Fulano", "Maria Fulana", "Diego Sultano", "Juan " +
				"Mengano", "Bruce Lee");
		       Flux.fromIterable(usuariosList)
		        .map(nombre->new Usuario(nombre.toUpperCase(), ""))
				.filter(usuario -> usuario.getNombre().toLowerCase().contains("a"))
				.map(usuario -> usuario.getNombre().toLowerCase())
		       .subscribe(e -> log.info(e));
	}
}
