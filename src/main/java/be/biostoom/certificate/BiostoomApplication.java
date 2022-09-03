package be.biostoom.certificate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Biostoom Api", version = "3.0", description = "Description"))
public class BiostoomApplication {
	public static void main(String[] args) {
		SpringApplication.run(BiostoomApplication.class, args);
	}
}
