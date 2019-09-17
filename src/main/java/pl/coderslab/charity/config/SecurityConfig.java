package pl.coderslab.charity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {

        authenticationMgr.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select email, password, enabled from user where username=?")
                .authoritiesByUsernameQuery("select email, authority from user_authorities where username=?");
/*        authenticationMgr.inMemoryAuthentication()
                .withUser("devuser").password("{noop}dev").authorities("ROLE_USER")
                .and()
                .withUser("adminuser").password("{noop}admin").authorities("ROLE_USER", "ROLE_ADMIN");*/
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/donation*").hasRole("USER")
                .antMatchers("/admin*").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .and()
                .httpBasic();

    }

}
