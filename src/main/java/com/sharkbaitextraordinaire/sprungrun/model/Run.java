package com.sharkbaitextraordinaire.sprungrun.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;

public class Run {

	private Long runid;
	private LocalDate rdate;
	private String timeofday;
	private BigDecimal distance;
	private String units;
	private Time elapsed;
	private String effort;
	private String comment;
	private int shoeid;
	
	public Run(LocalDate rdate, String timeofday, BigDecimal distance, String units, Time elapsed, String effort,
			String comment, int shoeid) {
		this.rdate = rdate;
		this.timeofday = timeofday;
		this.distance = distance;
		this.units = units;
		this.elapsed = elapsed;
		this.effort = effort;
		this.comment = comment;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
                runid, rdate, timeofday, distance, units, elapsed, effort, comment, shoeid
                );
    }
}
