package flujosarchivosproyecto;

import jopi.JOPI;

public class Alumno {

    protected String nControl, nombre, semestre, promedio;

    public Alumno() {
        
        this.nControl = trunca(JOPI.cadena("No Control? ") , 10); // 8
        this.nombre = trunca(JOPI.cadena("Nombre? "), 20); // 20
        this.semestre = trunca(JOPI.cadena("Semestre? "), 3); // 2
        this.promedio = trunca(JOPI.cadena("Promedio? "), 3); // 3
    }
   
    public String getDatos() {
        String msg =  this.nControl + "\n";
        msg +=  this.nombre + "\n";
        msg +=  this.semestre + "\n";
        msg +=  this.promedio+"\n";

        return msg;
    }

    public String trunca(String cad , int f) {
        String msg = "";
        
        msg = cad+ "                                           "; 
        return msg.substring(0, f);
    }

}
