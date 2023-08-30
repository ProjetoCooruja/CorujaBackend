package br.ifba.cooruja.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.path.dev}")
    private String uploadPathDev;

    @Value("${upload.path.prod}")
    private String uploadPathProd;

    @Value("${sharing.path}")
    private String sharingPath;

    @Value("${spring.profiles.active}")
    private String profilesActive;

    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Testando o ambiente de execução.
        String uploadPath = uploadPathDev;
        if (profilesActive.toUpperCase().equals("PROD")){
            uploadPath = uploadPathProd;
        }

        // registry.addResourceHandler("/images/**")
        //         .addResourceLocations("file:/Users/othon/Documents/GitHub/CorujaBackend/backend/src/main/resources/images/");
        registry.addResourceHandler("/" + sharingPath + "/**")
                .addResourceLocations("file:"+ uploadPath + "/");
    }
}