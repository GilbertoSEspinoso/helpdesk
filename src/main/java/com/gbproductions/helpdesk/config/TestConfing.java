package com.gbproductions.helpdesk.config;

import com.gbproductions.helpdesk.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfing {

    @Autowired
    private DBservice dBservice;

    @Bean
    public void instaciaDB() {
        this.dBservice.instanciaDB();
    }
}
