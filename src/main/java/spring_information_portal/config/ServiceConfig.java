package spring_information_portal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"spring_information_portal.service", "spring_information_portal.tool"})
public class ServiceConfig {
}
