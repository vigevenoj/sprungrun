package com.sharkbaitextraordinaire.sprungrun;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharkbaitextraordinaire.sprungrun.dao.RunDao;
import com.sharkbaitextraordinaire.sprungrun.model.Run;

@RestController
@RequestMapping("/runs")
public class RunController {

	private static final Logger logger = LoggerFactory.getLogger(RunController.class);
	
	@Autowired
	private RunDao runDao;
	
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Run> findAll() {
		return runDao.findAll();
	}
	
	@RequestMapping(value = "/bydate", method = RequestMethod.GET, produces = "application/json")
	public List<Run> findByDate(@RequestParam(value="date")
								@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		logger.info("Trying to find runs with date " + date);
		return runDao.findByDate(date);
	}
}
