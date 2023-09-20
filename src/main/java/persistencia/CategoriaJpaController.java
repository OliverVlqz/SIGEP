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
import javax.persistence.Persistence;
import logica.Categoria;
import logica.Publicacion;
import logica.exceptions.NonexistentEntityException;

/**
 *
 * @author olive
 */
public class CategoriaJpaController implements Serializable {

    public CategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public CategoriaJpaController() {
        emf=Persistence.createEntityManagerFactory("sigep");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    

    public void create(Categoria categoria) {
        if (categoria.getPublicaciones() == null) {
            categoria.setPublicaciones(new ArrayList<Publicacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Publicacion> attachedPublicaciones = new ArrayList<Publicacion>();
            for (Publicacion publicacionesPublicacionToAttach : categoria.getPublicaciones()) {
                publicacionesPublicacionToAttach = em.getReference(publicacionesPublicacionToAttach.getClass(), publicacionesPublicacionToAttach.getId());
                attachedPublicaciones.add(publicacionesPublicacionToAttach);
            }
            categoria.setPublicaciones(attachedPublicaciones);
            em.persist(categoria);
            for (Publicacion publicacionesPublicacion : categoria.getPublicaciones()) {
                Categoria oldCategoriaOfPublicacionesPublicacion = publicacionesPublicacion.getCategoria();
                publicacionesPublicacion.setCategoria(categoria);
                publicacionesPublicacion = em.merge(publicacionesPublicacion);
                if (oldCategoriaOfPublicacionesPublicacion != null) {
                    oldCategoriaOfPublicacionesPublicacion.getPublicaciones().remove(publicacionesPublicacion);
                    oldCategoriaOfPublicacionesPublicacion = em.merge(oldCategoriaOfPublicacionesPublicacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getId());
            List<Publicacion> publicacionesOld = persistentCategoria.getPublicaciones();
            List<Publicacion> publicacionesNew = categoria.getPublicaciones();
            List<Publicacion> attachedPublicacionesNew = new ArrayList<Publicacion>();
            for (Publicacion publicacionesNewPublicacionToAttach : publicacionesNew) {
                publicacionesNewPublicacionToAttach = em.getReference(publicacionesNewPublicacionToAttach.getClass(), publicacionesNewPublicacionToAttach.getId());
                attachedPublicacionesNew.add(publicacionesNewPublicacionToAttach);
            }
            publicacionesNew = attachedPublicacionesNew;
            categoria.setPublicaciones(publicacionesNew);
            categoria = em.merge(categoria);
            for (Publicacion publicacionesOldPublicacion : publicacionesOld) {
                if (!publicacionesNew.contains(publicacionesOldPublicacion)) {
                    publicacionesOldPublicacion.setCategoria(null);
                    publicacionesOldPublicacion = em.merge(publicacionesOldPublicacion);
                }
            }
            for (Publicacion publicacionesNewPublicacion : publicacionesNew) {
                if (!publicacionesOld.contains(publicacionesNewPublicacion)) {
                    Categoria oldCategoriaOfPublicacionesNewPublicacion = publicacionesNewPublicacion.getCategoria();
                    publicacionesNewPublicacion.setCategoria(categoria);
                    publicacionesNewPublicacion = em.merge(publicacionesNewPublicacion);
                    if (oldCategoriaOfPublicacionesNewPublicacion != null && !oldCategoriaOfPublicacionesNewPublicacion.equals(categoria)) {
                        oldCategoriaOfPublicacionesNewPublicacion.getPublicaciones().remove(publicacionesNewPublicacion);
                        oldCategoriaOfPublicacionesNewPublicacion = em.merge(oldCategoriaOfPublicacionesNewPublicacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = categoria.getId();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            List<Publicacion> publicaciones = categoria.getPublicaciones();
            for (Publicacion publicacionesPublicacion : publicaciones) {
                publicacionesPublicacion.setCategoria(null);
                publicacionesPublicacion = em.merge(publicacionesPublicacion);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
