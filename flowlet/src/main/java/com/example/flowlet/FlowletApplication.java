package com.example.flowlet;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flowlet API", version = "1.0", description = "Flowlet の REST API"))
public class FlowletApplication {

    static void main(String[] args) {
        SpringApplication.run(FlowletApplication.class, args);
    }

}
