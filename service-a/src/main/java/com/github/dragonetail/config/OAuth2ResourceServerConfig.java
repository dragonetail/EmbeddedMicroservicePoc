package com.github.dragonetail.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.*;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().authorizeRequests()
                // .access("#oauth2.hasScope('readScope')")
                .anyRequest().permitAll();

    }

    // JWT token store

//    @Override
//    public void configure(final ResourceServerSecurityConfigurer config) {
//        config.tokenServices(tokenServices());
////        configurer
////                .resourceId(applicationProperties.getAuth().getResourceId())
////                .tokenServices(tokenServices)
////                .tokenStore(tokenStore);
//    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("12345678");
        //converter.setJwtClaimsSetVerifier(jwtClaimsSetVerifier());

        return converter;
    }

//    @Bean
//    public JwtClaimsSetVerifier jwtClaimsSetVerifier() {
//        return new DelegatingJwtClaimsSetVerifier(Arrays.asList(issuerClaimVerifier()));
//    }
//
//    @Bean
//    public JwtClaimsSetVerifier issuerClaimVerifier() {
//        try {
//            return new IssuerClaimVerifier(new URL("http://localhost:8081"));
//        } catch (final MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        return defaultTokenServices;
//    }
}
