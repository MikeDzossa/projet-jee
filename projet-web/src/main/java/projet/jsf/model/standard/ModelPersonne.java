package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoPersonne;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServicePersonne;
import projet.jsf.data.Compte;
import projet.jsf.data.Ouvrage;
import projet.jsf.data.Personne;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.CompteActif;
import projet.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@ViewScoped
@Named
public class ModelPersonne implements Serializable {

	
	// Champs
	
	private List<Personne>		liste;
	
	private Personne			courant;
	
	private Personne			utilisateurActif;
	
	private List<Personne>		demandes;
	
	private List<Personne>		requetes;
	
	private List<Personne>		etrangers;
	
	@EJB
	private IServicePersonne	servicePersonne;

	@Inject
	private IMapper				mapper;
	
	@Inject
	private CompteActif		compteActif;
	
	@Inject
	private ModelOuvrage	modelOuvrage;

	
	// Getters 
	
	public List<Personne> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoPersonne dto : servicePersonne.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}

	public Personne getCourant() {
		if ( courant == null ) {
			courant = new Personne();
			courant.setCompte( new Compte() );
		}
		for(Personne p : courant.getRequetesAmis()) {
			System.out.println("["+courant.getCompte().getPseudo()+"] : "+ p);
		}
		return courant;
	}
	
	public Personne getUtilisateurActif() {
		if(utilisateurActif == null) {
			DtoPersonne dto = servicePersonne.retrouverPourCompte( compteActif.getId() );
			if ( dto == null ) {
				UtilJsf.messageError( "La personne demandée n'existe pas" );
				return null;
			} else {
				utilisateurActif = mapper.map(dto);
			}	
		}
		return utilisateurActif;
	}
	
	public Personne getAmiCourant() {
		if(courant != null) {
			DtoPersonne dto = servicePersonne.retrouverAmi( courant.getId() );
			if ( dto == null ) {
				UtilJsf.messageError( "La personne demandée n'existe pas" );
			} else {
				courant =  mapper.map(dto);
			}	
		}
		return courant;
	}
	
	public List<Personne> getAmis(){
		List<Personne> amis = new ArrayList<>();
		for(Personne p : getUtilisateurActif().getDemandesAmis()) {
			if(getUtilisateurActif().getRequetesAmis().contains(p))
				amis.add(p);
		}
		return amis;
	}
	
	// Voir les demandes recus en attente de réponse
	public List<Personne> getDemandes(){
		actualiserDemandes();
		return demandes;
	}
	
	public void actualiserDemandes() {
		if(demandes == null) demandes = new ArrayList<>();
		demandes.clear();
		for(Personne p : getUtilisateurActif().getDemandesAmis()) {
			if(!getUtilisateurActif().getRequetesAmis().contains(p))
				demandes.add(p);
		}
	}
	
	// Voir vos requetes non-répondus
	public List<Personne> getRequetes(){
		actualiserRequetes();
		return requetes;
	}
	
	public void actualiserRequetes() {
		if(requetes == null) requetes = new ArrayList<>();
		requetes.clear();
		for(Personne p : getUtilisateurActif().getRequetesAmis()) {
			if(!getUtilisateurActif().getDemandesAmis().contains(p))
				requetes.add(p);
		}
	}
	
	public List<Personne> getEtrangers(){
		actualiserEtrangers();
		return etrangers;
	}
	
	public void actualiserEtrangers() {
		if(etrangers == null) etrangers = new ArrayList<>();
		etrangers.clear();
		for(Personne p : getListe()) {
			if(!p.equals(utilisateurActif) && !getUtilisateurActif().getDemandesAmis().contains(p) && !getUtilisateurActif().getRequetesAmis().contains(p))
				etrangers.add(p);
		}
	}
	
	
	
	
	// Initialisaitons
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoPersonne dto = servicePersonne.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "La personne demandée n'existe pas" );
				return "liste";
			} else {
				courant = mapper.map( dto );
			}
		}
		return null;
	}
	
	public String actualiserAmiCourant() {
		if ( courant != null ) {
			DtoPersonne dto = servicePersonne.retrouverAmi( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "La personne demandée n'existe pas" );
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
				servicePersonne.inserer( mapper.map(courant) );
			} else {
				servicePersonne.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Personne personne ) {
		try {
			servicePersonne.supprimer( personne.getId() );
			liste.remove(personne);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e ); 
		}
		return null;
	}
	
	
	// Gestion d'amitie
	
	public String validerOperation() {
		try {
			for(Ouvrage o : utilisateurActif.getOuvrages() ) {
				Personne p = new Personne();
				p.setId(utilisateurActif.getId());
				o.setProprietaire(p);
			}
			servicePersonne.modifier( mapper.map(utilisateurActif) );
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public void accepterDemande( Personne item ) {
		getUtilisateurActif().getRequetesAmis().add(item);
		validerOperation();
	}
	
	public void envoyerRequete( Personne item ) {
		getUtilisateurActif().getRequetesAmis().add(item);
		validerOperation();
	}
	
	public void supprimerAmi( Personne item ) {
		getUtilisateurActif().getDemandesAmis().remove( item );
		getUtilisateurActif().getRequetesAmis().remove( item );
		validerOperation();
	}
	
	public void supprimerRequete( Personne item ) {
		getUtilisateurActif().getRequetesAmis().remove( item ); 
		validerOperation();
	}
	
	public void refuserDemande( Personne item ) {
		getUtilisateurActif().getDemandesAmis().remove( item );
		validerOperation();
	}
	
	// gestion des ouvrages
	
	public void supprimerOuvrage( Ouvrage item ) {
		if(getUtilisateurActif().getOuvrages().remove(item))
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
		else UtilJsf.messageError("Une erreur s'est produite.");
	}
	
	public String miseAJourOuvrage( ) {
		Ouvrage item = modelOuvrage.getCourant();
		if(!getUtilisateurActif().getOuvrages().contains(item)) {
			item.setProprietaire(utilisateurActif);
		}
		return modelOuvrage.validerMiseAJour();
	}
	
	public void emprunterOuvrage( Ouvrage item ) {
		
	}
	
}
