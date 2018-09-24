/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author polaco
 */

public class PartidaImputable extends Partida {
private Map movimientos = new TreeMap();
    public PartidaImputable()
    {
    }

    public PartidaImputable(String id, String descripcion, double importe, String nombre, PartidaDescriptiva Padre) {
    super(id,descripcion, 0,0, nombre, Padre);
    this.actualizarImporte(importe);
    Empresa.unaPersistencia.insert(this);
    }

    public void regitrarMovimiento(String id, Date fecha, String descripcion, Double importe)
    {
        if (movimientos.containsKey(id)== false)
        {
            movimientos.put(id, new Movimiento(id, fecha, descripcion, importe));
            this.actualizarImporteEjecutado(importe);
            Empresa.unaPersistencia.update(this);
        }
    }
    
    public Map getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Map movimientos) {
        this.movimientos = movimientos;
    }
}