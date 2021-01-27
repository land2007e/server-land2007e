package com.t3h.server.securety

import com.t3h.server.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
open class SecurityConfiguration : WebSecurityConfigurerAdapter   {
    private val userRepository: UserRepository

    @Autowired
    constructor(userRepository: UserRepository) {
        println(".................................................")
        this.userRepository = userRepository

    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .exceptionHandling()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        val aut = AuthorizationFilter( AntPathRequestMatcher("/api/**"), userRepository)
        aut.setAuthenticationManager(
                authenticationManagerBean()
        )
        http.addFilterBefore(aut, UsernamePasswordAuthenticationFilter::class.java)

    }

    @Bean
    open fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }
}