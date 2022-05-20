package graphs;

import java.util.ArrayList;
import java.util.Scanner;



/**
 * @author Diego Alonzo 20172
 */
public class Main {
 
	/**
	 * Es el main
	 * @param args
	 */
	//C:\Users\Windows 10\Documents\UVG\CODING\Algoritmos y estructuras de datos\HDT\HDT10\Others\guategrafo.txt
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		Leer archivo = new Leer();
		System.out.println("Ingrese el directorio:");
		String ruta = teclado.nextLine();
		String texto = archivo.Leer(ruta);
		ArrayList<String> ciudades = new ArrayList<>();
		String ciudad = "";
		for(int i=0;i<texto.length();i++) {
			if(Character.isAlphabetic((texto.charAt(i)))||((String.valueOf(texto.charAt(i))).equals("-"))) {
				ciudad=ciudad+texto.charAt(i);
			}else if(!Character.isAlphabetic((texto.charAt(i)))) {
				if(!(Character.isDigit((texto.charAt(i))))) {
					if(!((String.valueOf(texto.charAt(i))).equals("\n"))) {
						if(!(ciudades.contains(ciudad))) {	
							ciudades.add(ciudad);
						}
						ciudad="";
					}
				}
			}
		}
		Integer caminos[][] = new Integer[ciudades.size()][ciudades.size()];
		for(int i=0;i<caminos.length;i++) {
			caminos[i][i]=0;
		}
		int contador=0;
		int l=0;
		int j=0;
		ciudad="";
		for ( int i = 0 ; i < texto.length() ; i++ ) {
			if ( Character.isAlphabetic((texto.charAt(i))) || (Character.isDigit((texto.charAt(i)))) || ((String.valueOf(texto.charAt(i))).equals("-")) ) {
				ciudad = ciudad + texto.charAt(i) ;// Le suma los caracteres a la palabra -> A->An->Ant->Anti->Antig->Antigu->Antigua
			}else if ( !Character.isAlphabetic((texto.charAt(i))) ) {
					if ( contador == 0 ) {	// En caso de que el contador sea 0
						l = ciudades.indexOf(ciudad); // Se obtiene el index de la ciudad
						contador++ ;	// Le suma 1 al contador
						ciudad = "" ;	// Reset de ciudad
					}else if( contador == 1 ) {	// Si el contador es 1
						j=ciudades.indexOf(ciudad);	// j es el index de la ciudad 1
						contador++;	// Se suma 1 al contador
						ciudad="";	// Reset de ciudad
					}else if(contador==2) {
						caminos[l][j]= Integer.parseInt(ciudad);
						caminos[j][l]= Integer.parseInt(ciudad);
						ciudad="";
						contador=0;
						l=0;
						j=0;
					}
					
				}
			
		}
		for(int i=0;i<caminos.length;i++) {
			for ( int k = 0 ; k < caminos.length ; k ++ ) {
			if ( caminos [k][i] == null ) {
				caminos[k][i] = 1000000000 ;
			}
			
			}
			
		}
		
		Integer rutas[][] = new Integer[ciudades.size()][ciudades.size()];
		for ( int i = 0 ; i < ciudades.size(); i++ ) {
			for (int k = 0 ; k < ciudades.size() ; k++ ) {
				rutas[k][i] = i ; // It fills with i all the column
				if ( k == i) {
					rutas [k][i] = 0 ; //matriz[fila][columna]
				}
			}
		}
		Cities citi = new Cities(caminos, ciudades, rutas);
		
//		citi.printMatrix(rutas);
//		citi.printMatrix(citi.adyacency);
//		System.out.print(citi.getCenter()+"\n");
//		citi.changeRouteStatus("Antigua","Santa-Lucia" , 70);
//		System.out.print(citi.getCenter()+"\n");
//		citi.changeRouteStatus("Guatemala","Santa-Lucia" , 90);
//		citi.changeRouteStatus("Antigua", "Mixco", null);
//		System.out.print(citi.getCenter()+"\n");
//		citi.changeRouteStatus("Guatemala", "Escuintla", 100);
//		System.out.print(citi.getCenter()+"\n");
//		citi.changeRouteStatus("Santa-Lucia","Escuintla" , 100);
//		System.out.print(citi.getCenter()+"\n");
//		citi.changeRouteStatus("Guatemala", "Santa-Lucia", 100);
//		System.out.print(citi.getCenter()+"\n");
//		citi.changeRouteStatus("Guatemala", "Escuintla", 200);
//		System.out.print(citi.getCenter()+"\n");
//		citi.changeRouteStatus("Guatemala", "Mixco", 100);
//		System.out.print(citi.getCenter()+"\n");
//		citi.changeRouteStatus("Guatemala", "Antigua", 100);
//
//		System.out.print(citi.getCenter()+"\n");
//		citi.printRoute("Antigua", "Mixco") ;
//		citi.printRoute("Antigua", "Antigua");
//		citi.printRoute("Antigua", "Guatemala");
//		citi.printRoute("Antigua", "Santa-Lucia");
		
		while (true) {
			System.out.print("Seleccione una opcion:\n1-Buscar la ruta mas corta entre ciudades\n2-Ciudad mas centralizada\n3-Actualizar la conexion entre dos ciudades\n4-Indicar si hay interrupcion entre ciudades\n5-Salir\n") ;
			String opcion = teclado.nextLine() ;
			if (opcion.equals("1")) {
				System.out.print("Ingrese la ciudad 1: ");
				String ciudad1 = teclado.nextLine() ;
				System.out.print("Ingrese la ciudad 2: ");
				String ciudad2 = teclado.nextLine() ;
				citi.printRoute(ciudad1, ciudad2);
			}else if( opcion.equals("2")){
				System.out.print("La ciudad mas centralizada es: " + citi.getCenter() + "\n");
			}else if( opcion.equals("3")){
				System.out.print("Ingrese la ciudad 1: ");
				String ciudad1 = teclado.nextLine() ;
				System.out.print("Ingrese la ciudad 2: ");
				String ciudad2 = teclado.nextLine() ;
				System.out.print("Ingrese el cambio en km: ");
				try {
					String km = teclado.nextLine() ;
					citi.changeRouteStatus(ciudad1, ciudad2,Integer.valueOf(km)) ;
				}catch (Exception e){
					System.out.print("No ingreso una cantidad de km valida\n") ;
				}
				
			}else if( opcion.equals("4")){
				System.out.print("Ingrese la ciudad 1: ") ;
				String ciudad1 = teclado.nextLine() ;
				System.out.print("Ingrese la ciudad 2: ") ;
				String ciudad2 = teclado.nextLine() ;
				citi.changeRouteStatus(ciudad1, ciudad2, null) ;
			}else if(opcion.equals("5")) {
				System.out.print("Gracias por usar nuestro programa\n") ;
				break ;
			}else {
				System.out.print("No ingreso una opcion valida, vuelvalo a intentar\n");
			}
		}
		teclado.close();
	}
	

}
