/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String contrasenia;
    private String correo;
    private String genero;
    private String tipoUsuario; // puedes usar ENUM
    private int numSanciones;

    @OneToMany(mappedBy = "usuario")
    private List<Publicacion> publicaciones;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;
    
    // Getters, setters, etc...

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String contrasenia, String correo, String genero, String tipoUsuario, int numSanciones, List<Publicacion> publicaciones, List<Comentario> comentarios) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.genero = genero;
        this.tipoUsuario = tipoUsuario;
        this.numSanciones = numSanciones;
        this.publicaciones = publicaciones;
        this.comentarios = comentarios;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getNumSanciones() {
        return numSanciones;
    }

    public void setNumSanciones(int numSanciones) {
        this.numSanciones = numSanciones;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    
    
    
    
}




