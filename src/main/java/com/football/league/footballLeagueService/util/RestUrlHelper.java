package com.football.league.footballLeagueService.util;

public class RestUrlHelper {
	private static String APIKey = "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978"; 
	public static String STANDING_LEAGUE_URL = "https://apiv2.apifootball.com/?action=get_standings&league_id=%s&APIkey=%s";
	public static String GET_COUNTRIES_URL = "https://apiv2.apifootball.com/?action=get_countries&APIkey=%s";

	public static String getLeagueStandingURL(String leagueId)
	{
		String URL = String.format(STANDING_LEAGUE_URL, leagueId,APIKey);
		return URL;
	}
	
	public static String getCountriesURL()
	{
		String URL = String.format(GET_COUNTRIES_URL,APIKey);
		return URL;
	}
}
