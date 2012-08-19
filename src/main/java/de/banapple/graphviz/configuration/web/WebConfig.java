package de.banapple.graphviz.configuration.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "de.banapple.graphviz.controller")
public class WebConfig{
   //
}
