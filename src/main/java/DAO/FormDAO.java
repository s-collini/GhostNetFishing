package DAO;

import Model.Form;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.util.List;

@Named
@ApplicationScoped
public class FormDAO {

    private EntityManagerFactory emf = EmFactory.getEntityManagerFactory();

        public List<Form> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Form> forms = null;

        try {
            TypedQuery<Form> query = em.createQuery("SELECT f FROM Form f", Form.class);
            forms = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Verwendet printStackTrace zum Protokollieren von Fehlern
        } finally {
            em.close();
        }

        return forms;
    }

    public Form findById(long id) {
        EntityManager em = emf.createEntityManager();
        Form form = null;

        try {
            form = em.find(Form.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return form;
    }

    public void save(Form form) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(form);
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

