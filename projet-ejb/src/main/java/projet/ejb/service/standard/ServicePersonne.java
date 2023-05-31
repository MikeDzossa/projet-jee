package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import projet.commun.dto.DtoPersonne;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServicePersonne;
import projet.ejb.dao.IDaoPersonne;
import projet.ejb.dao.IDaoOuvrage;
import projet.ejb.data.Personne;
import projet.ejb.data.mapper.IMapperEjb;
import projet.ejb.data.mapper.IMapperEjbAmiCourant;
import projet.ejb.data.mapper.IMapperEjbUtilisateurActif;

@Stateless
@Remote
public class ServicePersonne implements IServicePersonne {

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IMapperEjbUtilisateurActif mapperUser;
	@Inject
	private IMapperEjbAmiCourant mapperAmi;
	@Inject
	private IDaoPersonne daoPersonne;
	@Inject
	private IDaoOuvrage daoOuvrage;
	// Actions

	@Override
	public int inserer(DtoPersonne dtoPersonne) throws ExceptionValidation {
		verifierValiditeDonnees(dtoPersonne);
		int id = daoPersonne.inserer(mapper.map(dtoPersonne));
		return id;
	}

	@Override
	public void modifier(DtoPersonne dtoPersonne) throws ExceptionValidation {
		verifierValiditeDonnees(dtoPersonne);
		daoPersonne.modifier(mapper.map(dtoPersonne));
	}

	@Override
	public void supprimer(int idPersonne) throws ExceptionValidation {
		if (daoOuvrage.compterPourPersonne(idPersonne) != 0) {
			throw new ExceptionValidation("Cet personne est attaché à des ouvrages");
		}
		daoPersonne.supprimer(idPersonne);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoPersonne retrouver(int idPersonne) {
		Personne personne = daoPersonne.retrouver(idPersonne);
		return mapper.map(personne);
	}
	
	@Override
	public DtoPersonne retrouverAmi(int idPersonne) {
		Personne personne = daoPersonne.retrouverAmi(idPersonne);
		return mapperAmi.map(personne);
	}
	
	@Override
	public DtoPersonne retrouverPourCompte(int idCompte) {
		Personne personne = daoPersonne.retrouverPourCompte(idCompte);
		return mapperUser.map(personne);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoPersonne> listerTout() {
		List<DtoPersonne> liste = new ArrayList<>();
		for (Personne personne : daoPersonne.listerTout()) {
			liste.add( mapper.map(personne) );
		}
		return liste;
	}

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoPersonne dtoPersonne) throws ExceptionValidation {

		StringBuilder message = new StringBuilder();

		if (dtoPersonne.getNom() == null || dtoPersonne.getNom().isEmpty()) {
			message.append("\nLe nom est absent.");
		} else if (dtoPersonne.getNom().length() > 25) {
			message.append("\nLe nom est trop long.");
		}

		if (dtoPersonne.getPrenom() == null || dtoPersonne.getPrenom().isEmpty()) {
			message.append("\nLe prénom est absent.");
		} else if (dtoPersonne.getPrenom().length() > 25) {
			message.append("\nLe prénom est trop long.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}

}
