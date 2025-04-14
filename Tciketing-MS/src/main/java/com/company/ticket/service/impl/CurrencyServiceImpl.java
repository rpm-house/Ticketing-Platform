package com.company.ticket.service.impl;

import java.net.InetAddress;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.company.ticket.service.CurrencyService;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Value("${exchangerate.key}")
	private String exchangerateKey;

	private String url = "https://api.exchangerate.host";

	private final WebClient webClient = WebClient.create(url);

	@Override
	public Double convertCurrency(String from, String to, double amount) {

		if (isSafeUrl(url)) {

			JsonNode jsonVal = webClient.get()
					.uri(uriBuilder -> uriBuilder.path("/convert").queryParam("access_key", exchangerateKey)
							.queryParam("format", 1).queryParam("from", from).queryParam("to", to)
							.queryParam("amount", amount).build())
					.retrieve().bodyToMono(JsonNode.class).onErrorResume(e -> Mono.empty()).block();

			log.info("jsonVal : {}", jsonVal.get("result").asDouble());

			return jsonVal.get("result").asDouble();
		} else {
			log.error("The Exchnage Rate URL is not safe : {}", url);
			// throw new TicketException("The Exchnage Rate URL is not safe");
			return 0.0;
		}
		
	}

	public boolean isSafeUrl(String urlString) {
		try {
			URL url = new URL(urlString);
			String host = url.getHost();
			InetAddress address = InetAddress.getByName(host);
			if (address.isLoopbackAddress() || address.isAnyLocalAddress() || address.isSiteLocalAddress()) {
				return false;
			}
			return url.getProtocol().equals("https") || url.getProtocol().equals("http");
		} catch (Exception e) {
			return false;
		}
	}
}
