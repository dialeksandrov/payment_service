package kg.aleksandrov.paymentservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author dialeksandrov
 * @created 15/05/2022
 **/
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("kg.aleksandrov.paymentservice.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData(){
        return new ApiInfo("Test project for NurTelecom",
                "Payment Service",
                "1.0", "Free to use",
                new Contact("Dmitrii Aleksandrov", "https://www.linkedin.com/in/dmitrii-aleksandrov-a24252214/", "dialeksandrov12@gmail.com"),
                "Api License",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }
}
