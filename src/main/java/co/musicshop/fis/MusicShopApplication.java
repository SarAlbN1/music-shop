package co.musicshop.fis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class MusicShopApplication {
    @Bean

    public ViewResolver viewResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setPrefix("/WEB-INF/views/");

        resolver.setSuffix(".html");

        return resolver;

    }

    public static void main(String[] args) {
        SpringApplication.run(MusicShopApplication.class, args);
    }

}
