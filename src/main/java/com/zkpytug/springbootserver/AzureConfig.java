package com.zkpytug.springbootserver;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfig {

    @Value("${spring.cloud.azure.keyvault.secret.credential.client-id}")
    private String clientId;

    @Value("${spring.cloud.azure.keyvault.secret.credential.client-secret}")
    private String clientSecret;

    @Value("${spring.cloud.azure.keyvault.secret.profile.tenant-id}")
    private String tenantId;

    @Bean
    public ClientSecretCredential clientSecretCredential() {
        return new ClientSecretCredentialBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .tenantId(tenantId)
                .build();
    }
}