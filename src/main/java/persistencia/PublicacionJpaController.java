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
import logica.Comentario;
import logica.PalabrasAltisonantes;
import logica.Publicacion;
import logica.Usuario;
import logica.exceptions.NonexistentEntityException;

/**
 *
 * @author olive
 */
public class PublicacionJpaController implements Serializable {

    public PublicacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public PublicacionJpaController() {
        emf=Persistence.createEntityManagerFactory("sigep");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Publicacion publicacion) {
        if (publicacion.getComentarios() == null) {
            publicacion.setComentarios(new ArrayList<Comentario>());
        }
        if (publicacion.getPalabrasAltisonantes() == null) {
            publicacion.setPalabrasAltisonantes(new ArrayList<PalabrasAltisonantes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = publicacion.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                publicacion.setUsuario(usuario);
            }
            Categoria categoria = publicacion.getCategoria();
            if (categoria != null) {
                categoria = em.getReference(categoria.getClass(), categoria.getId());
                publicacion.setCategoria(categoria);
            }
            List<Comentario> attachedComentarios = new ArrayList<Comentario>();
            for (Comentario comentariosComentarioToAttach : publicacion.getComentarios()) {
                comentariosComentarioToAttach = em.getReference(comentariosComentarioToAttach.getClass(), comentariosComentarioToAttach.getId());
                attachedComentarios.add(comentariosComentarioToAttach);
            }
            publicacion.setComentarios(attachedComentarios);
            List<PalabrasAltisonantes> attachedPalabrasAltisonantes = new ArrayList<PalabrasAltisonantes>();
            for (PalabrasAltisonantes palabrasAltisonantesPalabrasAltisonantesToAttach : publicacion.getPalabrasAltisonantes()) {
                palabrasAltisonantesPalabrasAltisonantesToAttach = em.getReference(palabrasAltisonantesPalabrasAltisonantesToAttach.getClass(), palabrasAltisonantesPalabrasAltisonantesToAttach.getId());
                attachedPalabrasAltisonantes.add(palabrasAltisonantesPalabrasAltisonantesToAttach);
            }
            publicacion.setPalabrasAltisonantes(attachedPalabrasAltisonantes);
            em.persist(publicacion);
            if (usuario != null) {
                usuario.getPublicaciones().add(publicacion);
                usuario = em.merge(usuario);
            }
            if (categoria != null) {
                categoria.getPublicaciones().add(publicacion);
                categoria = em.merge(categoria);
            }
            for (Comentario comentariosComentario : publicacion.getComentarios()) {
                Publicacion oldPublicacionOfComentariosComentario = comentariosComentario.getPublicacion();
                comentariosComentario.setPublicacion(publicacion);
                comentariosComentario = em.merge(comentariosComentario);
                if (oldPublicacionOfComentariosComentario != null) {
                    oldPublicacionOfComentariosComentario.getComentarios().remove(comentariosComentario);
                    oldPublicacionOfComentariosComentario = em.merge(oldPublicacionOfComentariosComentario);
                }
            }
            for (PalabrasAltisonantes palabrasAltisonantesPalabrasAltisonantes : publicacion.getPalabrasAltisonantes()) {
                palabrasAltisonantesPalabrasAltisonantes.getPublicaciones().add(publicacion);
                palabrasAltisonantesPalabrasAltisonantes = em.merge(palabrasAltisonantesPalabrasAltisonantes);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Publicacion publicacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Publicacion persistentPublicacion = em.find(Publicacion.class, publicacion.getId());
            Usuario usuarioOld = persistentPublicacion.getUsuario();
            Usuario usuarioNew = publicacion.getUsuario();
            Categoria categoriaOld = persistentPublicacion.getCategoria();
            Categoria categoriaNew = publicacion.getCategoria();
            List<Comentario> comentariosOld = persistentPublicacion.getComentarios();
            List<Comentario> comentariosNew = publicacion.getComentarios();
            List<PalabrasAltisonantes> palabrasAltisonantesOld = persistentPublicacion.getPalabrasAltisonantes();
            List<PalabrasAltisonantes> palabrasAltisonantesNew = publicacion.getPalabrasAltisonantes();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                publicacion.setUsuario(usuarioNew);
            }
            if (categoriaNew != null) {
                categoriaNew = em.getReference(categoriaNew.getClass(), categoriaNew.getId());
                publicacion.setCategoria(categoriaNew);
            }
            List<Comentario> attachedComentariosNew = new ArrayList<Comentario>();
            for (Comentario comentariosNewComentarioToAttach : comentariosNew) {
                comentariosNewComentarioToAttach = em.getReference(comentariosNewComentarioToAttach.getClass(), comentariosNewComentarioToAttach.getId());
                attachedComentariosNew.add(comentariosNewComentarioToAttach);
            }
            comentariosNew = attachedComentariosNew;
            publicacion.setComentarios(comentariosNew);
            List<PalabrasAltisonantes> attachedPalabrasAltisonantesNew = new ArrayList<PalabrasAltisonantes>();
            for (PalabrasAltisonantes palabrasAltisonantesNewPalabrasAltisonantesToAttach : palabrasAltisonantesNew) {
                palabrasAltisonantesNewPalabrasAltisonantesToAttach = em.getReference(palabrasAltisonantesNewPalabrasAltisonantesToAttach.getClass(), palabrasAltisonantesNewPalabrasAltisonantesToAttach.getId());
                attachedPalabrasAltisonantesNew.add(palabrasAltisonantesNewPalabrasAltisonantesToAttach);
            }
            palabrasAltisonantesNew = attachedPalabrasAltisonantesNew;
            publicacion.setPalabrasAltisonantes(palabrasAltisonantesNew);
            publicacion = em.merge(publicacion);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getPublicaciones().remove(publicacion);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getPublicaciones().add(publicacion);
                usuarioNew = em.merge(usuarioNew);
            }
            if (categoriaOld != null && !categoriaOld.equals(categoriaNew)) {
                categoriaOld.getPublicaciones().remove(publicacion);
                categoriaOld = em.merge(categoriaOld);
            }
            if (categoriaNew != null && !categoriaNew.equals(categoriaOld)) {
                categoriaNew.getPublicaciones().add(publicacion);
                categoriaNew = em.merge(categoriaNew);
            }
            for (Comentario comentariosOldComentario : comentariosOld) {
                if (!comentariosNew.contains(comentariosOldComentario)) {
                    comentariosOldComentario.setPublicacion(null);
                    comentariosOldComentario = em.merge(comentariosOldComentario);
                }
            }
            for (Comentario comentariosNewComentario : comentariosNew) {
                if (!comentariosOld.contains(comentariosNewComentario)) {
                    Publicacion oldPublicacionOfComentariosNewComentario = comentariosNewComentario.getPublicacion();
                    comentariosNewComentario.setPublicacion(publicacion);
                    comentariosNewComentario = em.merge(comentariosNewComentario);
                    if (oldPublicacionOfComentariosNewComentario != null && !oldPublicacionOfComentariosNewComentario.equals(publicacion)) {
                        oldPublicacionOfComentariosNewComentario.getComentarios().remove(comentariosNewComentario);
                        oldPublicacionOfComentariosNewComentario = em.merge(oldPublicacionOfComentariosNewComentario);
                    }
                }
            }
            for (PalabrasAltisonantes palabrasAltisonantesOldPalabrasAltisonantes : palabrasAltisonantesOld) {
                if (!palabrasAltisonantesNew.contains(palabrasAltisonantesOldPalabrasAltisonantes)) {
                    palabrasAltisonantesOldPalabrasAltisonantes.getPublicaciones().remove(publicacion);
                    palabrasAltisonantesOldPalabrasAltisonantes = em.merge(palabrasAltisonantesOldPalabrasAltisonantes);
                }
            }
            for (PalabrasAltisonantes palabrasAltisonantesNewPalabrasAltisonantes : palabrasAltisonantesNew) {
                if (!palabrasAltisonantesOld.contains(palabrasAltisonantesNewPalabrasAltisonantes)) {
                    palabrasAltisonantesNewPalabrasAltisonantes.getPublicaciones().add(publicacion);
                    palabrasAltisonantesNewPalabrasAltisonantes = em.merge(palabrasAltisonantesNewPalabrasAltisonantes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = publicacion.getId();
                if (findPublicacion(id) == null) {
                    throw new NonexistentEntityException("The publicacion with id " + id + " no longer exists.");
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
            Publicacion publicacion;
            try {
                publicacion = em.getReference(Publicacion.class, id);
                publicacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The publicacion with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = publicacion.getUsuario();
            if (usuario != null) {
                usuario.getPublicaciones().remove(publicacion);
                usuario = em.merge(usuario);
            }
            Categoria categoria = publicacion.getCategoria();
            if (categoria != null) {
                categoria.getPublicaciones().remove(publicacion);
                categoria = em.merge(categoria);
            }
            List<Comentario> comentarios = publicacion.getComentarios();
            for (Comentario comentariosComentario : comentarios) {
                comentariosComentario.setPublicacion(null);
                comentariosComentario = em.merge(comentariosComentario);
            }
            List<PalabrasAltisonantes> palabrasAltisonantes = publicacion.getPalabrasAltisonantes();
            for (PalabrasAltisonantes palabrasAltisonantesPalabrasAltisonantes : palabrasAltisonantes) {
                palabrasAltisonantesPalabrasAltisonantes.getPublicaciones().remove(publicacion);
                palabrasAltisonantesPalabrasAltisonantes = em.merge(palabrasAltisonantesPalabrasAltisonantes);
            }
            em.remove(publicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Publicacion> findPublicacionEntities() {
        return findPublicacionEntities(true, -1, -1);
    }

    public List<Publicacion> findPublicacionEntities(int maxResults, int firstResult) {
        return findPublicacionEntities(false, maxResults, firstResult);
    }

    private List<Publicacion> findPublicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Publicacion.class));
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

    public Publicacion findPublicacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Publicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPublicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Publicacion> rt = cq.from(Publicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
