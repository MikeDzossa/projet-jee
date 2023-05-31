package projet.commun.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class DtoPersonne implements Serializable {
	
	
	// Champs
	
	private int				id;
	
	private String			nom;
	
	private String			prenom;

	private LocalDate		dateNaissance;
	
	private List<DtoPersonne> demandesAmis = new ArrayList<>();
	
	private List<DtoPersonne> requetesAmis = new ArrayList<>();
	
	private List<DtoOuvrage> ouvrages = new ArrayList<>();
	
	private DtoCompte 		compte;
	
	
	// Constructeurs
	
	public DtoPersonne() {
	}

	public DtoPersonne(int id, String nom, String prenom,  LocalDate dateNaissance, DtoCompte compte) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.compte = compte;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public DtoCompte getCompte() {
		return compte;
	}

	public void setCompte(DtoCompte compte) {
		this.compte = compte;
	}

	public List<DtoPersonne> getDemandesAmis() {
		return demandesAmis;
	}

	public void setDemandesAmis(List<DtoPersonne> demandesAmis) {
		this.demandesAmis = demandesAmis;
	}

	public List<DtoPersonne> getRequetesAmis() {
		return requetesAmis;
	}

	public void setRequetesAmis(List<DtoPersonne> requetesAmis) {
		this.requetesAmis = requetesAmis;
	}

	public List<DtoOuvrage> getOuvrages() {
		return ouvrages;
	}

	public void setOuvrages(List<DtoOuvrage> ouvrages) {
		this.ouvrages = ouvrages;
	}

	

}
