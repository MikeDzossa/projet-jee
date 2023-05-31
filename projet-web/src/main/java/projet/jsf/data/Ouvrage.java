package projet.jsf.data;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
public class Ouvrage implements Serializable  {

	
	// Champs

	Integer	id;
	
	@NotBlank( message = "Le titre doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le titre : 25 car. maxi" )
	private String		titre;
	
	@NotNull( message = "La catégorie est obligatoire")
	private Categorie		categorie;
	
	@NotNull( message = "L'éditeur est obligatoire")
	private Editeur		editeur;
	
	@NotNull( message = "L'auteur est obligatoire")
	private Auteur		auteur;
	
	@NotNull( message = "Le proprietaire est obligatoire")
	private Personne		proprietaire;
	// Constructeurs
	
	public Ouvrage() {
	}

	public Ouvrage(int id, String titre, Categorie categorie, Auteur auteur, Editeur editeur, Personne proprietaire) {
		setId(id);
		setTitre(titre);
		setAuteur(auteur);
		setEditeur(editeur);
		setCategorie(categorie);
		setProprietaire(proprietaire);
	}
	
		
	// Getters & setters
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public Auteur getAuteur() {
		return auteur;
	}
	
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	
	public Editeur getEditeur() {
		return editeur;
	}
	
	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	public Personne getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Personne proprietaire) {
		this.proprietaire = proprietaire;
	}

    
	// equals() et hashcode()

	

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
		Ouvrage other = (Ouvrage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
