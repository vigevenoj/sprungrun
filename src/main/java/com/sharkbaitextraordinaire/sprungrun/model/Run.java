package com.sharkbaitextraordinaire.sprungrun.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "runs")
@Table(name = "runs")
public class Run {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="runid_seq")
	@SequenceGenerator(name="runid_seq",sequenceName="runid_seq",allocationSize=1)
	private Long runid;
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate rdate;
	private String timeofday;
	private BigDecimal distance;
	private String units;
	private Time elapsed;
	private String effort;
	private String comments;
	private int shoeid;
	
	public Run(LocalDate rdate, String timeofday, BigDecimal distance, String units, Time elapsed, String effort,
			String comments, int shoeid) {
		this.rdate = rdate;
		this.timeofday = timeofday;
		this.distance = distance;
		this.units = units;
		this.elapsed = elapsed;
		this.effort = effort;
		this.comments = comments;
		this.shoeid = shoeid;
	}
	
	public Run() {
		// Empty constructor
	}
	public Long getRunid() {
		return runid;
	}
	public void setRunid(Long runid) {
		this.runid = runid;
	}
	public LocalDate getRdate() {
		return rdate;
	}
	public void setRdate(LocalDate rdate) {
		this.rdate = rdate;
	}
	public String getTimeofday() {
		return timeofday;
	}
	public void setTimeofday(String timeofday) {
		this.timeofday = timeofday;
	}
	public BigDecimal getDistance() {
		return distance;
	}
	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public Time getElapsed() {
		return elapsed;
	}
	public void setElapsed(Time elapsed) {
		this.elapsed = elapsed;
	}
	public String getEffort() {
		return effort;
	}
	public void setEffort(String effort) {
		this.effort = effort;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getShoeid() {
		return shoeid;
	}
	public void setShoeid(int shoeid) {
		this.shoeid = shoeid;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Run [runid %d on %s, at %s, %s %s, took %s , effort='%s', %s, shoeid='%d']",
                runid, rdate, timeofday, distance, units, elapsed, effort, comments, shoeid
                );
    }
}
