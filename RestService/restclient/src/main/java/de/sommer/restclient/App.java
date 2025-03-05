package de.sommer.restclient;


import java.util.List;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class App {
    private static String baseURL = "http://localhost:8090/rest/mitarbeiter-service";

    public static void readMitarbeiter(int id) {
        String url = baseURL + "/mitarbeiter/" + id;
        System.out.println("Calling url=" + url);
        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request(MediaType.APPLICATION_XML).get();
//        String mitarbeiter = response.readEntity(String.class);
        Mitarbeiter mitarbeiter = response.readEntity(Mitarbeiter.class);
        System.out.println("Mitarbeiter: " + mitarbeiter);
    }

    public static void readAllMitarbeiter() {
        String url = baseURL + "/all";
        System.out.println("Calling url=" + url);
        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request(MediaType.APPLICATION_XML).get();
//        String mitarbeiterList = response.readEntity(String.class);
        List<Mitarbeiter> mitarbeiterList = response.readEntity(new GenericType<List<Mitarbeiter>>() {});
        System.out.println("All Mitarbeiter: " + mitarbeiterList);
    }

    public static void addMitarbeiter() {
        String url = baseURL + "/mitarbeiter/add";
        System.out.println("Calling url=" + url);
        Client client = ClientBuilder.newClient();
        Mitarbeiter mitarbeiter = new Mitarbeiter(-1, "Test", "Mitarbeiter", 30, "IT", 50000);
        Response response = client.target(url).request(MediaType.APPLICATION_XML)
                .post(Entity.entity(mitarbeiter, MediaType.APPLICATION_XML));
        Mitarbeiter mitarbeiterResult = response.readEntity(Mitarbeiter.class);
        System.out.println("Mitarbeiter added: " + mitarbeiterResult);
    }

    public static void deleteMitarbeiter(int id) {
        String url = baseURL + "/mitarbeiter/" + id;
        System.out.println("Calling url=" + url);
        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request(MediaType.APPLICATION_XML).delete();
        Mitarbeiter mitarbeiter = response.readEntity(Mitarbeiter.class);
        System.out.println("Mitarbeiter deleted: " + mitarbeiter);
    }

    public static void main(String[] args) {
        readAllMitarbeiter();
        readMitarbeiter(1);
        deleteMitarbeiter(5);
        addMitarbeiter();
        readAllMitarbeiter();
    }
}

