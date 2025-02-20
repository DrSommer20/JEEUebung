package de.sommer.verteiltesysteme.rmi.backend;

import java.rmi.RemoteException;
import java.util.List;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.jws.WebService;

@Remote
public interface MitarbeiterService  {

    public void addMitarbeiter(Mitarbeiter mitarbeiter);
    public void deleteMitarbeiter(int id);
    public void updateMitarbeiter(Mitarbeiter mitarbeiter);
    public Mitarbeiter getMitarbeiter(int id);
    public List<Mitarbeiter> getAllMitarbeiter();
    
} 
