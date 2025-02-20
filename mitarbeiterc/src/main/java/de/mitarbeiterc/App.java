package de.mitarbeiterc;

import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;

import de.sommer.verteiltesysteme.rmi.backend.Mitarbeiter;
import de.sommer.verteiltesysteme.rmi.backend.MitarbeiterService;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws Exception {
		// Define how to load access to Wildfly
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial", "org.wildfly.naming.client.WildFlyInitialContextFactory");
		properties.put("java.naming.provider.url", "http-remoting://localhost:8080");
		InitialContext ctx = new InitialContext(properties);
		
		// Define JDNI name for finding the EJB
		String jndiName = "05a_jee_server/MitarbeiterServiceImpl!de.sommer.verteiltesysteme.rmi.backend.MitarbeiterService";
		
		// Lookup EJB
		MitarbeiterService mitarbeiterService = (MitarbeiterService) ctx.lookup(jndiName);
		
        System.out.println("");
        System.out.println("Creating new Mitarbeiter...");
        Mitarbeiter mitarbeiter = new Mitarbeiter(1, "Sommer", "Max", 25, "IT", 5000);
        System.out.println("Adding Mitarbeiter to the service...");
        mitarbeiterService.addMitarbeiter(mitarbeiter);
        System.out.println("Mitarbeiter added successfully.");

        System.out.println("");
        System.out.println("Retrieving Mitarbeiter with ID 1...");
        mitarbeiter = mitarbeiterService.getMitarbeiter(1);
        System.out.println(mitarbeiter);

        System.out.println("");
        System.out.println("Adding another Mitarbeiter to the service...");
        mitarbeiterService.addMitarbeiter(new Mitarbeiter(2, "Muster", "Hans", 30, "HR", 4000));
        System.out.println("Mitarbeiter added successfully.");

        System.out.println("");
        System.out.println("Retrieving all Mitarbeiter...");
        List<Mitarbeiter> mitarbeiterList = mitarbeiterService.getAllMitarbeiter();
        System.out.println("All Mitarbeiter:");
        for (Mitarbeiter m : mitarbeiterList) {
            System.out.println(m);
        }

	}
}
