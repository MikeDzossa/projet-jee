package projet.jsf.data;

import java.util.Objects;



public class Emprunt {
    
	
    // Champs
   
	 private	Integer  id;
	

    private Ouvrage         	ouvrage;
  
    private Personne      	emprunteur;
	

	private Boolean valider;
	// Constructeurs

	public Emprunt() {
		super();
	}	
	
	public Emprunt(Integer id, Ouvrage ouvrage, Personne emprunteur, Boolean valider) {
		super();
		this.id = id;
		this.ouvrage = ouvrage;
		this.emprunteur = emprunteur;
		this.valider = valider;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return Objects.equals(id, other.id);
	}

    // hashCode & equal()


	
  
}
