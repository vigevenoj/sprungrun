package com.sharkbaitextraordinaire.sprungrun;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sharkbaitextraordinaire.sprungrun.model.Run;

@SpringBootApplication
public class SprungrunApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SprungrunApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(SprungrunApplication.class, args);
	}


	// this is a test class to print any runs
	@Override
	public void run(String... strings) throws Exception {
		log.info("querying for runs");
		jdbcTemplate.query(
				"select "
				+ "rdate, timeofday, distance, units, elapsed, effort, comment, shoeid "
				+ "from runs "
				+ "where rdate >= '2014-12-01'", 
				(rs, rowNum) -> { 
				Run run = new Run();
				run.setRdate(rs.getDate("rdate").toLocalDate());
				run.setTimeofday(rs.getString("timeofday"));
				run.setDistance(rs.getBigDecimal("distance"));
				run.setUnits(rs.getString("units"));
				run.setElapsed(rs.getTime("elapsed"));
				run.setEffort(rs.getString("effort"));
				run.setComment(rs.getString("comment"));
				run.setShoeid(rs.getInt("shoeid"));
				return run;
				}).forEach(run -> log.info(run.toString()));
		log.info("done printing run info");
	}
	
	
}
