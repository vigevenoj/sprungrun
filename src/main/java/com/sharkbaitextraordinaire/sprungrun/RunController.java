package com.sharkbaitextraordinaire.sprungrun;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharkbaitextraordinaire.sprungrun.dao.RunDao;
import com.sharkbaitextraordinaire.sprungrun.dao.RunRepository;
import com.sharkbaitextraordinaire.sprungrun.model.Run;

@RestController
@RequestMapping("/runs")
public class RunController {

	private static final Logger logger = LoggerFactory.getLogger(RunController.class);

	@Autowired
	private RunDao runDao;
	
	@Autowired
	private RunRepository runRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Run> getRunsViaJPA(@RequestParam(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return this.runRepository.findByRdate(date);
	}
}
