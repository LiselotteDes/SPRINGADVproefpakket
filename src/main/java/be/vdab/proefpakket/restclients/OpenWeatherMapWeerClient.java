package be.vdab.proefpakket.restclients;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import be.vdab.proefpakket.exceptions.KanTemperatuurNietLezenException;

@Component
class OpenWeatherMapWeerClient implements WeerClient {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapWeerClient.class);
	private final String uriTemplate;
	private final RestTemplate restTemplate;
	
	OpenWeatherMapWeerClient(@Value("${openWeatherMapURL}") String uriTemplate, RestTemplateBuilder restTemplateBuilder) {
		this.uriTemplate = uriTemplate;
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public BigDecimal getTemperatuur(String plaats) {
		try {
			Weer weer = restTemplate.getForObject(uriTemplate, Weer.class, plaats);
			return weer.getMain().getTemp();
		} catch (RestClientException ex) {
			LOGGER.error("kan temperatuur niet lezen", ex);
			throw new KanTemperatuurNietLezenException();
		}
	}
}
