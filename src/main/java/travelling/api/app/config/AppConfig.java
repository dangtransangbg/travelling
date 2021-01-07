package travelling.api.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import travelling.api.app.interceptor.HttpLoggingInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@EnableWebMvc
@Configuration
@Slf4j
public class AppConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    private static final List<Locale> LOCALES = Arrays.asList(
            new Locale("vi"),
            new Locale("en"));

    @Value("${message.default.locale}")
    private String defaultLocale;


    @Autowired
    private HttpLoggingInterceptor httpLoggingInterceptor;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ROOT);
        return slr;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename("i18n/messages");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headerLang = request.getHeader("Accept-Language");
        Locale myLocale = headerLang == null || headerLang.isEmpty()
                ? new Locale(defaultLocale)
                : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
        log.info("Locale process {}", myLocale.getLanguage());
        return myLocale;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(httpLoggingInterceptor);
    }

}
