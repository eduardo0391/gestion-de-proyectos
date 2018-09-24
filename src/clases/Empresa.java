/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import persistencia.Persistencia;
/**
 *
 * @author polaco
 */
public class Empresa {
private String id;
private String nombre;
private Map proyectos;
private Map usuarios;
Usuario unUsuario;
Proyecto unProyecto;
public static Persistencia unaPersistencia = new Persistencia();
public Empresa()
{
}

public Empresa(String id, String nombre)
{
    this.nombre= nombre;
    this.id = id;
    proyectos = new HashMap();
    usuarios = new HashMap();
    unaPersistencia.insert(this);
    // persistencia.insert(this);
}
    public String getId() {
        return id;
    }

    public Map getProyectos() {
        return proyectos;
    }

    public void setProyectos(Map proyectos) {
        this.proyectos = proyectos;
    }

    public Map getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map usuarios) {
        this.usuarios = usuarios;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarUsuario(String usuario, String contraseña, Boolean valor)
    {
        if(usuarios.containsKey(usuario)==false)
         this.usuarios.put(usuario, new Usuario(usuario, contraseña, valor));
        else
          JOptionPane.showMessageDialog(null, "Ya existe el usuario registrado con ese nombre");
        unaPersistencia.update(this);
    }   
    
    public void eliminarUsuario(String usuario)
    {
        this.unUsuario= buscarUsuario(usuario);
        if (unUsuario!=null)
            usuarios.remove(usuario);
            unaPersistencia.delete(unUsuario);
            unaPersistencia.update(this);
    }

    public void modificarUsuario(String usuario, String contraseña, boolean ejecucion)
    {
        this.unUsuario= buscarUsuario(usuario);
        if (unUsuario!=null)
        unUsuario.setContraseña(contraseña);
        unUsuario.setEjecucion(ejecucion);
        unaPersistencia.update(unUsuario);
        unaPersistencia.update(this);
    }

    public Boolean comprobarAcceso(String usuario, String contraseña)
    {
        Boolean valor=false;
        this.unUsuario=buscarUsuario(usuario);
        if (unUsuario!=null)
        valor= unUsuario.comprobarContraseña(contraseña);
        return valor;
    }

    public Usuario buscarUsuario(String usuario)
    {
        this.unUsuario= null;
        unUsuario= (Usuario)usuarios.get(usuario);
        return unUsuario;
    }
   public void agregarMovimiento(String id, Date fecha, String descripcion, Double importe, Proyecto unProyecto, PartidaImputable unaPartida)
   {
      if (unProyecto.getFormulacion()==true)
       unProyecto.registrarMovimiento(id, fecha, descripcion, importe, unaPartida);
      else
       JOptionPane.showMessageDialog(null, "Este proyecto aún no ha sido terminado de formular");
   }
    
    public void agregarPartidaDescriptiva(String id, String proyecto, String descripcion,String nombre, String idpadre) throws Exception
    {
        unProyecto= this.buscarProyecto(proyecto);
        if (unProyecto!=null)
        {
            if (unProyecto.getFormulacion()==false)
            unProyecto.agregarPartidaDescriptiva(id, descripcion, nombre, idpadre);
            else
             JOptionPane.showMessageDialog(null, "Este proyecto a terminado su formulacion");
        }
    }

    public void modificarPartida(String id, String proyecto, String descripcion, double importe, String nombre) throws Exception
    {
        unProyecto= (Proyecto)this.proyectos.get(proyecto);
        if (unProyecto != null)
            unProyecto.modificarPartida(id, descripcion, importe, nombre);
        //this.unaPersistencia.update(this);
    }

    public void eliminar(String id) throws Exception
    {
       unProyecto.eliminarPartida(id);
    }
    
    public void agregarPartidaImputable(String id, String proyecto, String descripcion, double importe, String nombre, String idpadre) throws Exception
    {
        unProyecto= this.buscarProyecto(proyecto);
        if (unProyecto !=null)
        {
            if (unProyecto.getFormulacion()==false)
            unProyecto.agregarPartidaImputable(id, descripcion, importe, nombre, idpadre);
            else
            {JOptionPane.showMessageDialog(null, "Este proyecto a finalizado su formulacion");}
        }
    }

    public void agregarProyecto(String id, String empresa, String nombre)
    {
     unProyecto= this.buscarProyecto(id);
     if (unProyecto==null)
     {
            proyectos.put(id, new Proyecto(id, empresa, nombre));}
            unaPersistencia.update(this);
    }

    public void terminarFormulacion(String idproyecto)
    {
       Proyecto proyecto = this.buscarProyecto(idproyecto);
       if (proyecto!=null)
       proyecto.setFormulacion(true);
    }

   public Proyecto buscarProyecto(String id)
    {
        unProyecto= null;
        unProyecto= (Proyecto)proyectos.get(id);
        return unProyecto;
    }
}