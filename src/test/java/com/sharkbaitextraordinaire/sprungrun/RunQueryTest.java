package com.sharkbaitextraordinaire.sprungrun;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.sharkbaitextraordinaire.sprungrun.dao.RunRepository;
import com.sharkbaitextraordinaire.sprungrun.model.Run;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class) // ?
@SpringBootTest
@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class RunQueryTest {

	@Autowired
	private RunRepository repository;
	
	private Run shortRun;
	private Run longSlowRun;
	
	@Before
	public void init() {
		shortRun = new Run();
		shortRun.setDistance(new BigDecimal(3.1));
		shortRun.setUnits("miles");
		shortRun.setRdate(LocalDate.now());
		shortRun.setTimeofday("noon");
		shortRun.setElapsed(java.sql.Time.valueOf("00:27:30"));
		repository.save(shortRun);
		
		longSlowRun = new Run();
		longSlowRun.setRdate(LocalDate.parse("2018-11-11"));
		longSlowRun.setTimeofday("am");
		longSlowRun.setDistance(new BigDecimal("18.3"));
		longSlowRun.setUnits("miles");
		longSlowRun.setElapsed(java.sql.Time.valueOf("03:10:15"));
		repository.save(longSlowRun);
	}
	
	@Test
	public void getRunFromElevenEleven() {
		assertNotNull(repository);
		List<Run> results = repository.findByRdate(LocalDate.parse("2018-11-11"));
		assertNotNull(results);
		assertNotNull(longSlowRun);
		for (Run r : results) {
			System.out.println(r.toString());
		}
		assertTrue(results.contains(longSlowRun));
		assertFalse(results.contains(shortRun));
	}
}
