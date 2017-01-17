package com.sharkbaitextraordinaire.sprungrun.dao;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;
import java.util.Locale;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sharkbaitextraordinaire.sprungrun.model.Run;

@Component
public class RunDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static String SELECT_RUNS = "select rdate, timeofday, distance, units, elapsed, effort, comments, shoeid from runs";
	private static String BY_DATE = " rdate = ?";
	private static String BEFORE_DATE = " rdate <= ?";
	private static String SINCE_DATE = " rdate >= ?";
	private static String SELECT_RUNS_WITH_UNITS_AND_PACE = "select r.runid, r.rdate, r.timeofday, r.distance*uc.factor as distance, r.elapsed, "
			+ "uc.to_u as units, "
			+ "extract(epoch from elapsed) / (r.distance*uc.factor) * '1 second'::interval as pace "
			+ "from runs r, unit_conversion uc where uc.from_u = r.units and uc.to_u = ? "
			+ "and distance is not null "
			+ "and r.elapsed is not null ";
	private static String PACE_FASTER_THAN = " extract(epoch from elapsed) / (r.distance*uc.factor) * '1 second'::interval <= ? ";
	private static String PACE_SLOWER_THAN = " extract(epoch from elapsed) / (r.distance*uc.factor) * '1 second'::interval >= ? ";
	private static String LONGER_THAN = " distance >= ?"; // TODO needs distance conversion

	private String formatDuration(Duration duration) {
		SimpleDateFormat hhmmssFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		hhmmssFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		return hhmmssFormat.format(duration);
	}

	public void insert(Run run) {
		String sql = "insert into runs (rdate, timeofday, distance, units, elapsed, effort, comments, shoeid)"
				+ " values "
				+ " (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { run.getRdate(), run.getTimeofday(), run.getDistance(),
				run.getUnits(), run.getElapsed(), run.getComments(), run.getShoeid() });
	}

	public List<Run> findAll() {
		String sql = "select rdate, timeofday, distance, units, elapsed, effort, comments, shoeid from runs";
		List<Run> runs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Run>(Run.class));
		return runs;
	}

	public List<Run> findByDate(LocalDate date) {
		String sql = SELECT_RUNS + " where " + BY_DATE;
		List<Run> runs = jdbcTemplate.query(sql, 
				(rs, rowNum) -> { 
					Run run = new Run();
					run.setRdate(rs.getDate("rdate").toLocalDate());
					run.setTimeofday(rs.getString("timeofday"));
					run.setDistance(rs.getBigDecimal("distance"));
					run.setUnits(rs.getString("units"));
					run.setElapsed(rs.getTime("elapsed"));
					run.setEffort(rs.getString("effort"));
					run.setComments(rs.getString("comments"));
					run.setShoeid(rs.getInt("shoeid"));
					return run;}, Date.valueOf(date));
		return runs;
	}

	public List<Run> fasterThanPace(Duration pace, String units) {
		String sql = SELECT_RUNS_WITH_UNITS_AND_PACE
				+ "and extract(epoch from elapsed) / (r.distance*uc.factor) * '1 second'::interval <= ?;";
		List<Run> runs = jdbcTemplate.query(sql,
				(rs, rowNum) -> {
					Run run = new Run();
					run.setRdate(rs.getDate("rdate").toLocalDate());
					run.setTimeofday(rs.getString("timeofday"));
					run.setDistance(rs.getBigDecimal("distance"));
					run.setUnits(rs.getString("units"));
					run.setElapsed(rs.getTime("elapsed"));
					run.setEffort(rs.getString("effort"));
					run.setComments(rs.getString("comments"));
					run.setShoeid(rs.getInt("shoeid"));
					return run;}, units, formatDuration(pace));
		return runs;
	}
	
	public List<Run> findWithFilters(String...filters) {
		String sql = SELECT_RUNS_WITH_UNITS_AND_PACE;
		// if filters includes a pace greater than x,
		
		return null;
	}
}
