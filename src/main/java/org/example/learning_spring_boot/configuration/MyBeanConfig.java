package org.example.learning_spring_boot.configuration;

import org.example.test.MyTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.test")
public class MyBeanConfig {
    @Bean
    public String myBean(){
        return "MyBean";
    }
    MyTest myTest;
}
