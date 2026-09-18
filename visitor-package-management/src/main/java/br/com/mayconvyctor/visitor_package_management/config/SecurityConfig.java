package br.com.mayconvyctor.visitor_package_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilitamos o CSRF temporariamente para facilitar a integração local com o JSF
                .csrf(csrf -> csrf.disable())

                // Regras de Autorização
                .authorizeHttpRequests(auth -> auth
                        // Libera o acesso à tela de login e aos arquivos internos do PrimeFaces (CSS/JS)
                        .requestMatchers("/login.xhtml", "/javax.faces.resource/**", "/jakarta.faces.resource/**").permitAll()
                        // Qualquer outra página (index, moradores, encomendas) exige que o usuário esteja logado
                        .anyRequest().authenticated()
                )

                // Configuração do formulário de Login
                .formLogin(form -> form
                        .loginPage("/login.xhtml") // Diz ao Spring qual é a nossa tela visual
                        .defaultSuccessUrl("/index.xhtml", true) // Para onde ir se a senha estiver certa
                        .permitAll()
                )

                // Configuração de Logout (Sair)
                .logout(logout -> logout
                        .logoutSuccessUrl("/login.xhtml")
                        .permitAll()
                );

        return http.build();
    }

    /**
     * Cria um usuário temporário em memória para podermos logar.
     * Numa aplicação real, este método buscaria o usuário no banco de dados.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123456")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}