package br.com.starwars.components;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlBuilder {

	public static final String REQUEST_PATH_PLANETS = "/planets";
	public static final String REQUEST_PATH_NAME = "/name";
	public static final String PARAM_NAME = "/{name}";
	public static final String PARAM_ID = "/{id}";
	public static final String PARAM_SEARCH = "search";
	
	@Value("${swapi.url.planets}")
	private String url;
	
	public String swapiUrlPlanets(String name) {
		return fromHttpUrl(url).queryParam(PARAM_SEARCH, name).toUriString();
	}
}