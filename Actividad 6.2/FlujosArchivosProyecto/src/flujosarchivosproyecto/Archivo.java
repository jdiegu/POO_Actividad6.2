package flujosarchivosproyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import jopi.JOPI;
import salida.JOPIS;

public class Archivo {
    
    protected String nombreArchivo;
    
    public void creaArchivo() {
        nombreArchivo = JOPI.cadena("Ingresa el nombre del archivo");
        File f = new File(nombreArchivo + ".dat");
        f.delete();
    }

    public void agregaDatos() {
        RandomAccessFile archivo = null;

        try {
            File f = new File(nombreArchivo + ".dat");
            archivo = new RandomAccessFile(f, "rw");
            archivo.seek(archivo.length());

            Archivo arch = new Archivo();
            
            String alum = new Alumno().getDatos();
            archivo.writeBytes("0"+"\n"+alum);
            archivo.writeBytes("\n");
            archivo.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: el archivo no se pudo crear");
        } catch (IOException e) {
            System.err.println("Error: no se pudo cerrar el archivo");
        }

    }
    
    public void eliminarDatosFisico(int nRegistro) {
        
        try {
            File f = new File(nombreArchivo + ".dat");
            File f2 = new File("copia_"+nombreArchivo+".dat");
            f2.delete();
            RandomAccessFile archivo2 = new RandomAccessFile(f2, "rw");
            archivo2.seek(0);
            int i = 1; //contador de espacios
            String guardado = "";
            RandomAccessFile archivo = new RandomAccessFile(f, "rw");
            archivo.seek(0);

            while ((guardado = archivo.readLine()) != null) {

                if (i != nRegistro) {
                    archivo2.writeBytes(guardado + "\n");
                }
               
                if (guardado.isBlank()) {
                    i++;
                }
            }
            // Cerrar los archivos
            archivo.close();
            archivo2.close();
            
            Thread.sleep(1000);
            
            if (f.delete()) {
                f2.renameTo(f);
            } else {
                System.err.println("Error: aaaaa no se pudo eliminar el archivo original");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error el archivo no se pudo crear");
        } catch (IOException e) {
            System.err.println("Error no se pudo cerrar el archivo");
        } catch (InterruptedException ex) {
            System.err.println("Se murio");
        }

        return;
    }
    
    public void eliminaPerma(){
        
        try {
            File f = new File(nombreArchivo + ".dat");
            int i = 1; //contador de espacios
            int p = 1;
            String guardado = "";
            RandomAccessFile archivo = new RandomAccessFile(f, "rw");
            archivo.seek(0);
            int arr[] = new int[100001];    
            int cont=0;
            while ((guardado = archivo.readLine()) != null) {
                
                if(guardado.equals("1"))
                    arr[cont++] = i;

                if (guardado.isBlank()) 
                    i++;
            }
            
            archivo.close();
            for(int c=cont-1; c>=0; c--){
                Thread.sleep(1000);
                System.out.println(arr[c]);
                this.eliminarDatosFisico(arr[c]);
            }
            // Cerrar los archivos
            
        } catch (FileNotFoundException e) {
            System.err.println("Error el archivo no se pudo crear");
        } catch (IOException e) {
            System.err.println("Error no se pudo cerrar el archivo");
        } catch (InterruptedException ex) {
            System.out.println("Se murio");
        }
    }
    
    public void eliminarDatosLogico() {
        //SOLICITAR EL NUMERO DE REGISTRO A ELIMINAR
        int nRegistro = JOPI.entero("Ingresa el numero de registro que desea eliminar");

        //ABRIR EL ARCHIVO ORIGINAL Y COLOCARSE AL INICIO
        try {
            File f = new File(nombreArchivo + ".dat");
            int i = 1; //contador de espacios
            int p = 1;
            String guardado = "";
            RandomAccessFile archivo = new RandomAccessFile(f, "rw");
            archivo.seek(0);

            if (nRegistro == 1) {
                archivo.writeBytes("1");
                i++;
            }
            while ((guardado = archivo.readLine()) != null) {

                if (guardado.isBlank()) {
                    i++;
                }

                while ((i == nRegistro && i != 1) && p == 1) {
                    archivo.writeBytes("1");
                    archivo.readLine();
                    guardado = archivo.readLine();
                    p++;
                }

            }
            // Cerrar los archivos
            archivo.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error el archivo no se pudo crear");
        } catch (IOException e) {
            System.err.println("Error no se pudo cerrar el archivo");
        }

        return;
    }

    public void recuperarRegistro(int nRegistro) {

        //ABRIR EL ARCHIVO ORIGINAL Y COLOCARSE AL INICIO
        try {
            File f = new File(nombreArchivo + ".dat");
            int i = 1; //contador de espacios
            int p = 1;
            String guardado = "";
            RandomAccessFile archivo = new RandomAccessFile(f, "rw");
            archivo.seek(0);

            if (nRegistro == 1) {
                archivo.writeBytes("0");
                i++;
            }
            while ((guardado = archivo.readLine()) != null) {

                if (guardado.isBlank()) {
                    i++;
                }

                while ((i == nRegistro && i != 1) && p == 1) {
                    archivo.writeBytes("0");
                    archivo.readLine();
                    guardado = archivo.readLine();
                    p++;
                }

            }
            // Cerrar los archivos
            archivo.close();
            
        } catch (FileNotFoundException e) {
            System.err.println("Error el archivo no se pudo crear");
        } catch (IOException e) {
            System.err.println("Error no se pudo cerrar el archivo");
        }

      
    }
    
     public void recuperaTodo(){
        
        try {
            File f = new File(nombreArchivo + ".dat");
            int i = 1; //contador de espacios
            String guardado;
            RandomAccessFile archivo = new RandomAccessFile(f, "rw");
            archivo.seek(0);
            int arr[] = new int[100001];    
            int cont=0;
            while ((guardado = archivo.readLine()) != null) {
                
                if(guardado.equals("1"))
                    arr[cont++] = i;

                if (guardado.isBlank()) 
                    i++;
            }
            
            archivo.close();
            for(int c=0; c < cont; c++){
                Thread.sleep(1000);
                System.out.println(arr[c]);
                this.recuperarRegistro(arr[c]);
            }
            // Cerrar los archivos
            
        } catch (FileNotFoundException e) {
            System.err.println("Error el archivo no se pudo crear");
        } catch (IOException e) {
            System.err.println("Error no se pudo cerrar el archivo");
        } catch (InterruptedException ex) {
            System.out.println("Se murio");
        }
    }
    
    public void consultaDatos() {

        int nRegistro = JOPI.entero("Ingresa el numero de registro que desea consultar"); //CANTIDAD DE SALTOS DE LINEA

        try {
            File f = new File(nombreArchivo + ".dat");
            RandomAccessFile archivo = new RandomAccessFile(f, "r");
            archivo.seek(0);
            nRegistro--;
            System.out.println( archivo.length()  );
            
            if( (nRegistro*43) >= archivo.length() ){
                JOPIS.mensaje("El registro no existe!!");
                return;
            }
            
            archivo.seek((nRegistro * 43));
            String linea = archivo.readLine();
            System.out.println("linea = " + linea);
            if(linea.equals("0")){
               
                linea = archivo.readLine()+"\n";
                linea += archivo.readLine()+"\n";
                linea += archivo.readLine()+"\n";
                linea += archivo.readLine();
            
                JOPIS.mensaje(linea);
            }
            else
                JOPIS.mensaje("El registro no existe");
            
            // Cerrar los archivos
            archivo.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error el archivo no se pudo crear");
        } catch (IOException e) {
            System.err.println("Error no se pudo cerrar el archivo");
        }
    }

    public void mostrarTodo(){
        
        try {
            File f = new File(nombreArchivo + ".dat");
            RandomAccessFile archivo = new RandomAccessFile(f, "r");
            archivo.seek(0);
            System.out.println( archivo.length()  );
            String linea , todo="";
            
            if(archivo.length() == 0)
                System.out.println("El archivo esta vacio");
            else
            {
                while( (linea = archivo.readLine()) != null)
                    todo += linea+"\n";
                
                JOPIS.mensaje(todo);
            }
            // Cerrar los archivos
            archivo.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error el archivo no se pudo crear");
        } catch (IOException e) {
            System.err.println("Error no se pudo cerrar el archivo");
        }
        
        
    }
    
    public void modificaRegistro() {
        
        try {
            int nRegistro = JOPI.entero("Registro a modificar?");
            File f = new File(nombreArchivo + ".dat");
            File f2 = new File("copia_"+nombreArchivo+".dat");
            f2.delete();
            RandomAccessFile archivo2 = new RandomAccessFile(f2, "rw");
            archivo2.seek(0);
            int i = 1; //contador de espacios
            String guardado = "";
            RandomAccessFile archivo = new RandomAccessFile(f, "rw");
            archivo.seek(0);

            while ((guardado = archivo.readLine()) != null) {

                if (i != nRegistro) 
                    archivo2.writeBytes(guardado + "\n");
                else{
                    archivo2.writeBytes("0\n"+new Alumno().getDatos());
                    guardado = archivo.readLine();
                    guardado = archivo.readLine();
                    guardado = archivo.readLine();
                    guardado = archivo.readLine();
                    i++;
                }
               
                if (guardado.isBlank())
                    i++;
                
            }
            // Cerrar los archivos
            archivo.close();
            archivo2.close();
            
            Thread.sleep(1000);
            
            if (f.delete())
                f2.renameTo(f);
            else
                System.err.println("Error: aaaaa no se pudo eliminar el archivo original");
            
        } catch (FileNotFoundException e) {
            System.err.println("Error el archivo no se pudo crear");
        } catch (IOException e) {
            System.err.println("Error no se pudo cerrar el archivo");
        } catch (InterruptedException ex) {
            System.err.println("Se murio");
        }

    }
    
}
    