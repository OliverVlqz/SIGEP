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
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public UsuarioJpaController() {
        emf=Persistence.createEntityManagerFactory("sigep");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getPublicaciones() == null) {
            usuario.setPublicaciones(new ArrayList<Publicacion>());
        }
        if (usuario.getComentarios() == null) {
            usuario.setComentarios(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Publicacion> attachedPublicaciones = new ArrayList<Publicacion>();
            for (Publicacion publicacionesPublicacionToAttach : usuario.getPublicaciones()) {
                publicacionesPublicacionToAttach = em.getReference(publicacionesPublicacionToAttach.getClass(), publicacionesPublicacionToAttach.getId());
                attachedPublicaciones.add(publicacionesPublicacionToAttach);
            }
            usuario.setPublicaciones(attachedPublicaciones);
            List<Comentario> attachedComentarios = new ArrayList<Comentario>();
            for (Comentario comentariosComentarioToAttach : usuario.getComentarios()) {
                comentariosComentarioToAttach = em.getReference(comentariosComentarioToAttach.getClass(), comentariosComentarioToAttach.getId());
                attachedComentarios.add(comentariosComentarioToAttach);
            }
            usuario.setComentarios(attachedComentarios);
            em.persist(usuario);
            for (Publicacion publicacionesPublicacion : usuario.getPublicaciones()) {
                Usuario oldUsuarioOfPublicacionesPublicacion = publicacionesPublicacion.getUsuario();
                publicacionesPublicacion.setUsuario(usuario);
                publicacionesPublicacion = em.merge(publicacionesPublicacion);
                if (oldUsuarioOfPublicacionesPublicacion != null) {
                    oldUsuarioOfPublicacionesPublicacion.getPublicaciones().remove(publicacionesPublicacion);
                    oldUsuarioOfPublicacionesPublicacion = em.merge(oldUsuarioOfPublicacionesPublicacion);
                }
            }
            for (Comentario comentariosComentario : usuario.getComentarios()) {
                Usuario oldUsuarioOfComentariosComentario = comentariosComentario.getUsuario();
                comentariosComentario.setUsuario(usuario);
                comentariosComentario = em.merge(comentariosComentario);
                if (oldUsuarioOfComentariosComentario != null) {
                    oldUsuarioOfComentariosComentario.getComentarios().remove(comentariosComentario);
                    oldUsuarioOfComentariosComentario = em.merge(oldUsuarioOfComentariosComentario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Publicacion> publicacionesOld = persistentUsuario.getPublicaciones();
            List<Publicacion> publicacionesNew = usuario.getPublicaciones();
            List<Comentario> comentariosOld = persistentUsuario.getComentarios();
            List<Comentario> comentariosNew = usuario.getComentarios();
            List<Publicacion> attachedPublicacionesNew = new ArrayList<Publicacion>();
            for (Publicacion publicacionesNewPublicacionToAttach : publicacionesNew) {
                publicacionesNewPublicacionToAttach = em.getReference(publicacionesNewPublicacionToAttach.getClass(), publicacionesNewPublicacionToAttach.getId());
                attachedPublicacionesNew.add(publicacionesNewPublicacionToAttach);
            }
            publicacionesNew = attachedPublicacionesNew;
            usuario.setPublicaciones(publicacionesNew);
            List<Comentario> attachedComentariosNew = new ArrayList<Comentario>();
            for (Comentario comentariosNewComentarioToAttach : comentariosNew) {
                comentariosNewComentarioToAttach = em.getReference(comentariosNewComentarioToAttach.getClass(), comentariosNewComentarioToAttach.getId());
                attachedComentariosNew.add(comentariosNewComentarioToAttach);
            }
            comentariosNew = attachedComentariosNew;
            usuario.setComentarios(comentariosNew);
            usuario = em.merge(usuario);
            for (Publicacion publicacionesOldPublicacion : publicacionesOld) {
                if (!publicacionesNew.contains(publicacionesOldPublicacion)) {
                    publicacionesOldPublicacion.setUsuario(null);
                    publicacionesOldPublicacion = em.merge(publicacionesOldPublicacion);
                }
            }
            for (Publicacion publicacionesNewPublicacion : publicacionesNew) {
                if (!publicacionesOld.contains(publicacionesNewPublicacion)) {
                    Usuario oldUsuarioOfPublicacionesNewPublicacion = publicacionesNewPublicacion.getUsuario();
                    publicacionesNewPublicacion.setUsuario(usuario);
                    publicacionesNewPublicacion = em.merge(publicacionesNewPublicacion);
                    if (oldUsuarioOfPublicacionesNewPublicacion != null && !oldUsuarioOfPublicacionesNewPublicacion.equals(usuario)) {
                        oldUsuarioOfPublicacionesNewPublicacion.getPublicaciones().remove(publicacionesNewPublicacion);
                        oldUsuarioOfPublicacionesNewPublicacion = em.merge(oldUsuarioOfPublicacionesNewPublicacion);
                    }
                }
            }
            for (Comentario comentariosOldComentario : comentariosOld) {
                if (!comentariosNew.contains(comentariosOldComentario)) {
                    comentariosOldComentario.setUsuario(null);
                    comentariosOldComentario = em.merge(comentariosOldComentario);
                }
            }
            for (Comentario comentariosNewComentario : comentariosNew) {
                if (!comentariosOld.contains(comentariosNewComentario)) {
                    Usuario oldUsuarioOfComentariosNewComentario = comentariosNewComentario.getUsuario();
                    comentariosNewComentario.setUsuario(usuario);
                    comentariosNewComentario = em.merge(comentariosNewComentario);
                    if (oldUsuarioOfComentariosNewComentario != null && !oldUsuarioOfComentariosNewComentario.equals(usuario)) {
                        oldUsuarioOfComentariosNewComentario.getComentarios().remove(comentariosNewComentario);
                        oldUsuarioOfComentariosNewComentario = em.merge(oldUsuarioOfComentariosNewComentario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Publicacion> publicaciones = usuario.getPublicaciones();
            for (Publicacion publicacionesPublicacion : publicaciones) {
                publicacionesPublicacion.setUsuario(null);
                publicacionesPublicacion = em.merge(publicacionesPublicacion);
            }
            List<Comentario> comentarios = usuario.getComentarios();
            for (Comentario comentariosComentario : comentarios) {
                comentariosComentario.setUsuario(null);
                comentariosComentario = em.merge(comentariosComentario);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
