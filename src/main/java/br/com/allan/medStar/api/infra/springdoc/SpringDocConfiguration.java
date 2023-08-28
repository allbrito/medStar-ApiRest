package br.com.allan.medStar.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                            .addSecuritySchemes("bearer-key",
                                    new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                            .info(new Info()
                                    .title("MedStar API")
                                    .description("API Rest da aplicação de MedStar, que tem as funcionalidades de CRUD de Médicos e Pacientes, além de agendamentos e cancelamentos")
                                    .contact(new Contact()
                                            .name("Allan Brito")
                                            .email("allbdesenvolvedor04@gmail.com")
                                            .url("http://github.com/allbrito")));
    }
}
