package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoEmprunt;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceEmprunt;
import projet.jsf.data.Emprunt;
import projet.jsf.data.Ouvrage;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelEmprunt implements Serializable {

	
	// Champs
	
	private List<Emprunt>		liste;
	private List<Emprunt>		demandes;
	private Emprunt			courant;
	
	@EJB
	private IServiceEmprunt	serviceEmprunt;
	
	@Inject
	private IMapper				mapper;
	
	@Inject
	private ModelPersonne modelPersonne;

	
	// Getters 
	
	public List<Emprunt> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoEmprunt dto : serviceEmprunt.listerPourEmprunteur(modelPersonne.getUtilisateurActif().getId()) ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}

	public List<Emprunt> getDemandes() {
		if ( demandes == null ) {
			demandes = new ArrayList<>();
			for ( Ouvrage o : modelPersonne.getUtilisateurActif().getOuvrages() ) {
				System.out.println(o.getTitre());
				for(DtoEmprunt dto : serviceEmprunt.listerPourOuvrage(o.getId())) {
					System.out.println(" Un emprunt trouvé");
					demandes.add( mapper.map( dto ) );
				}
			}
		}
		return demandes;
	}
	
	public Emprunt getCourant() {
		if ( courant == null ) {
			courant = new Emprunt();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoEmprunt dto = serviceEmprunt.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "L'emprunt demandée n'existe pas" );
				return "liste";
			} else {
				courant = mapper.map( dto );
			}
		}
		return null;
	}
	
	
	// Actions
	
	public String validerMiseAJour() {
		try {
			if ( courant.getId()==null) {
				serviceEmprunt.inserer( mapper.map(courant) );
			} else {
				serviceEmprunt.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Emprunt item ) {
		try {
			serviceEmprunt.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e );
		}
		return null;
	}
	
	public void emprunterOuvrage( Ouvrage item ) {
		courant = new Emprunt(null, item , modelPersonne.getUtilisateurActif(), false );
		if(verifieruniciterEmprunt(courant)) 
			validerMiseAJour();
		else
			courant = null;
	}
	
	public void validerEmprunt( Emprunt item ) {
		courant = item;
		validerMiseAJour();
	}
	
	boolean verifieruniciterEmprunt(Emprunt emprunt) {
		for(Emprunt e : getListe()) {
			if(e.getOuvrage().getId() == emprunt.getOuvrage().getId()) {
				UtilJsf.messageError( "vous avez déjà acces à cet Ouvrage" );
				return false;
			}
		}
		System.out.println(" ----- Je ne suis pas dans la liste ");
		return true;
	}
}
