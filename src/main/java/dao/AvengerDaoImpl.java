package dao;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.factory.AvengerFactory;
import model.Avenger;

public class AvengerDaoImpl {

	private HttpResponse<String> getAvengers() throws URISyntaxException, IOException, InterruptedException {
		HttpClient httpClient = HttpClient.newBuilder().followRedirects(
				HttpClient.Redirect.ALWAYS).build();

		HttpRequest request = HttpRequest
				.newBuilder(new URI("https://raw.githubusercontent.com/fivethirtyeight/data/master/avengers/avengers.csv")).GET()
				.build();

		return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	}
	
	public List<Avenger> findAvengers() throws URISyntaxException, IOException, InterruptedException{
		List<Avenger> avengers = Stream.of(getAvengers().body().replaceAll("_Fury,","Fury").replaceAll("Jr., M","Jr. M").replaceAll("Fury,","Fury").split("\r"))
				.skip(1)
				.map(AvengerFactory::createAvenger)
				.collect(Collectors.toList());
		
		return avengers;
	}

}