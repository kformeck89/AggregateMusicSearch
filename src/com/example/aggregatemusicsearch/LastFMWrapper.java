package com.example.aggregatemusicsearch;

import java.util.Collection;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Caller;

public class LastFMWrapper {
	public static final int RESULT_LIMIT = 20;
	public static final String API_KEY = "08de14b60223c008bca6fe9748ab2fe5";
	public static final String SECRET = "is 6e926ef51872eba3bdc1a9967dea364e";
	public static final String USER = "tst";
	public static final String ARTIST = "artist";
	
	public LastFMWrapper() {
		Caller.getInstance().setUserAgent(USER);
	}
	
	public Collection<Artist> artistSearch(String artist) {
		Caller.getInstance().setCache(null);
		return Artist.getSimilar(artist, RESULT_LIMIT, API_KEY);
	}
}
