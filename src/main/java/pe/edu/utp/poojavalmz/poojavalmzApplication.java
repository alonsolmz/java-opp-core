package pe.edu.utp.poojavalmz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class poojavalmzApplication {

	public static void main(String[] args) {
		SpringApplication.run(poojavalmzApplication.class, args);
        System.out.println("¡Aplicación iniciada en http://localhost:8081!");
        System.out.println("Consola H2 disponible en http://localhost:8081/h2-console");
	}

}
