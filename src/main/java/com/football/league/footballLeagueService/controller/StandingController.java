package com.football.league.footballLeagueService.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.football.league.footballLeagueService.service.StandingService;

@RestController
@RequestMapping(value="/football/standing",method=RequestMethod.GET)
public class StandingController {
	@Autowired
	private StandingService standingService;
	
	@RequestMapping("/league/{id}")
	@ResponseBody
	public List<Map<String,String>> getLeagueStandings(@PathVariable("id") String leagueId,@RequestParam String countryName,@RequestParam String teamName)
	{
		return standingService.getStandingInfo(leagueId, countryName, teamName);
	}
}
