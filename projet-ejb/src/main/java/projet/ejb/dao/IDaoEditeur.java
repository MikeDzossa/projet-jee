package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Editeur;


public interface IDaoEditeur {

	int			inserer( Editeur editeur );

	void 		modifier( Editeur editeur );

	void 		supprimer( int idEditeur );

	Editeur 	retrouver( int idEditeur );

	List<Editeur> listerTout();

}
