package com.example.todos.config

import com.example.todos.service.user.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore


@Configuration
@EnableAuthorizationServer
class AuthServerConfig(
        val authenticationManager: AuthenticationManager,
        val userService: UserService,
        val passwordEncoder: PasswordEncoder
) : AuthorizationServerConfigurerAdapter() {

    @Bean
    fun defaultAccessTokenConverter(): JwtAccessTokenConverter? {
        val converter = JwtAccessTokenConverter()
        converter.setSigningKey("123")
        return converter
    }

    @Bean
    fun tokenStore(): TokenStore? {
        return JwtTokenStore(defaultAccessTokenConverter())
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        if (security == null) return
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        if (clients == null) return
        clients
                .inMemory()
                .withClient("fooClientId")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("read", "write")
                .resourceIds("api")
                .autoApprove(true)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        if (endpoints == null) return
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(defaultAccessTokenConverter())
                .userDetailsService(userService)
    }
}