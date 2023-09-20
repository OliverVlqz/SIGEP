/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import logica.Categoria;
import logica.Comentario;
import logica.Publicacion;
import logica.Usuario;

/**
 *
 * @author olive
 */
public class ControladoraPersistencia {

    PublicacionJpaController pubJPA = new PublicacionJpaController();
    UsuarioJpaController usuJPA = new UsuarioJpaController();
    CategoriaJpaController catJPA = new CategoriaJpaController();
    ComentarioJpaController comJPA = new ComentarioJpaController();

    public void crearUsuario(Usuario usu) {
        usuJPA.create(usu);
    }

    public List<Usuario> getUsuarios() {
        return usuJPA.findUsuarioEntities();
    }

    //Login
    public Usuario getUsuarioPorNombre(String nombre) {

        List<Usuario> usuarios = usuJPA.findUsuarioEntities();
        for (Usuario usu : usuarios) {
            if (usu.getNombre().equals(nombre)) {
                return usu;
            }
        }
        return null;
    }

    public void guardarPublicacion(Publicacion publicacion) {

        pubJPA.create(publicacion);
    }

    // ... Otros métodos existentes ...
    public List<Publicacion> obtenerTodasLasPublicaciones() {

        return pubJPA.findPublicacionEntities();
    }

    public List<Publicacion> getPublicaciones() {
        return pubJPA.findPublicacionEntities();  // Asumiendo que tienes un método findAll() en tu JPA para obtener todas las publicaciones
    }
// Categoria

    public void crearCategoria(Categoria nuevaCategoria) {
        catJPA.create(nuevaCategoria);
    }

    public List<Categoria> obtenerCategorias() {
        return catJPA.findCategoriaEntities();
    }

    public Categoria obtenerCateriaPorId(Long id) {
        return catJPA.findCategoria(id);
    }

    //Comentario
    public void guardarComentario(Comentario nuevoComentario) {
        comJPA.create(nuevoComentario);
    }

    public List<Comentario> listarComentarios() {
        // Aquí debes usar tu JPA controller para obtener todos los comentarios
        return comJPA.findComentarioEntities();
    }

    public Comentario findComentario(Long id) {

        return comJPA.findComentario(id);
    }

    public Publicacion buscarPublicacionPorId(Long publicacionId) {
        return pubJPA.findPublicacion(publicacionId);
    }

    public Usuario findUsuarioById(Long id) {
        return usuJPA.findUsuario(id);
    }
   

}
