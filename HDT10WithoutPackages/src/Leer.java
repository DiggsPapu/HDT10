
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * 
 */

/**
 * @author ThinkPad T470
 * Obtenido https://geekytheory.com/como-leer-un-fichero-en-java/
 */
public class Leer {
	/**
	 * Es para la lectura del archivo y la devolucion del texto
	 * @param direccion
	 * @return
	 */
public String Leer(String direccion){ 
        
        String texto = "";
        
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){ 
                temp = temp + bfRead+"\n"; 
            }

            bf.close();
            texto = temp;
            
        }catch(Exception e){ 
            System.err.println("No se encontro archivo");
        }
        return texto;
       
           }
/**
 * Es para listar
 * @param demo
 * @return
 */
	public  ArrayList<String> Listar(String demo) {
	    ArrayList<String> listas = new ArrayList<String>();
	    String cosa ="";
	    for (int i=0;i<demo.length()-1;i++){
	        
	        if((demo.charAt(i)+"").equals("\n")){
	            listas.add(cosa);
	            cosa="";
	        }else{
	            cosa=cosa+demo.charAt(i);
	        }
	    }
	    listas.add(cosa);
	   return listas;
	
		
	}
}
