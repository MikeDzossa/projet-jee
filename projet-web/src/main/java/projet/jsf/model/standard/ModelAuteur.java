package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoAuteur;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceAuteur;
import projet.jsf.data.Auteur;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelAuteur implements Serializable {

	
	// Champs
	
	private List<Auteur>		liste;
	
	private Auteur			courant;
	
	@EJB
	private IServiceAuteur	serviceAuteur;
	
	@Inject
	private IMapper				mapper;

	
	// Getters 
	
	public List<Auteur> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoAuteur dto : serviceAuteur.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}

	public Auteur getCourant() {
		if ( courant == null ) {
			courant = new Auteur();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoAuteur dto = serviceAuteur.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "L'auteur demandée n'existe pas" );
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
			if ( courant.getId() == null) {
				serviceAuteur.inserer( mapper.map(courant) );
			} else {
				serviceAuteur.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Auteur item ) {
		try {
			serviceAuteur.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e );
		}
		return null;
	}
	
}
