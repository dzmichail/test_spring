package myapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.Resource;

@Configuration
public class LoggerConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Resource(name = "fileEventLogger")
    private IEventLogger fileEventLogger;


}
