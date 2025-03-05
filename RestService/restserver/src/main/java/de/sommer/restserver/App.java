package de.sommer.restserver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class App 
{
	public static void main(String[] args) throws URISyntaxException, IOException {

		// Define base URL for server
		String baseUrl = "http://localhost:8090/rest";

		// Define REST class to be used
		ResourceConfig resourceConfig = new ResourceConfig(MitarbeiterService.class);

		// Start Grizzly http server
		final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(new URI(baseUrl), resourceConfig, false);
		server.start();
		
		System.out.println("Server started with base-url=" + baseUrl + "...");
		System.out.println("use example url=" + baseUrl + "/mitarbeiter-service/mitarbeiter/1");

	}
}
