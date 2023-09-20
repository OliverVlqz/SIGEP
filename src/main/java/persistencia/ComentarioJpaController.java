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
import logica.Comentario;
import logica.Publicacion;
import logica.Usuario;
import logica.exceptions.NonexistentEntityException;

/**
 *
 * @author olive
 */
public class ComentarioJpaController implements Serializable {

    public ComentarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ComentarioJpaController() {
        emf=Persistence.createEntityManagerFactory("sigep");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comentario comentario) {
        if (comentario.getRespuestas() == null) {
            comentario.setRespuestas(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = comentario.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                comentario.setUsuario(usuario);
            }
            Publicacion publicacion = comentario.getPublicacion();
            if (publicacion != null) {
                publicacion = em.getReference(publicacion.getClass(), publicacion.getId());
                comentario.setPublicacion(publicacion);
            }
            Comentario comentarioPadre = comentario.getComentarioPadre();
            if (comentarioPadre != null) {
                comentarioPadre = em.getReference(comentarioPadre.getClass(), comentarioPadre.getId());
                comentario.setComentarioPadre(comentarioPadre);
            }
            List<Comentario> attachedRespuestas = new ArrayList<Comentario>();
            for (Comentario respuestasComentarioToAttach : comentario.getRespuestas()) {
                respuestasComentarioToAttach = em.getReference(respuestasComentarioToAttach.getClass(), respuestasComentarioToAttach.getId());
                attachedRespuestas.add(respuestasComentarioToAttach);
            }
            comentario.setRespuestas(attachedRespuestas);
            em.persist(comentario);
            if (usuario != null) {
                usuario.getComentarios().add(comentario);
                usuario = em.merge(usuario);
            }
            if (publicacion != null) {
                publicacion.getComentarios().add(comentario);
                publicacion = em.merge(publicacion);
            }
            if (comentarioPadre != null) {
                comentarioPadre.getRespuestas().add(comentario);
                comentarioPadre = em.merge(comentarioPadre);
            }
            for (Comentario respuestasComentario : comentario.getRespuestas()) {
                respuestasComentario.getRespuestas().add(comentario);
                respuestasComentario = em.merge(respuestasComentario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comentario comentario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comentario persistentComentario = em.find(Comentario.class, comentario.getId());
            Usuario usuarioOld = persistentComentario.getUsuario();
            Usuario usuarioNew = comentario.getUsuario();
            Publicacion publicacionOld = persistentComentario.getPublicacion();
            Publicacion publicacionNew = comentario.getPublicacion();
            Comentario comentarioPadreOld = persistentComentario.getComentarioPadre();
            Comentario comentarioPadreNew = comentario.getComentarioPadre();
            List<Comentario> respuestasOld = persistentComentario.getRespuestas();
            List<Comentario> respuestasNew = comentario.getRespuestas();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                comentario.setUsuario(usuarioNew);
            }
            if (publicacionNew != null) {
                publicacionNew = em.getReference(publicacionNew.getClass(), publicacionNew.getId());
                comentario.setPublicacion(publicacionNew);
            }
            if (comentarioPadreNew != null) {
                comentarioPadreNew = em.getReference(comentarioPadreNew.getClass(), comentarioPadreNew.getId());
                comentario.setComentarioPadre(comentarioPadreNew);
            }
            List<Comentario> attachedRespuestasNew = new ArrayList<Comentario>();
            for (Comentario respuestasNewComentarioToAttach : respuestasNew) {
                respuestasNewComentarioToAttach = em.getReference(respuestasNewComentarioToAttach.getClass(), respuestasNewComentarioToAttach.getId());
                attachedRespuestasNew.add(respuestasNewComentarioToAttach);
            }
            respuestasNew = attachedRespuestasNew;
            comentario.setRespuestas(respuestasNew);
            comentario = em.merge(comentario);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getComentarios().remove(comentario);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getComentarios().add(comentario);
                usuarioNew = em.merge(usuarioNew);
            }
            if (publicacionOld != null && !publicacionOld.equals(publicacionNew)) {
                publicacionOld.getComentarios().remove(comentario);
                publicacionOld = em.merge(publicacionOld);
            }
            if (publicacionNew != null && !publicacionNew.equals(publicacionOld)) {
                publicacionNew.getComentarios().add(comentario);
                publicacionNew = em.merge(publicacionNew);
            }
            if (comentarioPadreOld != null && !comentarioPadreOld.equals(comentarioPadreNew)) {
                comentarioPadreOld.getRespuestas().remove(comentario);
                comentarioPadreOld = em.merge(comentarioPadreOld);
            }
            if (comentarioPadreNew != null && !comentarioPadreNew.equals(comentarioPadreOld)) {
                comentarioPadreNew.getRespuestas().add(comentario);
                comentarioPadreNew = em.merge(comentarioPadreNew);
            }
            for (Comentario respuestasOldComentario : respuestasOld) {
                if (!respuestasNew.contains(respuestasOldComentario)) {
                    respuestasOldComentario.getRespuestas().remove(comentario);
                    respuestasOldComentario = em.merge(respuestasOldComentario);
                }
            }
            for (Comentario respuestasNewComentario : respuestasNew) {
                if (!respuestasOld.contains(respuestasNewComentario)) {
                    respuestasNewComentario.getRespuestas().add(comentario);
                    respuestasNewComentario = em.merge(respuestasNewComentario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comentario.getId();
                if (findComentario(id) == null) {
                    throw new NonexistentEntityException("The comentario with id " + id + " no longer exists.");
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
            Comentario comentario;
            try {
                comentario = em.getReference(Comentario.class, id);
                comentario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentario with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = comentario.getUsuario();
            if (usuario != null) {
                usuario.getComentarios().remove(comentario);
                usuario = em.merge(usuario);
            }
            Publicacion publicacion = comentario.getPublicacion();
            if (publicacion != null) {
                publicacion.getComentarios().remove(comentario);
                publicacion = em.merge(publicacion);
            }
            Comentario comentarioPadre = comentario.getComentarioPadre();
            if (comentarioPadre != null) {
                comentarioPadre.getRespuestas().remove(comentario);
                comentarioPadre = em.merge(comentarioPadre);
            }
            List<Comentario> respuestas = comentario.getRespuestas();
            for (Comentario respuestasComentario : respuestas) {
                respuestasComentario.getRespuestas().remove(comentario);
                respuestasComentario = em.merge(respuestasComentario);
            }
            em.remove(comentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comentario> findComentarioEntities() {
        return findComentarioEntities(true, -1, -1);
    }

    public List<Comentario> findComentarioEntities(int maxResults, int firstResult) {
        return findComentarioEntities(false, maxResults, firstResult);
    }

    private List<Comentario> findComentarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comentario.class));
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

    public Comentario findComentario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comentario.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comentario> rt = cq.from(Comentario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
