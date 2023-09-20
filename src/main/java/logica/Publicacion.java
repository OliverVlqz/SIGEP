/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Publicacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String contenido;
    private Date fecha;
    private String tipoPublicacion; 
    private boolean statusVerificacion;
    private int reaccion;
    @Lob
    private byte[] imagenData;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "publicacion")
    private List<Comentario> comentarios;

    @ManyToMany
    @JoinTable(
        name = "publicacion_palabra",
        joinColumns = @JoinColumn(name = "publicacion_id"),
        inverseJoinColumns = @JoinColumn(name = "palabra_id")
    )
    private List<PalabrasAltisonantes> palabrasAltisonantes;

    // Getters, setters, etc...

    public Publicacion() {
    }

    public Publicacion(Long id, String titulo, String contenido, Date fecha, String tipoPublicacion, boolean statusVerificacion, int reaccion, byte[] imagenData, Usuario usuario, Categoria categoria, List<Comentario> comentarios, List<PalabrasAltisonantes> palabrasAltisonantes) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.tipoPublicacion = tipoPublicacion;
        this.statusVerificacion = statusVerificacion;
        this.reaccion = reaccion;
        this.imagenData = imagenData;
        this.usuario = usuario;
        this.categoria = categoria;
        this.comentarios = comentarios;
        this.palabrasAltisonantes = palabrasAltisonantes;
    }

    public byte[] getImagenData() {
        return imagenData;
    }

    public void setImagenData(byte[] imagenData) {
        this.imagenData = imagenData;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(String tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public boolean isStatusVerificacion() {
        return statusVerificacion;
    }

    public void setStatusVerificacion(boolean statusVerificacion) {
        this.statusVerificacion = statusVerificacion;
    }

    public int getReaccion() {
        return reaccion;
    }

    public void setReaccion(int reaccion) {
        this.reaccion = reaccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<PalabrasAltisonantes> getPalabrasAltisonantes() {
        return palabrasAltisonantes;
    }

    public void setPalabrasAltisonantes(List<PalabrasAltisonantes> palabrasAltisonantes) {
        this.palabrasAltisonantes = palabrasAltisonantes;
    }
    
    
    
    
}

