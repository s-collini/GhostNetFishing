import jakarta.persistence.*;
import java.util.List;

public class GhostNetsDAO {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("GhostNetFishing");


    public void save(GhostNets nets) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nets); // Speichert Geisternetz-Objekt
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback im Fehlerfall
            }
        } finally {
            em.close();
        }
    }

    public List<GhostNets> findAll() {
        EntityManager em = emf.createEntityManager();
        List<GhostNets> ghostNetsList = null;
        try {
            Query query = em.createQuery("SELECT g FROM GhostNets g");
            ghostNetsList = query.getResultList(); // Ruft Ergebnisse ab
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close(); // Schließt  EntityManager
        }
        return ghostNetsList; // Gibt Liste zurück
    }

}

