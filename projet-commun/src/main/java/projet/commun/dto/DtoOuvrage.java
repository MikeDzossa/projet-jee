package projet.commun.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class DtoOuvrage implements Serializable {
	
	
	// Champs
	
	private int				id;
	
	private String			titre;
	
	private DtoCategorie	categorie;
	
	private DtoEditeur	editeur;
	
	private DtoAuteur	auteur;
	
	private DtoPersonne	proprietaire;
	
	// Constructeurs
	
	public DtoOuvrage() {
	}

	public DtoOuvrage(int id, String titre, DtoEditeur editeur, DtoAuteur auteur, DtoCategorie categorie, DtoPersonne proprietaire) {
		super();
		setId(id);
		setTitre(titre);
		setAuteur(auteur);
		setEditeur(editeur);
		setCategorie(categorie);
		setProprietaire(proprietaire);
	}
	
	
	
	// Getters & setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public DtoEditeur getEditeur() {
		return editeur;
	}

	public void setEditeur(DtoEditeur editeur) {
		this.editeur = editeur;
	}

	public DtoAuteur getAuteur() {
		return auteur;
	}

	public void setAuteur(DtoAuteur auteur) {
		this.auteur = auteur;
	}

	public DtoCategorie getCategorie() {
		return categorie;
	}

	public void setCategorie(DtoCategorie categorie) {
		this.categorie = categorie;
	}

	public DtoPersonne getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(DtoPersonne proprietaire) {
		this.proprietaire = proprietaire;
	}


}
