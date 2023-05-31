package projet.jsf.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@SuppressWarnings("serial")
public class Personne implements Serializable {


	// Champs
	
	private Integer			id;
	
	@NotBlank( message = "Le nom doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le nom : 25 car. maxi" )
	private String			nom;

	@NotBlank( message = "Le prénom doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le prénom : 25 car. maxi" )
	private String			prenom;

	@NotNull( message = "Le compte est obligatoire")
	private Compte		compte;
	
	@NotNull( message = "La date de naissance est obligatoire")
	private LocalDate 		dateNaissance;

	private List<Personne> demandesAmis = new ArrayList<>();
	
	private List<Personne> requetesAmis = new ArrayList<>();

	private List<Ouvrage> ouvrages = new ArrayList<>();
	
	// Constructeurs
	
	public Personne() {
	}

	public Personne(Integer id, String nom, String prenom, LocalDate datenaissance, Compte compte) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.compte = compte;
		this.dateNaissance = datenaissance;
	}
	
	
	// Getters & setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public List<Personne> getDemandesAmis() {
		return demandesAmis;
	}

	public void setDemandesAmis(List<Personne> demandesAmis) {
		this.demandesAmis = demandesAmis;
	}

	public List<Personne> getRequetesAmis() {
		return requetesAmis;
	}

	public void setRequetesAmis(List<Personne> requetesAmis) {
		this.requetesAmis = requetesAmis;
	}

	public List<Ouvrage> getOuvrages() {
		return ouvrages;
	}

	public void setOuvrages(List<Ouvrage> ouvrages) {
		this.ouvrages = ouvrages;
	}

	@Override
	public String toString() {
		return this.prenom + " " + this.nom;
	}
	
	// hashCode() & equals()
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		var other = (Personne) obj;
		return Objects.equals(id, other.id);
	}
	

}
