package com.mmhealth.MiIngresoBack.config;

import com.mmhealth.MiIngresoBack.dto.response.CustomApiResponse;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    static {
        // Configuración para que Swagger reconozca ApiResponse como genérico
        SpringDocUtils.getConfig().replaceWithSchema(CustomApiResponse.class, new Schema<CustomApiResponse>()
                .addProperty("status", new StringSchema().example("success"))
                .addProperty("message", new StringSchema().example("Operación exitosa"))
                .addProperty("data", new Schema()));
    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Mi Ingreso")
                        .version("1.0")
                        .description("API para la gestión de ingresos de colaboradores")
                        .contact(new Contact()
                                .name("Soporte Técnico")
                                .email("soporte@ecuasanitas.com")));
    }
}