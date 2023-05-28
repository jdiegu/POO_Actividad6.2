package flujosarchivosproyecto;

import jopi.JOPI;

public class Menu {
    
    Archivo crud = new Archivo();
    
    public void menuElimina(){
        String opciones[] = {"Fisico", "Logico" , "Limpia" , "Regresar"};
        int op;
        do{
            op = salida.Menu.menuBotones(opciones, "ELIMINA");
        
            switch(op){
                case 0:
                    int a = JOPI.entero("Num. de registro a eliminar?");
                    crud.eliminarDatosFisico(a);
                    break;
                case 1:
                    crud.eliminarDatosLogico();
                    break;
                case 2:
                    crud.eliminaPerma();
                    break;
            }
        }while(op != 3);
    }
    
    public void menuRecupera(){
        String opciones[] = {"Registro", "Todo" , "Regresar"};
        int op;
        do{
            op = salida.Menu.menuBotones(opciones, "RECUPERAR");
        
            switch(op){
                case 0:
                    int b  = JOPI.entero("Ingresa el numero de registro que desea recuperar");
                    crud.recuperarRegistro(b);
                    break;
                case 1:
                    crud.recuperaTodo();
                    break;
            }
        }while(op != 2);
    }
    
     public void menuMuestra(){
        String opciones[] = {"Registro", "Todo" , "Regresar"};
        int op;
        do{
            op = salida.Menu.menuBotones(opciones, "MOSTRAR");
        
            switch(op){
                case 0:
                    crud.consultaDatos();
                    break;
                case 1:
                    crud.mostrarTodo();
                    break;
            }
        }while(op != 2);
    }
    
    public void menu() {
        
        crud.creaArchivo();
        
        String opciones[] = {"Agregar Datos" , "Consultar" , "Modifica" , "Elimina", "Recupera" ,"Terminar"};
        int opcion;
        do {
            opcion = salida.Menu.menuBotones(opciones, "Menu principal");

            switch (opcion) {
                case 0:
                    crud.agregaDatos();
                    break;
                case 1:
                    this.menuMuestra();
                    break;
                case 2:
                    crud.modificaRegistro();
                    break;
                case 3:
                    this.menuElimina();
                    break;
                case 4:
                    this.menuRecupera();
                    break;
            }

        } while (opcion != 5);
    }

}
