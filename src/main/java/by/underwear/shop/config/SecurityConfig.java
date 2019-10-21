package by.underwear.shop.config;

import by.underwear.shop.domain.UserRole;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                        .antMatchers("/admin/**").hasRole(UserRole.ADMIN.getAuthorityName())
                        .anyRequest().permitAll()
                        .and()
                .formLogin()
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/j_spring_security_check")
                        .defaultSuccessUrl("/admin/dashboard")
                        .failureUrl("/admin/login?error")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                        .and()
                .logout()
                        .logoutUrl("/j_spring_security_logout")
                        .logoutSuccessUrl("/admin/login?logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                        .and()
                .csrf().disable();
    }
}