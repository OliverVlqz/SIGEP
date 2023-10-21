package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Comentario;
import logica.Publicacion;
import logica.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-20T20:36:45")
@StaticMetamodel(Comentario.class)
public class Comentario_ { 

    public static volatile SingularAttribute<Comentario, Date> fecha;
    public static volatile SingularAttribute<Comentario, String> contenido;
    public static volatile ListAttribute<Comentario, Comentario> respuestas;
    public static volatile SingularAttribute<Comentario, Comentario> comentarioPadre;
    public static volatile SingularAttribute<Comentario, Usuario> usuario;
    public static volatile SingularAttribute<Comentario, Long> id;
    public static volatile SingularAttribute<Comentario, Publicacion> publicacion;

}