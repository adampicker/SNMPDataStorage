package corp.netizen.datastore.security;

import corp.netizen.datastore.auth.CorsFilter;
import corp.netizen.datastore.auth.JWTAuthenticationFilter;
import corp.netizen.datastore.auth.JWTAuthorizationFilter;
import corp.netizen.datastore.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;


@Configuration
public class SpringSecurityConfig {


    @Bean
    static CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @EnableWebSecurity
    @Configuration
    @Order(1)
    public static class ClientSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationEntryPoint authEntryPoint;

        @Autowired
        private ApplicationUserService applicationUserService;
        @Autowired
        BCryptPasswordEncoder bCryptPasswordEncoder;


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    //.cors().and()
                    .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                    .antMatcher("/clients/**") // Add this
                    .httpBasic().and()
                    .exceptionHandling()
                    .authenticationEntryPoint(authEntryPoint).and().authorizeRequests()
                    .antMatchers("/clients/**").authenticated()
                    .and().csrf().disable();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("snmp")
                    .password(bCryptPasswordEncoder
                            .encode("snmp"))
                    .roles("CLIENT");
        }


    }

    @Configuration
    @EnableWebSecurity
    public static class UserSecurityConfig extends WebSecurityConfigurerAdapter {


        @Autowired
        private UserDetailsService userDetailsService;
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            System.out.println("here");
            http
                    //.cors().and()
                    .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/users/sign-up").permitAll()
                    .antMatchers("/users/data-stream").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                    .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        }

    }

//					.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().and()


}
