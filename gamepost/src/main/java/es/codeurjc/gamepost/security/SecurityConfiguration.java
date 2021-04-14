package es.codeurjc.gamepost.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    //private CustomUserAuthenticationProvider authenticationProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // TODO: configure password encoder.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        //auth.authenticationProvider(authenticationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // TODO: Assign public pages.
        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/signin").permitAll();
        //http.authorizeRequests().antMatchers("/").permitAll();
        //http.authorizeRequests().antMatchers("/").permitAll();
        
        //http.authorizeRequests().anyRequest().authenticated();    //All public

        // TODO: Assign private pages.
        // TODO: Assign roles.
        // Private pages
        //http.authorizeRequests().antMatchers("/").hasAnyRole("USER");   // Testing
        http.authorizeRequests().antMatchers("/notifications").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/game/newgame").hasAnyRole("ADMIN");

        // Login form
        http.formLogin().loginPage("/login").permitAll();
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/loginSuccess", true);
        http.formLogin().failureUrl("/login").permitAll();

        // TODO: Define propper logout.
        // Logout
        http.logout().logoutUrl("/logout").permitAll();
        http.logout().logoutSuccessUrl("/").permitAll();

        // Disable CSRF at the moment
        http.csrf().disable();
    }
}