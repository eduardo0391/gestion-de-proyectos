/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.util.Date;
/**
 *
 * @author polaco
 */

public class Movimiento
{
private String id;
private Date fecha;
private String descripcion;
private Double importe;

    public Double getImporte() {
        return importe;
    }
    public void setImporte(Double importe) {
        this.importe = importe;
    }
    public Movimiento() {
    }

    public Movimiento(String id, Date fecha, String descripcion, Double importe) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.importe= importe;
        Empresa.unaPersistencia.insert(this);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}