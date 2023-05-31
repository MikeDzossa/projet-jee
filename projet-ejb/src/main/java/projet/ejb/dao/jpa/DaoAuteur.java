package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoAuteur;
import projet.ejb.data.Auteur;


@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoAuteur implements IDaoAuteur {

	
	// Champs

	@PersistenceContext
	private EntityManager	em;
	
	
	// Actions
	
	@Override
	public int inserer(Auteur auteur) {
		em.persist(auteur);
		em.flush();
		return auteur.getId();
	}

	@Override
	public void modifier(Auteur auteur) {
		em.merge( auteur );
	}

	@Override
	public void supprimer(int idAuteur) {
		em.remove( retrouver(idAuteur) );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Auteur retrouver(int idAuteur) {
		return em.find( Auteur.class, idAuteur );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Auteur> listerTout() {
		em.clear();
		var jpql = "SELECT a FROM Auteur a ORDER BY a.nom";
		var query = em.createQuery( jpql, Auteur.class );
		return query.getResultList();
	}
	
}
