package DAO;


import Model.GhostNets;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.util.List;

@Named
@ApplicationScoped
public class GhostNetsDAO {
    
    private EntityManagerFactory emf = EmFactory.getEntityManagerFactory();

    public List<GhostNets> findAll() {
        EntityManager em = emf.createEntityManager();
        List<GhostNets> ghostNets = null;

        try {
            TypedQuery<GhostNets> query = em.createQuery("SELECT g FROM GhostNets g", GhostNets.class);
            ghostNets = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Verwendet printStackTrace zum Protokollieren von Fehlern
        } finally {
            em.close();
        }

        return ghostNets;
    }

    public GhostNets findById(long id) {
        EntityManager em = emf.createEntityManager();
        GhostNets ghostNets = null;

        try {
            ghostNets = em.find(GhostNets.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return ghostNets;
    }

    public void update(GhostNets ghostNets) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(ghostNets);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    public void save(GhostNets ghostNets) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ghostNets);
        em.getTransaction().commit();
        em.close();
    }

}
