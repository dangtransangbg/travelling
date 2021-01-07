package travelling.api.app.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import travelling.api.app.filter.CustomSiteMeshFilter;

@Configuration
public class ServletInit extends SpringBootServletInitializer {
    @Bean
    public FilterRegistrationBean siteMeshFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CustomSiteMeshFilter());
        return filterRegistrationBean;
    }

}
