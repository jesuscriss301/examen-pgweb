/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.Smartphone;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jesus
 */
public class SmartphoneJpaController implements Serializable {

    public SmartphoneJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Smartphone smartphone) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(smartphone);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSmartphone(smartphone.getId()) != null) {
                throw new PreexistingEntityException("Smartphone " + smartphone + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Smartphone smartphone) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            smartphone = em.merge(smartphone);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = smartphone.getId();
                if (findSmartphone(id) == null) {
                    throw new NonexistentEntityException("The smartphone with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Smartphone smartphone;
            try {
                smartphone = em.getReference(Smartphone.class, id);
                smartphone.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The smartphone with id " + id + " no longer exists.", enfe);
            }
            em.remove(smartphone);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Smartphone> findSmartphoneEntities() {
        return findSmartphoneEntities(true, -1, -1);
    }

    public List<Smartphone> findSmartphoneEntities(int maxResults, int firstResult) {
        return findSmartphoneEntities(false, maxResults, firstResult);
    }

    private List<Smartphone> findSmartphoneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Smartphone.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Smartphone findSmartphone(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Smartphone.class, id);
        } finally {
            em.close();
        }
    }

    public int getSmartphoneCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Smartphone> rt = cq.from(Smartphone.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
