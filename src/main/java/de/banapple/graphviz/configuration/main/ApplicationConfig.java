package de.banapple.graphviz.configuration.main;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
// @ImportResource( { "classpath*:/rest_config.xml" } )
@ComponentScan(basePackages = {
        "de.banapple.graphviz.configuration.main",
        "de.banapple.graphviz.controller" })
public class ApplicationConfig
{

    @Bean
    public static PropertyPlaceholderConfigurer properties()
    {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        final Resource[] resources = new ClassPathResource[] {
//                new ClassPathResource("persistence.properties"),
//                new ClassPathResource("restfull.properties") 
                };
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

}
