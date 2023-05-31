package projet.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table( name = "personne"  )
public class Personne {
	
	
	// Champs
	
	@Id
	@GeneratedValue( strategy = IDENTITY )
	@Column( name = "idpersonne" )
	private int				id;
	
	@Column( name = "nom" )
	private String			nom;
	
	@Column( name = "prenom" )
	private String			prenom;
	
	@Column( name = "datenaissance" )
	private LocalDate		dateNaissance;
	
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn( name = "idcompte" )
	private Compte 			compte;
	
	
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable( name = "amitie",
				joinColumns = @JoinColumn(name = "idutilisateur"),
				inverseJoinColumns = @JoinColumn( name = "idami") )
	private List<Personne> requetesAmis = new ArrayList<>();
	
	@ManyToMany( fetch = FetchType.LAZY )
	@JoinTable( name = "amitie",
				joinColumns = @JoinColumn(name = "idami"),
				inverseJoinColumns = @JoinColumn( name = "idutilisateur") )
	private List<Personne> demandesAmis = new ArrayList<>();
	
	@OneToMany( mappedBy = "proprietaire",
				fetch = FetchType.LAZY,
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private List<Ouvrage> ouvrages = new ArrayList<>();
	
	// Constructeurs
	
	public Personne() {
	}

	public Personne(int id, String nom, String prenom, LocalDate dateNaissance, Compte compte ) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setCompte(compte);
		setDateNaissance(dateNaissance);
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
	
	
	// hashcode() + equals()
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
