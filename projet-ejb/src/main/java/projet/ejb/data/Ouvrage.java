package projet.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table( name = "ouvrage" )
public class Ouvrage  {

	
	// Champs

	@Id
	@GeneratedValue( strategy = IDENTITY)
	@Column( name = "idouvrage")
	private int			id;
	
	@Column( name = "titre")
	private String		titre;
	
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "idcategorie" )
	private Categorie		categorie;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "idediteur")
	private Editeur		editeur;
	
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "idauteur")
	private Auteur		auteur;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "idproprietaire")
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
