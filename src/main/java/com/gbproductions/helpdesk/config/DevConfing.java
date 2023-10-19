package com.gbproductions.helpdesk.config;

import com.gbproductions.helpdesk.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfing {

    @Autowired
    private DBservice dBservice;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean instaciaDB() {
        if (value.equals("create")) {
            this.dBservice.instanciaDB();
        }
        return false;
    }
}
