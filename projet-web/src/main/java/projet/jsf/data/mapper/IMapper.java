package projet.jsf.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import projet.commun.dto.DtoAuteur;
import projet.commun.dto.DtoCategorie;
import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoEditeur;
import projet.commun.dto.DtoOuvrage;
import projet.commun.dto.DtoPersonne;
import projet.jsf.data.Auteur;
import projet.jsf.data.Categorie;
import projet.jsf.data.Compte;
import projet.jsf.data.Editeur;
import projet.jsf.data.Ouvrage;
import projet.jsf.data.Personne;


@Mapper( componentModel = "cdi" )
public interface IMapper {
  

	// Compte
	
	Compte    map( DtoCompte source );
	
	DtoCompte map( Compte source );
	
	Compte duplicate( Compte source );

	Compte update( @MappingTarget Compte target, Compte source );
	
	// Personne
	
	Personne    map( DtoPersonne source );
		
	DtoPersonne map( Personne source );
		
	Personne duplicate( Personne source );

	// Categorie
	
	Categorie    map( DtoCategorie source );
	
	DtoCategorie map( Categorie source );
	
	Categorie duplicate( Categorie source );
	
	// Editeur
	
	Editeur    map( DtoEditeur source );
	
	DtoEditeur map( Editeur source );
	
	Editeur duplicate( Editeur source );
	
	// Auteur
	
	Auteur    map( DtoAuteur source );
	
	DtoAuteur map( Auteur source );
	
	Auteur duplicate( Auteur source );
	
	// Ouvrage
	
	Ouvrage    map( DtoOuvrage source );
		
	DtoOuvrage map( Ouvrage source );
		
	Ouvrage duplicate( Ouvrage source );
	
	// For compte update

	List<Personne> duplicate( List<Personne> source );

}
