package com.webflux.webflux;

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
		Flux<String> nombres = Flux.just("", "Pedro Fulano", "Maria Fulana", "Diego Sultano", "Juan " +
				"Mengano", "Bruce Lee").doOnNext(e->{
			if(e.isEmpty()){
				throw new RuntimeException("Nombre vacio");
			}
			System.out.println(e);
		});
		nombres.subscribe(e -> log.info(e),error->log.info(error.getMessage()));

	}
}
