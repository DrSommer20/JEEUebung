package de.sommer.verteiltesysteme.rmi.backend;

import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@Stateless
@WebService
public class MitarbeiterServiceImpl implements MitarbeiterService{

    List<Mitarbeiter> mitarbeiterList = new ArrayList<Mitarbeiter>();

    @Override
    @WebMethod
    public void addMitarbeiter(Mitarbeiter mitarbeiter){
        mitarbeiterList.add(mitarbeiter);
    }

    @Override
    @WebMethod
    public void deleteMitarbeiter(int id){
        Mitarbeiter mitarbeiter = getMitarbeiter(id);
        mitarbeiterList.remove(mitarbeiter);
    }

    @Override
    @WebMethod
    public void updateMitarbeiter(Mitarbeiter updatedMitarbeiter){
        Mitarbeiter mitarbeiter = getMitarbeiter(updatedMitarbeiter.getId());
        mitarbeiter.setName(updatedMitarbeiter.getName());
        mitarbeiter.setVorname(updatedMitarbeiter.getVorname());
        mitarbeiter.setAlter(updatedMitarbeiter.getAlter());
        mitarbeiter.setAbteilung(updatedMitarbeiter.getAbteilung());
        mitarbeiter.setGehalt(updatedMitarbeiter.getGehalt());
    }

    @Override
    @WebMethod
    public Mitarbeiter getMitarbeiter(int id){
        for(Mitarbeiter mitarbeiter : mitarbeiterList) {
            if(mitarbeiter.getId() == id) {
                return mitarbeiter;
            }
        }
        return null;
    }

    @Override
    @WebMethod
    public List<Mitarbeiter> getAllMitarbeiter(){
        return mitarbeiterList;
    }
    
}
