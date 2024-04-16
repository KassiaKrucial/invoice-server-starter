package cz.itnetwork.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>This configuration class adds CORS mapping</p>
 * <p>Allows access to resources from external third parties</p>
 *
 * @author ITnetwork
 * @author The author of Javadoc for this class is Kat
 */
@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * <p>Defines global CORS configurations:</p>
     * <p>     adds mapping to `/**`</p>
     * <p>     allows http methods: HEAD, GET, POST, PUT, DELETE, OPTIONS</p>
     * <p>     all credentials are true</p>
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedOriginPatterns("**")
                .allowCredentials(true);
    }
}
