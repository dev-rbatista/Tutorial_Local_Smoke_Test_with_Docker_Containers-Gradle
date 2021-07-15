package switchtwentytwenty.project.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

//Code adapted from https://www.javainuse.com/spring/boot-jwt and https://www.javainuse.com/spring/boot-jwt-mysql

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JWTFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable().cors().disable()
                // dont authenticate this particular request
                //.authorizeRequests().anyRequest().permitAll().and()
                .authorizeRequests().antMatchers("/authenticate", "/categories").permitAll()
/*                .antMatchers(HttpMethod.POST, "/people", "/families/{familyID}/relations/", "/families/{familyID}/categories", "/people/{personID}/emails", "/accounts").hasAuthority("familyAdministrator")
                .antMatchers(HttpMethod.GET, "/people/{personID}", "/families/{familyID}/relations/", "/families/{familyID}/categories", "/people/{personID}/emails", "/accounts/{accountID}", "/families/{familyID}", "/families/{familyID}/categories/{categoryID}").hasAuthority("familyAdministrator")
                .antMatchers(HttpMethod.OPTIONS, "/families", "/families/{familyID}", "/families/{familyID}/categories", "/people", "people/{personID}").hasAuthority("familyAdministrator")
                .antMatchers(HttpMethod.DELETE, "/people/{personID/emails/{email}").hasAuthority("familyAdministrator")
                .antMatchers(HttpMethod.PATCH, "/families/{familyID}/relations/{relationID}").hasAuthority("familyAdministrator")
                .antMatchers(HttpMethod.POST, "/families", "/categories").hasAuthority("systemManager")
                .antMatchers(HttpMethod.GET, "/categories/all", "/categories/{categoryID}").hasAuthority("systemManager")
                .antMatchers(HttpMethod.OPTIONS, "/categories", "/families").hasAuthority("systemManager")
                .antMatchers(HttpMethod.POST, "/people/{personID}/emails", "/accounts").hasAuthority("familyMember")
                .antMatchers(HttpMethod.GET, "/people/{personID}", "/accounts/{accountID}").hasAuthority("familyMember")
                .antMatchers(HttpMethod.OPTIONS,"/people", "/people/{personID}").hasAuthority("familyMember")
                .antMatchers(HttpMethod.DELETE, "/people/{personID/emails/{email}").hasAuthority("familyMember")*/
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll().
                // all other requests need to be authenticated
                        anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
