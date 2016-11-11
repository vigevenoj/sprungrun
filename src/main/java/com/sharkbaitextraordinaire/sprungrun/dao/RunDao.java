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
				run.getUnits(), run.getElapsed(), run.getComment(), run.getShoeid() });
	}
	
	public List<Run> findAll() {
		String sql = "select rdate, timeofday, distance, units, elapsed, effort, comments, shoeid from runs";
		List<Run> runs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Run>(Run.class));
		return runs;
	}
	
	public List<Run> findByDate(LocalDate date) {
		String sql = "select rdate, timeofday, distance, units, elapsed, effort, comments, shoeid from runs"
				+ " where rdate = ?";
		List<Run> runs = jdbcTemplate.query(sql, 
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
				return run;}, Date.valueOf(date));
		return runs;
	}

  public List<Run> fasterThanPace(Duration pace, String units) {
    String sql = "select rdate, timeofday, distance, units, elapsed, effort, comments, shoeid from runs"
      + " "; // TODO get pace query
    List<Run> runs = jdbcTemplate.query(sql,
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
				return run;}, formatDuration(pace));
		return runs;
  }
}
