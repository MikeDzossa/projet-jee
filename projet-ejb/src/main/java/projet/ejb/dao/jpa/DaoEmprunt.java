package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoEmprunt;
import projet.ejb.data.Emprunt;
import projet.ejb.data.Personne;


@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoEmprunt implements IDaoEmprunt {

	
	// Champs

	@PersistenceContext
	private EntityManager	em;
	
	
	// Actions
	
	@Override
	public int inserer(Emprunt emprunt) {
		em.persist(emprunt);
		em.flush();
		return emprunt.getId();
	}

	@Override
	public void modifier(Emprunt emprunt) {
		em.merge( emprunt );
	}

	@Override
	public void supprimer(int idEmprunt) {
		em.remove( retrouver(idEmprunt) );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Emprunt retrouver(int idEmprunt) {
		return em.find( Emprunt.class, idEmprunt );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Emprunt> listerTout() {
		em.clear();
		var jpql = "SELECT e FROM Emprunt e ";
		var query = em.createQuery( jpql, Emprunt.class );
		return query.getResultList(); 
	}
	
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Emprunt> listerPourEmprunteur(int idPersonne) {
		em.clear();
		var jpql = "SELECT e FROM Emprunt e WHERE e.emprunteur.id =:idPersonne ";
		var query = em.createQuery( jpql, Emprunt.class );
		query.setParameter("idPersonne", idPersonne);
		return query.getResultList(); 
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Emprunt> listerPourOuvrage(int idOuvrage) {
		em.clear();
		var jpql = "SELECT e FROM Emprunt e WHERE e.ouvrage.id =:idOuvrage ";
		var query = em.createQuery( jpql, Emprunt.class );
		query.setParameter("idOuvrage", idOuvrage);
		return query.getResultList(); 
	}
	
	
}
