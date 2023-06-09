package projet.ejb.data;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table( name = "emprunt" )
public class Emprunt {
    
	
    // Champs
    
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column( name = "idemprunt" )
	 private	int id;
	
	@ManyToOne
	@JoinColumn( name = "idouvrage" )
    private Ouvrage         	ouvrage;
    
	@ManyToOne
	@JoinColumn( name = "idemprunteur" )
    private Personne      	emprunteur;
	
	@Column( name = "valider")
	private Boolean valider;
	// Constructeurs

	public Emprunt() {
		super();
	}	
	
	public Emprunt(int id, Ouvrage ouvrage, Personne emprunteur, Boolean valider) {
		super();
		this.id = id;
		this.ouvrage = ouvrage;
		this.emprunteur = emprunteur;
		this.valider = valider;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ouvrage getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(Ouvrage ouvrage) {
		this.ouvrage = ouvrage;
	}



	public Personne getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Personne emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Boolean getValider() {
		return valider;
	}

	public void setValider(Boolean valider) {
		this.valider = valider;
	}

    // hashCode & equal()
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
		Emprunt other = (Emprunt) obj;
		return id == other.id;
	}

	
  
}
