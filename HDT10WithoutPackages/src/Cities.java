
import java.util.ArrayList;
import java.util.Collections;

public class Cities {
	private Integer adyacency[][];
	private Integer routes[][];
	private ArrayList<String> cities;
	private final static int INF = 9999, nV = 5;
	/**
	 * Es la clase constructora de ciudadess
	 * @param adyacency
	 * @param ciudades
	 * @param ruta
	 */
	public Cities(Integer [][] adyacency, ArrayList<String> ciudades, Integer[][] ruta){
		cities = ciudades;
		this.routes = ruta;
		this.adyacency=adyacency;
		floydWarshall();
//		for (int k = 0 ; k < cities.size(); k++) {
//			System.out.print(cities.get(k));
//		}
//		System.out.print("\n");
	}
	/**
	 * Es el metodo para generar el algoritmo floydWarshall y que quede asi la matriz
	 * Se obtuvo de https://www.programiz.com/dsa/floyd-warshall-algorithm
	 */
	public void floydWarshall() {
	   
	    
	    // Adding vertices individually
	    for (int k = 0; k < nV; k++) {
	      for (int i = 0; i < nV; i++) {
	        for (int j = 0; j < nV; j++) {
	          if (this.adyacency[i][k] + this.adyacency[k][j] < this.adyacency[i][j]) {
	            this.adyacency[i][j] = this.adyacency[i][k] + this.adyacency[k][j];
	          	this.routes[i][j] = k ; // Esto es para agregar la matriz de rutas
	          }
	        }
	      }
	    }
//	    printMatrix(this.adyacency) ;
//	    printMatrix(this.routes) ;
	  }
	/**
	 * Es la clase para imprimir matrices, sirvio como debugger
	 * Esta clase se obtuvo de https://www.programiz.com/dsa/floyd-warshall-algorithm
	 * @param matrix
	 */
	  public void printMatrix(Integer matrix[][]) {
	    for (int i = 0; i < nV; ++i) {
	      for (int j = 0; j < nV; ++j) {
	        if (matrix[i][j] == INF)
	          System.out.print("INF ");
	        else
	          System.out.print(matrix[i][j] + "  ");
	      }
	      System.out.println();
	    }
	    
	  }
	  /**
	   * Es la clase para obtener el id de la ciudad
	   * @param city
	   * @return
	   */
	  private Integer getCity(String city) {
	    	
	    	if (cities.indexOf(city)!=-1) {
	    		return cities.indexOf(city);
	    	}
	    	return null;
	    }
	  public void changeRouteStatus(String city1, String city2, Integer espera) {
		  Integer id1 = getCity(city1);
//		  System.out.print(id1);
		  Integer id2 = getCity(city2);
//		  System.out.print(id2);
		  if ( id1 != null && id2 != null && espera != null ) {	// En el caso de que se tenga que no hay interrupcion de trafico
			  this.adyacency[id1][id2] = espera ;
			  this.adyacency[id2][id1] = espera ;
			  floydWarshall();
		  }
		  else if ( id1 != null && id2 != null && espera == null  ) {
			  this.adyacency[id1][id2] = 1000000000 ;
			  this.adyacency[id2][id1] = 1000000000 ; // Infinito entre ellos
			  floydWarshall();
		  }
		  else {
			  System.out.print("No existe la ciudad ingresada\n");
		  }
		  
	  }
	  /**
	   * Es el metodo para obtener la ciudad que esta en el centro del grafo
	   * 
	   * @return String
	   */
	  public String getCenter() {
		  ArrayList<Integer> maxValue = new ArrayList<>();
		  ArrayList<Integer> maxId = new ArrayList<>();
		  for ( int k = 0 ; k < this.cities.size() ; k++ ) {
			  ArrayList<Integer> valores = new ArrayList<>();
			  for ( int i = 0 ; i < this.cities.size() ; i++ ) {
				  valores.add(this.adyacency[i][k]) ; //Se add cada valor de la columna
			  }
			  maxValue.add(Collections.max(valores)) ; // Se add el value max de la columna
			  maxId.add(valores.indexOf(Collections.max(valores))) ;
		  }
		  for ( int k = 0 ; k < maxValue.size() ; k ++ ) {
			  if ( maxValue.get(k) == Collections.min(maxValue) ) {
				  return this.cities.get(maxId.get(k))  ; //Retorna el nombre de la ciudad que es centro
			  }
		  }
		  return null;
	  }
	  /**
	   * Es el metodo para imprimir la ruta existente entre dos ciudades
	   * @param city1
	   * @param city2
	   */
	  public void printRoute(String city1, String city2) {
		  if (city1.equals(city2)) {
			  System.out.print(city1+"->"+city2+"->"+0+"\n");
		  }
		  else if (getCity(city1)!=null && getCity(city2)!=null){
			  ArrayList<String>  ruta = new ArrayList<>() ;
			  Integer id1 = getCity(city1) ;
			  Integer id2 = getCity(city2) ;
			  Integer de = this.routes[id1][id2] ;
			  ruta.add(city2) ;
			  ruta.add(this.cities.get(de));
			  while (de != this.routes[id1][de] ) {
				  de = this.routes[id1][de] ;
				  ruta.add(this.cities.get(de)) ;
			  }
			  ruta.add(city1) ;
			  for (int k = ruta.size()-1  ; k >= 0 ; k -- ) {
				  System.out.print( ruta.get(k) + "->" );
			  }
			  System.out.print(this.adyacency[id1][id2]+"\n");
		  }else {
			  System.out.print("Alguna de las ciudades ingresadas no existe\n");
		  }
	  }
}
