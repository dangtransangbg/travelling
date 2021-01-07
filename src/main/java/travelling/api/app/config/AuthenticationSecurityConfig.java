package travelling.api.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import travelling.api.app.translate.ResponseUtils;
import travelling.api.security.AuthenticationFilter;
import travelling.api.security.TokenAuthenticator;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class AuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticator tokenAuthenticator;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ResponseUtils responseUtils;

    public AuthenticationSecurityConfig() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .and()
                .anonymous()
                .and()
                .servletApi()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/favicon.ico")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/users")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/admin/auth")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/login")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/tour/**")
                .permitAll()
                .and()
                .headers()
                .cacheControl();
        http.authorizeRequests().anyRequest().permitAll();

        http.addFilterBefore(new AuthenticationFilter(tokenAuthenticator, objectMapper, responseUtils), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/images/**","/ckeditor/**","/ckfinder/**","/media/**","/js/**","/css/**").anyRequest();
    }

}
