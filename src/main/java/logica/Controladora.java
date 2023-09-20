/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import persistencia.ControladoraPersistencia;
import java.io.InputStream;
import java.util.Date;
/**
 *
 * @author olive
 */
public class Controladora {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    public void crearUsuario(String nombre, String contrasenia, String correo, String genero, String tipoUsuario, String numSanciones) {
        Usuario usu = new Usuario();
        
        usu.setNombre(nombre);
        usu.setContrasenia(contrasenia); // Considera encriptar la contraseña antes de guardarla
        usu.setCorreo(correo);
        usu.setGenero(genero);
        usu.setTipoUsuario(tipoUsuario);
        usu.setNumSanciones(Integer.parseInt(numSanciones));
        controlPersis.crearUsuario(usu);
        
    }

    public List<Usuario> getUsuarios() {
        return controlPersis.getUsuarios();
    }
     public Usuario findUsuarioById(Long id) {
        
        return controlPersis.findUsuarioById(id);
    }

   //Login
    public Usuario checkLogin(String nombre, String contrasenia) {
    Usuario usuario = controlPersis.getUsuarioPorNombre(nombre);
    
    if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
        // Considera encriptar la contraseña y compararla con la versión encriptada
        return usuario;
    }
    return null;
}

    
    
    
    
   

    public List<Publicacion> recuperarTodasLasPublicaciones() {
        ControladoraPersistencia controlPersistencia = new ControladoraPersistencia();
        return controlPersistencia.obtenerTodasLasPublicaciones();
    }

  public List<Publicacion> obtenerPublicaciones() {
    return controlPersis.getPublicaciones();
}

 
    public void crearPublicacion(String titulo, String contenido, Date fecha, String tipoPublicacion, int reaccion, boolean statusVerificacion, InputStream imagenInput, Usuario usuarioLogueado, Categoria categoriaSeleccionada) throws IOException {
    // ... (código existente)

    Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(titulo);
        publicacion.setContenido(contenido);
        publicacion.setFecha(fecha);
        publicacion.setTipoPublicacion(tipoPublicacion);
        publicacion.setStatusVerificacion(statusVerificacion);
        publicacion.setReaccion(reaccion);
        publicacion.setCategoria(categoriaSeleccionada);
         publicacion.setUsuario(usuarioLogueado );
        
        if (imagenInput != null) {
            byte[] imagenData = new byte[(int) imagenInput.available()];
            imagenInput.read(imagenData);
            publicacion.setImagenData(imagenData);
        }

        
        controlPersis.guardarPublicacion(publicacion);

    // ... (resto del código)
}


    // Categoria
    
    public void crearCategoria(String nombreCategoria) {
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setTitulo(nombreCategoria);
        controlPersis.crearCategoria(nuevaCategoria);
    }

    public List<Categoria> obtenerCategorias() {
    return controlPersis.obtenerCategorias();
}

    public Categoria obtenerCategoriaPorId(Long id) {
        
    return controlPersis.obtenerCateriaPorId(id);
}

    public void crearComentario(String contenido, Usuario usuarioLogueado, Publicacion publicacion) {
    Comentario comentario = new Comentario();
    comentario.setContenido(contenido);
    comentario.setFecha(new Date()); // Establecer la fecha actual
    comentario.setUsuario(usuarioLogueado); // Establecer el usuario del comentario
    comentario.setPublicacion(publicacion); // Asociar el comentario con la publicación
    controlPersis.guardarComentario(comentario);
}
    
    public List<Comentario> obtenerComentarios() {
    return controlPersis.listarComentarios();
    }

   public void responderComentario(Long idComentarioOriginal, String contenidoRespuesta, Usuario usuarioLogueado) {
    Comentario comentarioOriginal = controlPersis.findComentario(idComentarioOriginal);
    Comentario respuesta = new Comentario();
    respuesta.setUsuario(usuarioLogueado);
    
    respuesta.setContenido(contenidoRespuesta);
    respuesta.setFecha(new Date());
    respuesta.setComentarioPadre(comentarioOriginal);
    
    controlPersis.guardarComentario(respuesta);
}

    public Publicacion buscarPublicacionPorId(Long publicacionId) {

        return controlPersis.buscarPublicacionPorId(publicacionId);
    }

    
    
 
}


        



    

