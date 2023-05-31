package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoAuteur;
import projet.commun.exception.ExceptionValidation;


public interface IServiceAuteur {
	
	int		inserer( DtoAuteur dtoAuteur ) throws ExceptionValidation;

	void	modifier( DtoAuteur dtoAuteur ) throws ExceptionValidation;

	void	supprimer( int idAuteur ) throws ExceptionValidation;

	DtoAuteur	retrouver( int idAuteur );

	List<DtoAuteur>	listerTout();

}
