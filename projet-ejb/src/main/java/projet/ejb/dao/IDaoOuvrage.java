package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Ouvrage;


public interface IDaoOuvrage {

	int			inserer( Ouvrage ouvrage );

	void 		modifier( Ouvrage ouvrage );

	void 		supprimer( int idOuvrage );

	Ouvrage 	retrouver( int idOuvrage );

	List<Ouvrage> listerTout();
    
    int 		compterPourCategorie( int idCategorie );
    
    int 		compterPourEditeur( int idEditeur );
    
    int 		compterPourAuteur( int idAuteur );
    
    int 		compterPourPersonne( int idProprietaire );

}
