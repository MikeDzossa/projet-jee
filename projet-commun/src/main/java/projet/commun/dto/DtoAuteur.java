package projet.commun.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class DtoAuteur implements Serializable {
    
    // Champs
    
    private int         id;
    private String      nom;
	
	
	// Constructeurs
    
    public DtoAuteur() {
		super();
	}
    
    public DtoAuteur(int id, String nom) {
		super();
		setId(id);
		setNom(nom);
	}
    
    
    // Getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
