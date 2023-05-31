package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoOuvrage;
import projet.ejb.data.Ouvrage;


@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoOuvrage implements IDaoOuvrage {
	
	// Champs
	
	@PersistenceContext
	private EntityManager	em;

	
	// Actions
	
	@Override
	public int inserer(Ouvrage ouvrage) {
		em.persist(ouvrage);
		em.flush();
		return ouvrage.getId();
	}

	@Override
	public void modifier(Ouvrage ouvrage) {
		em.merge( ouvrage );
	}

	@Override
	public void supprimer(int idOuvrage) {
		em.remove( retrouver(idOuvrage) );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Ouvrage retrouver(int idOuvrage) {
		var graph = em.createEntityGraph( Ouvrage.class );
		graph.addAttributeNodes( "categorie" );
		graph.addAttributeNodes( "auteur" );
		graph.addAttributeNodes( "editeur" );
		graph.addAttributeNodes( "proprietaire" );
		var props = new HashMap<String, Object>();
		props.put( "javax.persistence.loadgraph", graph );
		return em.find( Ouvrage.class, idOuvrage, props );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Ouvrage> listerTout() {
		em.clear();
		var jpql = "SELECT o FROM Ouvrage o ORDER BY o.titre";
		var query = em.createQuery( jpql, Ouvrage.class );
		return query.getResultList();
	}


	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public int compterPourCategorie(int idCategorie) {
		var jpql = "SELECT COUNT(o) FROM Ouvrage o WHERE o.categorie.id = :idCategorie";
		var query = em.createQuery( jpql, Long.class );
		query.setParameter( "idCategorie", idCategorie );
		return query.getSingleResult().intValue();
	}
	
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public int compterPourEditeur(int idEditeur) {
		var jpql = "SELECT COUNT(o) FROM Ouvrage o WHERE o.editeur.id = :idEditeur";
		var query = em.createQuery( jpql, Long.class );
		query.setParameter( "idEditeur", idEditeur );
		return query.getSingleResult().intValue();
	}
	
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public int compterPourAuteur(int idAuteur) {
		var jpql = "SELECT COUNT(o) FROM Ouvrage o WHERE o.auteur.id = :idAuteur";
		var query = em.createQuery( jpql, Long.class );
		query.setParameter( "idAuteur", idAuteur );
		return query.getSingleResult().intValue();
	}
	
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public int compterPourPersonne(int idProprietaire) {
		var jpql = "SELECT COUNT(o) FROM Ouvrage o WHERE o.proprietaire.id = :idProprietaire";
		var query = em.createQuery( jpql, Long.class );
		query.setParameter( "idProprietaire", idProprietaire );
		return query.getSingleResult().intValue();
	}

}
