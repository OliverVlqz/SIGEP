/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import logica.PalabrasAltisonantes;
import logica.Publicacion;
import logica.exceptions.NonexistentEntityException;

/**
 *
 * @author olive
 */
public class PalabrasAltisonantesJpaController implements Serializable {

    public PalabrasAltisonantesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PalabrasAltisonantes palabrasAltisonantes) {
        if (palabrasAltisonantes.getPublicaciones() == null) {
            palabrasAltisonantes.setPublicaciones(new ArrayList<Publicacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Publicacion> attachedPublicaciones = new ArrayList<Publicacion>();
            for (Publicacion publicacionesPublicacionToAttach : palabrasAltisonantes.getPublicaciones()) {
                publicacionesPublicacionToAttach = em.getReference(publicacionesPublicacionToAttach.getClass(), publicacionesPublicacionToAttach.getId());
                attachedPublicaciones.add(publicacionesPublicacionToAttach);
            }
            palabrasAltisonantes.setPublicaciones(attachedPublicaciones);
            em.persist(palabrasAltisonantes);
            for (Publicacion publicacionesPublicacion : palabrasAltisonantes.getPublicaciones()) {
                publicacionesPublicacion.getPalabrasAltisonantes().add(palabrasAltisonantes);
                publicacionesPublicacion = em.merge(publicacionesPublicacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PalabrasAltisonantes palabrasAltisonantes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PalabrasAltisonantes persistentPalabrasAltisonantes = em.find(PalabrasAltisonantes.class, palabrasAltisonantes.getId());
            List<Publicacion> publicacionesOld = persistentPalabrasAltisonantes.getPublicaciones();
            List<Publicacion> publicacionesNew = palabrasAltisonantes.getPublicaciones();
            List<Publicacion> attachedPublicacionesNew = new ArrayList<Publicacion>();
            for (Publicacion publicacionesNewPublicacionToAttach : publicacionesNew) {
                publicacionesNewPublicacionToAttach = em.getReference(publicacionesNewPublicacionToAttach.getClass(), publicacionesNewPublicacionToAttach.getId());
                attachedPublicacionesNew.add(publicacionesNewPublicacionToAttach);
            }
            publicacionesNew = attachedPublicacionesNew;
            palabrasAltisonantes.setPublicaciones(publicacionesNew);
            palabrasAltisonantes = em.merge(palabrasAltisonantes);
            for (Publicacion publicacionesOldPublicacion : publicacionesOld) {
                if (!publicacionesNew.contains(publicacionesOldPublicacion)) {
                    publicacionesOldPublicacion.getPalabrasAltisonantes().remove(palabrasAltisonantes);
                    publicacionesOldPublicacion = em.merge(publicacionesOldPublicacion);
                }
            }
            for (Publicacion publicacionesNewPublicacion : publicacionesNew) {
                if (!publicacionesOld.contains(publicacionesNewPublicacion)) {
                    publicacionesNewPublicacion.getPalabrasAltisonantes().add(palabrasAltisonantes);
                    publicacionesNewPublicacion = em.merge(publicacionesNewPublicacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = palabrasAltisonantes.getId();
                if (findPalabrasAltisonantes(id) == null) {
                    throw new NonexistentEntityException("The palabrasAltisonantes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PalabrasAltisonantes palabrasAltisonantes;
            try {
                palabrasAltisonantes = em.getReference(PalabrasAltisonantes.class, id);
                palabrasAltisonantes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The palabrasAltisonantes with id " + id + " no longer exists.", enfe);
            }
            List<Publicacion> publicaciones = palabrasAltisonantes.getPublicaciones();
            for (Publicacion publicacionesPublicacion : publicaciones) {
                publicacionesPublicacion.getPalabrasAltisonantes().remove(palabrasAltisonantes);
                publicacionesPublicacion = em.merge(publicacionesPublicacion);
            }
            em.remove(palabrasAltisonantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PalabrasAltisonantes> findPalabrasAltisonantesEntities() {
        return findPalabrasAltisonantesEntities(true, -1, -1);
    }

    public List<PalabrasAltisonantes> findPalabrasAltisonantesEntities(int maxResults, int firstResult) {
        return findPalabrasAltisonantesEntities(false, maxResults, firstResult);
    }

    private List<PalabrasAltisonantes> findPalabrasAltisonantesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PalabrasAltisonantes.class));
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

    public PalabrasAltisonantes findPalabrasAltisonantes(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PalabrasAltisonantes.class, id);
        } finally {
            em.close();
        }
    }

    public int getPalabrasAltisonantesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PalabrasAltisonantes> rt = cq.from(PalabrasAltisonantes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
