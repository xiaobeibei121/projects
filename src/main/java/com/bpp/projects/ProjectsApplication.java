package com.bpp.projects;

import com.squareup.okhttp.OkHttpClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.bpp")
public class ProjectsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectsApplication.class, args);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}



