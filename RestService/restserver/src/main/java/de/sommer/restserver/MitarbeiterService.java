package de.sommer.restserver;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("mitarbeiter-service")
public class MitarbeiterService {
    public static List<Mitarbeiter> mitarbeiterList = new ArrayList<Mitarbeiter>();

    static {
        mitarbeiterList.add(new Mitarbeiter(getNextID(), "Mayer", "Max", 30, "IT", 50000));
        mitarbeiterList.add(new Mitarbeiter(getNextID(), "Schmidt", "Xaver", 35, "HR", 55000));
        mitarbeiterList.add(new Mitarbeiter(getNextID(), "Müller", "Petra", 28, "Finance", 52000));
        mitarbeiterList.add(new Mitarbeiter(getNextID(), "Schmitt", "Emil", 40, "Marketing", 60000));
        for(Mitarbeiter mitarbeiter : mitarbeiterList) {
        	System.out.println(mitarbeiter);
        }
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("all")
    public List<Mitarbeiter> getAllMitarbeiter() {
        System.out.println("RestMitarbeiterService.all... called.");
        return mitarbeiterList;
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("mitarbeiter/{id}")
    public Mitarbeiter getMitarbeiter(@PathParam("id") int id) {
        System.out.println("RestMitarbeiterService.mitarbeiter/id... called.");
        for (Mitarbeiter mitarbeiter : mitarbeiterList) {
            if (mitarbeiter.getId() == id) {
                System.out.println("return " + mitarbeiter);
                return mitarbeiter;
            }
        }
        return null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("add")
    public Mitarbeiter addMitarbeiter(@QueryParam("name") String name, @QueryParam("vorname") String vorname,
                                       @QueryParam("alter") int alter, @QueryParam("abteilung") String abteilung,
                                       @QueryParam("gehalt") double gehalt) {
        System.out.println("RestMitarbeiterService.add... called.");
        Mitarbeiter mitarbeiter = new Mitarbeiter(getNextID(), name, vorname, alter, abteilung, gehalt);
        mitarbeiterList.add(mitarbeiter);
        return mitarbeiter;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/mitarbeiter/add")
    public Mitarbeiter addMitarbeiter(Mitarbeiter mitarbeiterParam) {
        System.out.println("RestMitarbeiterService.add with Mitarbeiter... called.");
        Mitarbeiter mitarbeiter = new Mitarbeiter(getNextID(), mitarbeiterParam.getName(), mitarbeiterParam.getVorname(),
                                                  mitarbeiterParam.getAlter(), mitarbeiterParam.getAbteilung(),
                                                  mitarbeiterParam.getGehalt());
        mitarbeiterList.add(mitarbeiter);
        return mitarbeiter;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_XML)
    @Path("mitarbeiter/{id}")
    public Mitarbeiter deleteMitarbeiter(@PathParam("id") int id) {
        System.out.println("RestMitarbeiterService.delete... called.");
        Mitarbeiter mitarbeiterToBeDeleted = null;
        for (Mitarbeiter mitarbeiter : mitarbeiterList) {
            if (mitarbeiter.getId() == id) {
                mitarbeiterToBeDeleted = mitarbeiter;
            }
        }
        mitarbeiterList.remove(mitarbeiterToBeDeleted);
        return mitarbeiterToBeDeleted;
    }

    private static int getNextID() {
        int max = 0;
        for (Mitarbeiter mitarbeiter : mitarbeiterList) {
            if (mitarbeiter.getId() > max) {
                max = mitarbeiter.getId();
            }
        }
        return max + 1;
    }
}