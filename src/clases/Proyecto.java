/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author polaco
 */

public class Proyecto
{
private String id;
private String empresa;
private String nombre;
private Map partidas;
private PartidaDescriptiva unaPartidaDescriptiva;
private Partida unaPartida;
private Boolean formulacion;

public Proyecto()
{}
    public Proyecto(String id, String empresa, String nombre) {
        this.id = id;
        this.empresa = empresa;
        this.nombre = nombre;
        this.formulacion = false;
        partidas= new HashMap();
        Empresa.unaPersistencia.insert(this);
    }

    public Partida buscarPartida(String id) throws Exception
    {
    unaPartida = null;
    if (partidas.containsKey(id))
    unaPartida= (Partida)partidas.get(id);
    return unaPartida;
    }



    public void registrarMovimiento(String id, Date fecha, String descripcion,Double importe, PartidaImputable unaPartida)
    {
     unaPartida.regitrarMovimiento(id, fecha, descripcion, importe);
    }
    public PartidaDescriptiva buscarPartidaPadre(String id)throws Exception
    {
      PartidaDescriptiva unPadre=null;

      if (partidas.containsKey(id))
      {
          unPadre = (PartidaDescriptiva)partidas.get(id);
      }
      return unPadre;
    }


    public void agregarPartidaDescriptiva(String id, String descripcion, String nombre, String idpadre) throws Exception
    {
     if (partidas.containsKey(id)==false)
     {
         unaPartidaDescriptiva = this.buscarPartidaPadre(idpadre);
         partidas.put(id, new PartidaDescriptiva(id, descripcion, nombre, unaPartidaDescriptiva));}
     else
        {
         JOptionPane.showMessageDialog(null, "Ya existe una partida con el ID ingresado");
        }
    }

    public void agregarPartidaImputable(String id, String descripcion, double importe, String nombre, String idpadre) throws Exception
    {
     if (partidas.containsKey(id)==false)
     {
        unaPartidaDescriptiva = this.buscarPartidaPadre(idpadre);
        partidas.put(id,new PartidaImputable(id, descripcion, importe, nombre, unaPartidaDescriptiva));}
     else 
     {
             JOptionPane.showMessageDialog(null,"Ya existe una partida con el ID ingresado");
     }
    }

    public void modificarPartida(String id, String descripcion, double importe, String nombre) throws Exception
    {
       unaPartida = this.buscarPartida(id);
       if (unaPartida != null)
       {
           unaPartida.modificarPartida(descripcion,importe, nombre);
       }
    }


    public void eliminarPartida(String id) throws Exception
    {  unaPartida = this.buscarPartida(id);
       if (unaPartida.getPadre()!=null)
       unaPartida.getPadre().actualizarPadres(unaPartida);
       this.eliminarHijos(unaPartida);
       Empresa.unaPersistencia.update(this);
    }
    
    public void eliminarHijos(Partida unaPartida)
    {
       if (unaPartida.esDescriptiva()){
          if (((PartidaDescriptiva)unaPartida).getHijos() != null)
          {
              Object[] x= ((PartidaDescriptiva)unaPartida).getHijos().values().toArray();
              for (int i= 0; i < x.length; i++)
                  eliminarHijos((Partida)x[i]);
          }}
           partidas.remove(unaPartida.getId());
           Empresa.unaPersistencia.delete(unaPartida);
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map getPartidas() {
        return partidas;
    }

    public void setPartidas(Map partidas) {
        this.partidas = partidas;
    }

    public Boolean getFormulacion() {
        return formulacion;
    }

    public void setFormulacion(Boolean formulacion) {
        this.formulacion = formulacion;
    }

}
