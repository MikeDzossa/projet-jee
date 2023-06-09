package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import projet.commun.dto.DtoEditeur;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceEditeur;
import projet.ejb.dao.IDaoEditeur;
import projet.ejb.dao.IDaoOuvrage;
import projet.ejb.data.Editeur;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceEditeur implements IServiceEditeur {

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoEditeur daoEditeur;
	@Inject
	private IDaoOuvrage daoOuvrage;

	// Actions

	@Override
	public int inserer(DtoEditeur dtoEditeur) throws ExceptionValidation {
		verifierValiditeDonnees(dtoEditeur);
		int id = daoEditeur.inserer(mapper.map(dtoEditeur));
		return id;
	}

	@Override
	public void modifier(DtoEditeur dtoEditeur) throws ExceptionValidation {
		verifierValiditeDonnees(dtoEditeur);
		daoEditeur.modifier(mapper.map(dtoEditeur));
	}

	@Override
	public void supprimer(int idEditeur) throws ExceptionValidation {
		if (daoOuvrage.compterPourEditeur(idEditeur) != 0) {
			throw new ExceptionValidation("Cet editeur est attaché à un ouvrage");
		}
		daoEditeur.supprimer(idEditeur);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoEditeur retrouver(int idEditeur) {
		return mapper.map(daoEditeur.retrouver(idEditeur));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoEditeur> listerTout() {
		List<DtoEditeur> liste = new ArrayList<>();
		for (Editeur editeur : daoEditeur.listerTout()) {
			liste.add(mapper.map(editeur));
		}
		return liste;
	}

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoEditeur dtoEditeur) throws ExceptionValidation {

		StringBuilder message = new StringBuilder();

		if (dtoEditeur.getNom() == null || dtoEditeur.getNom().isEmpty()) {
			message.append("\nLe nom est absent.");
		} else if (dtoEditeur.getNom().length() < 3) {
			message.append("\nLe nom est trop court.");
		} else if (dtoEditeur.getNom().length() > 25) {
			message.append("\nLe nom est trop long.");
		} else if (!daoEditeur.verifierUniciteNom(dtoEditeur.getNom(), dtoEditeur.getId())) {
			message.append("\nLe nom " + dtoEditeur.getNom() + " est déjà utilisé.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}

}
