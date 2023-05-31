package projet.ejb.data.mapper;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import org.mapstruct.factory.Mappers;

import projet.commun.dto.DtoAuteur;
import projet.commun.dto.DtoCategorie;
import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoEditeur;
import projet.commun.dto.DtoOuvrage;
import projet.commun.dto.DtoPersonne;
import projet.ejb.data.Auteur;
import projet.ejb.data.Categorie;
import projet.ejb.data.Compte;
import projet.ejb.data.Editeur;
import projet.ejb.data.Ouvrage;
import projet.ejb.data.Personne;

 
@Mapper( componentModel = "cdi" )
public interface IMapperEjbUtilisateurActif {  
	
	static final IMapperEjbUtilisateurActif INSTANCE = Mappers.getMapper( IMapperEjbUtilisateurActif.class );
	
	
	// Compte
	
	Compte map(DtoCompte source);

	DtoCompte map(Compte source);

	// Personne
	
	Personne map(DtoPersonne source);


	DtoPersonne map(Personne source);
	
	@MinimalMapping
	@Mapping( target="demandesAmis", ignore = true )
	@Mapping( target="requetesAmis", ignore = true )
	@Mapping( target="ouvrages", ignore = true )
	DtoPersonne  mapMinimal( Personne source );
	
	@IterableMapping(qualifiedBy = MinimalMapping.class)
	default List<DtoPersonne> mapAmis(List<Personne> amis) {
	    return amis.stream()
	            .map(this::mapMinimal)
	            .collect(Collectors.toList());
	}
 
	// Categorie

	Categorie map(DtoCategorie source);

	DtoCategorie map(Categorie source);

	// Editeur

	Editeur map(DtoEditeur source);

	DtoEditeur map(Editeur source);

	// Auteur

	Auteur map(DtoAuteur source);

	DtoAuteur map(Auteur source);

	// Ouvrage
	
	@Mapping( target="proprietaire", ignore = true )
	Ouvrage map( DtoOuvrage source );
		
	@Mapping( target="proprietaire", ignore = true )
	DtoOuvrage map( Ouvrage source );
	
	@IterableMapping(qualifiedBy = ListMapping.class)
	default List<DtoOuvrage> mapOuvrages(List<Ouvrage> ouvrages) {
		   return ouvrages.stream()
		            .map(this::map)
		            .collect(Collectors.toList());
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface MinimalMapping {
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface ListMapping {
	}
	
}
