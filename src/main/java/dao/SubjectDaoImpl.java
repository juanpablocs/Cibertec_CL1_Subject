package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Subject;

public class SubjectDaoImpl implements SubjectDao {
    
    private EntityManagerFactory factory;
    private EntityManager em;
    
    public SubjectDaoImpl() {
        factory = Persistence.createEntityManagerFactory("Cibertec_CL1_Subject");
        em = factory.createEntityManager();
    }

    @Override
    public void create(Subject s) {
        try {  
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Subject> read() {
        Query q = em.createQuery("SELECT s FROM Subject s"); 
        @SuppressWarnings("unchecked")
        List<Subject> list = q.getResultList();
        return list;
    }

    @Override
    public void update(Subject s) {
        try {           
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void delete(Integer id) {
        try {           
            em.getTransaction().begin();
            Subject s = em.find(Subject.class, id);
            if (s != null) {
                em.remove(s);
            }
            em.getTransaction().commit();
        } catch(Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e; 
        }
    }
    
    public void close() {
        if (em != null) {
            em.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

}
