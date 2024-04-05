package ra.webmovieapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ra.webmovieapp.model.enums.ERoleName;
import ra.webmovieapp.security.Jwt.AccessDenied;
import ra.webmovieapp.security.Jwt.JwtEntryPoint;
import ra.webmovieapp.security.Jwt.JwtTokenFilter;
import ra.webmovieapp.security.UserDetail.UserDetailService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtEntryPoint jwtEntryPoint;
    private final JwtTokenFilter jwtTokenFilter;
    private final AccessDenied accessDenied;
    private final UserDetailService userDetailService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/v1/auth/**").permitAll()
                                .requestMatchers("/v1/permit/**").permitAll()
                                .requestMatchers("/v1/account/**").hasAnyAuthority(
                                        String.valueOf(ERoleName.ROLE_ADMIN),
                                        String.valueOf(ERoleName.ROLE_USER))
                                .requestMatchers("/v1/admin/**").hasAnyAuthority(String.valueOf(ERoleName.ROLE_ADMIN))
                                .requestMatchers("/v1/user/**").hasAnyAuthority(String.valueOf(ERoleName.ROLE_USER))
                                .anyRequest().authenticated()
                ).
                exceptionHandling(
                        (auth) -> auth.authenticationEntryPoint(jwtEntryPoint)
                                .accessDeniedHandler(accessDenied)
                )
                .sessionManagement((auth) -> auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class).
                build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}