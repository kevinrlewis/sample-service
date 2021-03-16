package sampleservice.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Log4j2
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) {
        // The matchers below are generally only needed for static web content.
        web.ignoring()
                .antMatchers("/error")
                .antMatchers("/actuator/**")
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/bower_components/**")
                .antMatchers("/i18n/**")
                .antMatchers("/test/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/content/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().exceptionHandling()
                    .and()
                .headers().frameOptions().disable()
                    .and()
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                // Uncomment to allow all traffic
                .antMatchers("/api/**").permitAll()
//                .anyRequest().authenticated()
//                    .and()
//                .oauth2ResourceServer().jwt()
                ;
    }
}
