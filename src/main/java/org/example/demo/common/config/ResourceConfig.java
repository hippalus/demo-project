package org.example.demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

  @Bean
  public ResourceBundleMessageSource messageSource() {
    var source = new ResourceBundleMessageSource();
    source.setBasenames("i18n/asset");
    source.setDefaultEncoding("UTF-8");
    return source;
  }

}