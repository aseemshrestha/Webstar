package com.webstar.webmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@SpringBootApplication
@ComponentScan( basePackages = "com.webstar" ) //tell springboot to where to start scanning for files
@EntityScan( basePackages = "com.webstar.models" )
@EnableJpaRepositories( basePackages = "com.webstar.repository" )
@EnableAutoConfiguration
public class WebstarApplication extends SpringBootServletInitializer
{

    public static void main(String[] args)
    {
        SpringApplication.run(WebstarApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(WebstarApplication.class);
    }

    /* tiles configuration */
    @Bean
    public UrlBasedViewResolver tilesViewResolver()
    {
        UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);
        return tilesViewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer()
    {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        String[] defs = { "/WEB-INF/tiles.xml" };
        tilesConfigurer.setDefinitions(defs);
        return tilesConfigurer;
    }
}
