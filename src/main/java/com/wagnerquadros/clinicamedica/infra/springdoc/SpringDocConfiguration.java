package com.wagnerquadros.clinicamedica.infra.springdoc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
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
                        .title("Api Clínica Médica")
                        .description("API REST para gerenciamento de uma clínica médica. " +
                                "O projeto permite cadastrar, listar, atualizar e excluir logicamente médicos e pacientes, " +
                                "além de agendar e cancelar consultas.")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Wagner Quadros")
                                .email("email@email.com"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório GitHub")
                        .url("https://github.com/wagnerquadros/clinica_medica_API"));
    }
}
