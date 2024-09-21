import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*EntityManagerFactory einmal erstellen und am Schluss schließen,
    damit EntityManager in DAO erstellen*/

public class EmFactory {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("GhostNetFishing");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void shutdown() {
        if (emf != null) {
            emf.close();
        }
    }
}

