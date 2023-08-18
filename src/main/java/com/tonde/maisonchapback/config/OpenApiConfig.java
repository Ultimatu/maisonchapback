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
        tags = {
                @io.swagger.v3.oas.annotations.tags.Tag(
                        name = "User",
                        description = "User API"
                ),
                @io.swagger.v3.oas.annotations.tags.Tag(
                        name = "House",
                        description = "House API"
                ),
                @io.swagger.v3.oas.annotations.tags.Tag(
                        name = "Rate",
                        description = "Rate API"
                ),
                @io.swagger.v3.oas.annotations.tags.Tag(
                        name = "Stat",
                        description = "Stat API"
                )
        },
        security = {
                @io.swagger.v3.oas.annotations.security.SecurityRequirement(
                        name = "bearerAuth",
                        scopes = {"global"}
                )
        },

        extensions = {
                @io.swagger.v3.oas.annotations.extensions.Extension(
                        name = "x-internal-id",
                        properties = {
                                @io.swagger.v3.oas.annotations.extensions.ExtensionProperty(
                                        name = "id",
                                        value = "123456789"
                                )
                        }
                )
        }


)
public class OpenApiConfig {
}
