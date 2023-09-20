package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Categoria;
import logica.Comentario;
import logica.PalabrasAltisonantes;
import logica.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-12T12:10:25")
@StaticMetamodel(Publicacion.class)
public class Publicacion_ { 

    public static volatile SingularAttribute<Publicacion, Date> fecha;
    public static volatile SingularAttribute<Publicacion, String> contenido;
    public static volatile SingularAttribute<Publicacion, String> tipoPublicacion;
    public static volatile SingularAttribute<Publicacion, byte[]> imagenData;
    public static volatile SingularAttribute<Publicacion, Categoria> categoria;
    public static volatile SingularAttribute<Publicacion, String> titulo;
    public static volatile SingularAttribute<Publicacion, Integer> reaccion;
    public static volatile SingularAttribute<Publicacion, Boolean> statusVerificacion;
    public static volatile SingularAttribute<Publicacion, Usuario> usuario;
    public static volatile SingularAttribute<Publicacion, Long> id;
    public static volatile ListAttribute<Publicacion, PalabrasAltisonantes> palabrasAltisonantes;
    public static volatile ListAttribute<Publicacion, Comentario> comentarios;

}