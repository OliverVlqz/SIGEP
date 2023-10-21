package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Publicacion;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-20T20:36:45")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile ListAttribute<Categoria, Publicacion> publicaciones;
    public static volatile SingularAttribute<Categoria, String> titulo;
    public static volatile SingularAttribute<Categoria, Long> id;

}