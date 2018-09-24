/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

/**
 *
 * @author polaco
 */
public class Partida {
private String id;
private String descripcion;
private String nombre;
private Double importe;
private PartidaDescriptiva padre;
private Double importeEjecutado;

//private Integer importeactualizado;

    public Partida()
    {
    }

    public Partida(String id, String descripcion, double importe, double importeEjecutado, String nombre, PartidaDescriptiva padre) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.importe= importe;
        this.importeEjecutado = importeEjecutado;
        this.padre= padre;
        if (padre != null){
        this.padre.hijos.put(id, this);
    }
    }

    public Double getImporte(){
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getImporteAcumulado() {
        return importeEjecutado;
    }

    public void setImporteAcumulado(Double importeAcumulado) {
        this.importeEjecutado = importeAcumulado;
    }

    public void modificarPartida(String descripcion, double importe, String nombre)
    {
       this.descripcion = descripcion;
       this.nombre = nombre;       
           if (this.esDescriptiva()== false)
           this.actualizarImporte(importe - this.importe);
       //this.modificarImporte(this.importe, importe);}
       Empresa.unaPersistencia.update(this);

    }


    public void actualizarImporte(double importe)
    {
        this.importe+= importe;
        if (this.padre!= null)
        {
            this.padre.actualizarImporte(importe);
        }
    }

    public void actualizarImporteEjecutado(double importe)
    {
        this.importeEjecutado += importe;
        if (this.padre!= null)
        {
            this.padre.actualizarImporteEjecutado(importe);
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
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

    public PartidaDescriptiva getPadre() {
        return padre;
    }

    public Double getImporteEjecutado() {
        return importeEjecutado;
    }

    public void setImporteEjecutado(Double importeEjecutado) {
        this.importeEjecutado = importeEjecutado;
    }

    public void setPadre(PartidaDescriptiva padre) {
        this.padre = padre;
    }
    public boolean esDescriptiva()
    {return false;
    }
    public String toString()
    {
    return this.generarId() + " " + this.nombre + " " + this.importe.toString() ;
    }

    public String generarId(){
        String retorno = null;
        if(this.padre != null){
           retorno = this.padre.generarId();
           retorno += "." + this.id ;
        }else{
            retorno = this.id ;
        }
        return retorno;
    }
}
