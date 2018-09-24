/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author polaco
 */
public class PartidaDescriptiva extends Partida {
Map hijos;

    public PartidaDescriptiva(String id, String descripcion, String nombre, PartidaDescriptiva padre) {
    super(id,descripcion, 0,0, nombre, padre);
    hijos = new HashMap();
    Empresa.unaPersistencia.insert(this);
    }

    public PartidaDescriptiva() {
    }

    public Map getHijos() {
        return hijos;
    }


    public void agregarPartida(String id, String descripcion, double importe, String nombre){
        hijos.put(id, new PartidaImputable (id, descripcion, importe, nombre, this));
    }

    public void agregarPartida(String id, String descripcion, String nombre)
    {
        hijos.put(id, new PartidaDescriptiva(id, descripcion, nombre, this));
    }

    public void actualizarPadres(Partida unaPartida)
    {
        this.hijos.remove(unaPartida.getId());
        this.actualizarImporte(-unaPartida.getImporte());
    }

    public void setHijos(Map hijos) {
        this.hijos = hijos;
    }
   
    @Override
    public boolean esDescriptiva()
    {
        return true;
    }
}
