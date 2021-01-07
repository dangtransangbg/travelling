package travelling.api.app.config.CKConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration(value = "WebMvcCkfinder")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //   file:D:\\data\\file\\image\\
        registry.addResourceHandler("/public/image/**").addResourceLocations("/templates/userfiles/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
         registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/ckfinder/**").addResourceLocations("/templates/ckfinder/");
        registry.addResourceHandler("/common/**").addResourceLocations("/common/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");

        super.addResourceHandlers(registry);
    }
}
