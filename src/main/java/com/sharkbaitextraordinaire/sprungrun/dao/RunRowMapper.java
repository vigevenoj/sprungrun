package com.sharkbaitextraordinaire.sprungrun.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sharkbaitextraordinaire.sprungrun.model.Run;

public class RunRowMapper implements RowMapper<Run> {

	@Override
	public Run mapRow(ResultSet rs, int rowNum) throws SQLException {
		Run run = new Run();
		run.setRdate(rs.getDate("rdate").toLocalDate());
		run.setTimeofday(rs.getString("timeofday"));
		run.setDistance(rs.getBigDecimal("distance"));
		run.setUnits(rs.getString("units"));
//		run.setElapsed(rs.getTime("elapsed"));
		run.setEffort(rs.getString("effort"));
		run.setComments(rs.getString("comments"));
		run.setShoeid(rs.getInt("shoeid"));
		return run;
	}

}
