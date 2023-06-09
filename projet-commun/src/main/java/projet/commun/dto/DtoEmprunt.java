package projet.commun.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class DtoEmprunt implements Serializable {
    
    // Champs
    
    private DtoOuvrage       ouvrage;
    private DtoPersonne      emprunteur;
    private	Integer id;
	private boolean valider;
	
	// Constructeurs
   	public DtoEmprunt() {
		super();
	} 
   	
	
    public DtoEmprunt(Integer id ,DtoOuvrage ouvrage, DtoPersonne emprunteur, boolean valider ) {
		super();
		this.ouvrage = ouvrage;
		this.emprunteur = emprunteur;
		this.valider = valider;
		this.id=id;
	}



	public DtoOuvrage getOuvrage() {
		return ouvrage;
	}

	public void setOuvrage(DtoOuvrage ouvrage) {
		this.ouvrage = ouvrage;
	}

	public DtoPersonne getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(DtoPersonne emprunteur) {
		this.emprunteur = emprunteur;
	}

	public boolean isValider() {
		return valider;
	}

	public void setValider(boolean valider) {
		this.valider = valider;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


    
    
}
