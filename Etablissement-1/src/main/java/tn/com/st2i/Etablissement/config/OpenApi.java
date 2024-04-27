package tn.com.st2i.Etablissement.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class OpenApi {

   // @Bean
   // public OpenApi customOpenAPI(@org.springframework.beans.factory.annotation.Value("${springdoc.version}") String appVersion, @Value("${spring.application.name-openapi}") String nameService) {
//        final String securitySchemeName = "bearerAuth";
//        final String apiTitle = String.format("%s ", StringUtils.capitalize(nameService.concat("API")));
//        return new OpenApi().addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//                .components(
//                        new Components().addSecuritySchemes(securitySchemeName,
//                                new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer").bearerFormat("JWT")))
//                .info(new Info().title(apiTitle).version(appVersion));
//    }


}
