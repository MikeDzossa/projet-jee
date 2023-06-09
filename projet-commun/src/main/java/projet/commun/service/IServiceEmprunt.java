package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoEmprunt;
import projet.commun.exception.ExceptionValidation;


public interface IServiceEmprunt {
	
	int		inserer( DtoEmprunt dtoEmprunt ) throws ExceptionValidation;

	void	modifier( DtoEmprunt dtoEmprunt ) throws ExceptionValidation;

	void	supprimer( int idEmprunt ) throws ExceptionValidation;

	DtoEmprunt	retrouver( int idEmprunt );

	List<DtoEmprunt>	listerTout();

	List<DtoEmprunt> listerPourEmprunteur(int idPersonne);

	List<DtoEmprunt> listerPourOuvrage(int idOuvrage);

}
