package nl.novi.autogarage.Security;

import nl.novi.autogarage.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(userDetailsService);
        return new ProviderManager(auth);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/users/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/admedewerkers").authenticated()
                        .requestMatchers(HttpMethod.GET, "/keuringen").authenticated()
                        .requestMatchers(HttpMethod.POST, "/keuringen").authenticated()
                        .requestMatchers(HttpMethod.GET, "/keuringen/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/keuringen/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/monteurs").authenticated()
                        .requestMatchers(HttpMethod.POST, "/onderdelen").authenticated()
                        .requestMatchers(HttpMethod.GET, "/onderdelen/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/onderdelen/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/autos").authenticated()
                        .requestMatchers(HttpMethod.POST, "/autos").authenticated()
                        .requestMatchers(HttpMethod.GET, "/bomedwerkers").authenticated()
                        .requestMatchers(HttpMethod.POST, "/bomedewerkers").authenticated()
                        .requestMatchers(HttpMethod.GET, "/kassamedewerkers").authenticated()
                        .requestMatchers(HttpMethod.POST, "/kassamedewerkers").authenticated()
                        .requestMatchers(HttpMethod.GET, "/klanten").authenticated()
                        .requestMatchers(HttpMethod.POST, "/klanten").authenticated()


                        .requestMatchers("/**").permitAll()
                        .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}
