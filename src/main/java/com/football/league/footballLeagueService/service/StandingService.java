package com.football.league.footballLeagueService.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.football.league.footballLeagueService.util.RestUrlHelper;

@Component
public class StandingService {
	@Autowired
	private RestTemplate restTemplate;
	private  Map<String,String> countryNameToIdMap = new HashMap();
	
	public List<Map<String,String>> getStandingInfo(String leagueId,String countryName,String teamName)
	{
		List<Map<String,String>> responseListMap = new ArrayList<Map<String,String>>();
		
		if(StringUtils.isEmpty(leagueId))
			return responseListMap;
		
		String url = RestUrlHelper.getLeagueStandingURL(leagueId);
		List<Map<String,String>> responseList = restTemplate.getForObject(url, List.class);
		
		if(responseList!= null && !responseList.isEmpty())
		{
			for(Map<String,String> map : responseList)
			{
				Map<String,String> standingMap = new HashMap();
				String cname = map.get("country_name");
				String tname = map.get("team_name");
				
				if(cname!=null && cname.equals(countryName))
				{
					String id = countryNameToIdMap.get(countryName);
					standingMap.put(id==null ? countryName : id , countryName);
					
				}
				else if(countryName == null)
				{
					String id = countryNameToIdMap.get(cname);
					standingMap.put(id==null ? cname : id , countryName);
				}
				else
					continue;
				
				standingMap.put(map.get("league_id"), map.get("league_name"));
				
				if(tname!=null && tname.equals(teamName))
				{
					String id = map.get("team_id");
					standingMap.put(id==null ? teamName : id , teamName);
					
				}
				else if(teamName == null)
				{
					standingMap.put(map.get("team_id"), map.get("team_name"));
				}
				standingMap.put("overall_league_position", map.get("overall_league_position"));
				responseListMap.add(standingMap);
			}
			
		}
		System.out.print("Total Entries"+responseList.size());
		return responseListMap;
	}
	
	@PostConstruct
	public void prepareCountryNameToIdMap()
	{
		String url = RestUrlHelper.getCountriesURL();
		List<Map<String,String>> responseList = restTemplate.getForObject(url, List.class);
		if(responseList!= null && !responseList.isEmpty())
		{
			for(Map<String,String> countryMap : responseList)
				countryNameToIdMap.put(countryMap.get("country_name"), countryMap.get("country_id"));
		}
	}
}
