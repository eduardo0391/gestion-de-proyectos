/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

/**
 *
 * @author polaco
 */
public class Usuario
{
String nombre;
String contraseña;
Boolean ejecucion;

    public Usuario() {
    }

    public Usuario(String nombre, String contraseña, Boolean ejecucion) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.ejecucion = ejecucion;
        Empresa.unaPersistencia.insert(this);
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEjecucion(Boolean ejecucion)
    {
        this.ejecucion = ejecucion; //si el valor==true entonces el usuario va a ser de formulacion,
    }

    public Boolean getEjecucion()
    {
        return this.ejecucion;
    }

    public Boolean comprobarContraseña(String contraseña)
    {
        Boolean valor = false;
        if (this.contraseña.equals(contraseña.toString()))
          valor=true;
        return valor;
    }
}