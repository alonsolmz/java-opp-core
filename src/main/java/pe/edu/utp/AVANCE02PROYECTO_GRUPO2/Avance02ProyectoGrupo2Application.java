package pe.edu.utp.AVANCE02PROYECTO_GRUPO2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Avance02ProyectoGrupo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Avance02ProyectoGrupo2Application.class, args);
        System.out.println("¡Aplicación iniciada en http://localhost:8080!");
        System.out.println("Consola H2 disponible en http://localhost:8080/h2-console");
	}

}
