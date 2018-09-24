/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pantalla;
import clases.Empresa;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import persistencia.Persistencia;

/**
 *
 * @author polaco
 */
public class Principal {       
    public static Persistencia unaPersistencia = new Persistencia();
    public static void main(String[] args)
    {
       String id = "1";
       String nombre= "PROGRAMACION ORIENTADO A OBJETOS XD";
       Empresa unaEmpresa=null;
       unaEmpresa = new Empresa(id.toString(),nombre.toString());

       unaEmpresa.agregarProyecto("1", nombre, "Proyecto1");
       try
       {
            unaEmpresa.agregarPartidaDescriptiva("1", "1", "primero", "Diseño", "0");
            unaEmpresa.agregarPartidaDescriptiva("8", "1", "octavo", "Documentacion", "0");
            unaEmpresa.agregarPartidaDescriptiva("2", "1", "segundo", "Diseño BD", "1");
            unaEmpresa.agregarPartidaImputable("3", "1", "tercero", Double.parseDouble("500"), "Diseño MLogico", "2");
            unaEmpresa.agregarPartidaImputable("4", "1", "cuarto", Double.parseDouble("300"), "Diseño Fisico", "2");
            unaEmpresa.agregarPartidaDescriptiva("5", "1", "quintus", "Implementacion", "1");
            unaEmpresa.agregarPartidaImputable("6", "1", "sexto", Double.parseDouble("400"), "Java", "5");
            unaEmpresa.agregarPartidaImputable("7", "1", "septimo", Double.parseDouble("50"), "Visual Basic", "5"); 
            JFrame miventana = new Opciones(unaEmpresa);
            miventana.setVisible(true);
    }
    catch (Exception ex)
    {
        JOptionPane.showMessageDialog(null, ex.toString());
    }
    }
   }