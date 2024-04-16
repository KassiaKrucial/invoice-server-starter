package cz.itnetwork.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This configuration class adds CORS mapping
 * Allows access to resources from external third parties
 *
 * @author ITnetwork
 * @author The author of Javadoc for this class is Kat
 */
@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Defines global CORS configurations:
     *      adds mapping to `/**`
     *      allows http methods: HEAD, GET, POST, PUT, DELETE, OPTIONS
     *      all credentials are true
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
