package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Emprunt;



public interface IDaoEmprunt {

	int			inserer( Emprunt emprunt );

	void 		modifier( Emprunt emprunt );

	void 		supprimer( int idEmprunt );

	Emprunt 	retrouver( int idEmprunt );

	List<Emprunt> listerTout();

	List<Emprunt> listerPourEmprunteur(int idPersonne);

	List<Emprunt> listerPourOuvrage(int idOuvrage);


}
