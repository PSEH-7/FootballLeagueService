package com.football.league.footballLeagueService;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.football.league.footballLeagueService.service.StandingService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FootballLeagueServiceApplicationTests {
	@Autowired
	public StandingService standingService;

	@Test
	public void testStandingServiceWithLeagueId() {
		List<Map<String, String>> list = standingService.getStandingInfo("149", null, null);
		Assert.assertEquals(24, list.size());
	}
	
	@Test
	public void testStandingServiceWithLeagueIdAndTeamName() {
		List<Map<String, String>> list = standingService.getStandingInfo("149", null, "Liverpool");
		Assert.assertEquals(24, list.size());
	}
	
	@Test
	public void testStandingServiceWithLeagueIdAndTeamNameAndCompanyName() {
		List<Map<String, String>> list = standingService.getStandingInfo("149", "England", "Liverpool");
		Assert.assertEquals(24, list.size());
	}
	

}
