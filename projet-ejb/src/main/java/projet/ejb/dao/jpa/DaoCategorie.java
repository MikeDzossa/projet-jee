package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoCategorie;
import projet.ejb.data.Categorie;


@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoCategorie implements IDaoCategorie {

	
	// Champs

	@PersistenceContext
	private EntityManager	em;
	
	
	// Actions
	
	@Override
	public int inserer(Categorie categorie) {
		em.persist(categorie);
		em.flush();
		return categorie.getId();
	}

	@Override
	public void modifier(Categorie categorie) {
		em.merge( categorie );
	}

	@Override
	public void supprimer(int idCategorie) {
		em.remove( retrouver(idCategorie) );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Categorie retrouver(int idCategorie) {
		return em.find( Categorie.class, idCategorie );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Categorie> listerTout() {
		em.clear();
		var jpql = "SELECT c FROM Categorie c ORDER BY c.libelle";
		var query = em.createQuery( jpql, Categorie.class );
		return query.getResultList();
	}
	
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public boolean verifierUniciteLibelle( String libelle, int idCategorie )  {
	    var jpql = "SELECT COUNT(c) FROM Categorie c WHERE c.libelle=:libelle AND c.id <> :idCategorie ";
	    var query = em.createQuery( jpql, Long.class );
	    query.setParameter( "libelle", libelle );
	    query.setParameter( "idCategorie", idCategorie );
        return query.getSingleResult() == 0;
	}
	
}
