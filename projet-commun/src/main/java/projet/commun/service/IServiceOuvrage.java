package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoOuvrage;
import projet.commun.exception.ExceptionValidation;


public interface IServiceOuvrage {
	
	int				inserer( DtoOuvrage dtoOuvrage ) throws ExceptionValidation;
	
	void			modifier( DtoOuvrage dtoOuvrage ) throws ExceptionValidation;
	
	void			supprimer( int idOuvrage ) throws ExceptionValidation;
	
	DtoOuvrage 	retrouver( int idOuvrage );
	
	List<DtoOuvrage> listerTout();
	

}
