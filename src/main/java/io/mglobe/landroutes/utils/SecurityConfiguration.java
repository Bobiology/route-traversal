package io.mglobe.landroutes.utils;

import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import io.mglobe.landroutes.service.LandRouteService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	public static final Logger LOG = LogManager.getLogger(SecurityConfiguration.class);

	String timelog = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new java.util.Date());
	
	//@Autowired
	//LandRouteService landRoute;
	@Value("${spring.security.user.name}")
	private String basicAuthUsername;
	
	@Value("${spring.security.user.password}")
	private String basicAuthPassword;
	
	@Value("${spring.security.user.roles}")
	private String basicAuthRoles;

	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	LOG.info(timelog+" : Basic Auth Username: "+basicAuthUsername);
        auth.inMemoryAuthentication()
          .withUser(basicAuthUsername+"").password(passwordEncoder().encode(basicAuthPassword+""))
          .roles(basicAuthRoles);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
          .authorizeRequests()
          .anyRequest().authenticated()
          .and()
          .httpBasic();

        http.addFilterAfter(new CustomSecurityHeaderFilter(),
          BasicAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}