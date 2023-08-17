package br.ifba.cooruja.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${sharing.path}")
    private String sharingPath;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/images/**")
        //         .addResourceLocations("file:/Users/othon/Documents/GitHub/CorujaBackend/backend/src/main/resources/images/");
            registry.addResourceHandler("/" + sharingPath + "/**")
                .addResourceLocations("file:"+ uploadPath + "/");
    }
}