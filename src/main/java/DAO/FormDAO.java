package DAO;

import Model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.util.List;

@Named
@ApplicationScoped
public class PersonDAO {

    private EntityManagerFactory emf = EmFactory.getEntityManagerFactory();

        public List<Person> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Person> data = null;

        try {
            TypedQuery<Person> query = em.createQuery("SELECT f FROM Form f", Form.class);
            data = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Verwendet printStackTrace zum Protokollieren von Fehlern
        } finally {
            em.close();
        }

        return data;
    }

    public Form findById(long id) {
        EntityManager em = emf.createEntityManager();
        Form person = null;

        try {
            form = em.find(Form.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return form;
    }

    public void update(Form person) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(form); // Aktualisiert das bestehende Entity
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

    public void save(Form person) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(form); // Speichert eine neue Entität
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

}

