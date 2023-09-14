package com.tonde.maisonchapback.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Maison Chap API",
                version = "1.0",
                description = "Maison Chap API Documentation"
        ),
        servers = {
                @io.swagger.v3.oas.annotations.servers.Server(
                        description = "Localhost",
                        url = "http://localhost:8080"
                ),
                @io.swagger.v3.oas.annotations.servers.Server(
                        description = "CloudCluster",
                        url = "http://localhost:8080"
                )
        },

        security = {
                @io.swagger.v3.oas.annotations.security.SecurityRequirement(
                        name = "bearerAuth",
                        scopes = {"global"}
                ),
        }


)
public class OpenApiConfig {
}
