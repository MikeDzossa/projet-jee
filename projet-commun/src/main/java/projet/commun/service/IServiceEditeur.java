package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoEditeur;
import projet.commun.exception.ExceptionValidation;


public interface IServiceEditeur {
	
	int		inserer( DtoEditeur dtoEditeur ) throws ExceptionValidation;

	void	modifier( DtoEditeur dtoEditeur ) throws ExceptionValidation;

	void	supprimer( int idEditeur ) throws ExceptionValidation;

	DtoEditeur	retrouver( int idEditeur );

	List<DtoEditeur>	listerTout();

}
