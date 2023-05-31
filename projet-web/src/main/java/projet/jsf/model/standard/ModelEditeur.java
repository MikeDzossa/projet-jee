package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoEditeur;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceEditeur;
import projet.jsf.data.Editeur;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelEditeur implements Serializable {

	
	// Champs
	
	private List<Editeur>		liste;
	
	private Editeur			courant;
	
	@EJB
	private IServiceEditeur	serviceEditeur;
	
	@Inject
	private IMapper				mapper;

	
	// Getters 
	
	public List<Editeur> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoEditeur dto : serviceEditeur.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}

	public Editeur getCourant() {
		if ( courant == null ) {
			courant = new Editeur();
		}
		return courant;
	}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoEditeur dto = serviceEditeur.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "L'éditeur demandée n'existe pas" );
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
				serviceEditeur.inserer( mapper.map(courant) );
			} else {
				serviceEditeur.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Editeur item ) {
		try {
			serviceEditeur.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e );
		}
		return null;
	}
	
}
