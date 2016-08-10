package com.sharkbaitextraordinaire.sprungrun;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class SprungrunApplication {

    private static final Logger log = LoggerFactory.getLogger(SprungrunApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(SprungrunApplication.class, args);
	}
}
