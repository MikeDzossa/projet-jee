package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoEditeur;
import projet.ejb.data.Editeur;


@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoEditeur implements IDaoEditeur {

	
	// Champs

	@PersistenceContext
	private EntityManager	em;
	
	
	// Actions
	
	@Override
	public int inserer(Editeur editeur) {
		em.persist(editeur);
		em.flush();
		return editeur.getId();
	}

	@Override
	public void modifier(Editeur editeur) {
		em.merge( editeur );
	}

	@Override
	public void supprimer(int idEditeur) {
		em.remove( retrouver(idEditeur) );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Editeur retrouver(int idEditeur) {
		return em.find( Editeur.class, idEditeur );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Editeur> listerTout() {
		em.clear();
		var jpql = "SELECT e FROM Editeur e ORDER BY e.nom";
		var query = em.createQuery( jpql, Editeur.class );
		return query.getResultList();
	}
	
}
