/**
 * 
 */
package com.fhzz.springbootdemo.configuration.fileupload;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 *
 */
@Configuration
public class FileUploadConfig {
 
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("100MB"); 
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }
}