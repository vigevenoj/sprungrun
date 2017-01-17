package com.sharkbaitextraordinaire.sprungrun.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sharkbaitextraordinaire.sprungrun.model.Run;

public interface RunRepository extends CrudRepository<Run, Long> {
	
	@Query("select runid, comments, distance, effort, elapsed, rdate, shoeid, timeofday, units from runs where rdate=?")
	List<Run> findByRdate(LocalDate rdate);
	
}
