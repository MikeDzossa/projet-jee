package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import projet.commun.dto.DtoEmprunt;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceEmprunt;
import projet.ejb.dao.IDaoEmprunt;
import projet.ejb.data.Emprunt;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceEmprunt implements IServiceEmprunt {

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoEmprunt daoEmprunt;

	// Actions

	@Override
	public int inserer(DtoEmprunt dtoEmprunter) throws ExceptionValidation {
		verifierValiditeDonnees(dtoEmprunter);
		int id = daoEmprunt.inserer(mapper.map(dtoEmprunter));
		return id;
	}

	@Override
	public void modifier(DtoEmprunt dtoEmprunter) throws ExceptionValidation {
		//verifierValiditeDonnees(dtoEmprunter);
		daoEmprunt.modifier(mapper.map(dtoEmprunter));
	}

	@Override
	public void supprimer(int idEmprunter) throws ExceptionValidation {
		
		daoEmprunt.supprimer(idEmprunter);
	}

	/*@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoEmprunt retrouver(int idEmprunter) {
		return mapper.map(daoEmprunter.retrouver(idEmprunter));
	}*/

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoEmprunt> listerTout() {
		List<DtoEmprunt> liste = new ArrayList<>();
		for (Emprunt emprunter : daoEmprunt.listerTout()) {
			liste.add(mapper.map(emprunter));
		}
		return liste;
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoEmprunt> listerPourEmprunteur( int idPersonne) {
		List<DtoEmprunt> liste = new ArrayList<>();
		for (Emprunt emprunter : daoEmprunt.listerPourEmprunteur(idPersonne)) {
			liste.add(mapper.map(emprunter));
		}
		return liste;
	}
	
	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoEmprunt> listerPourOuvrage( int idOuvrage) {
		List<DtoEmprunt> liste = new ArrayList<>();
		for (Emprunt emprunter : daoEmprunt.listerPourOuvrage(idOuvrage)) {
			liste.add(mapper.map(emprunter));
		}
		return liste;
	}
	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoEmprunt dtoEmprunter) throws ExceptionValidation {

	}

	@Override
	public DtoEmprunt retrouver(int idEmprunt) {
		// TODO Auto-generated method stub
		return null;
	}

}
