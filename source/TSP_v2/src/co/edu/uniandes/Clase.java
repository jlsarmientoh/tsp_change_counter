//*****************************************************************
// Program Assignment:  02                                        
// Name: Gustavo Alberto Suárez Pinto                             
// Date: 04/02/2014                                               
// Description: Este programa tiene la información una clase del  
// 				programa.                                         
//*****************************************************************
// Listing Contents                                               
//                                                                
//                                                                
// Methods:                                                       
// Clase()                                                       
// setNombre()                                                   
// getNombre()                                                   
// setLoc()                                                      
// getLoc()                                                      
// setNumMet()                                                   
// getNumMet()                                                   
//*****************************************************************

package co.edu.uniandes;

public class Clase {
	
	private String nombre;
	private int loc;
	private int numMet;
	
	/**
	 * Este método setea el nombre de la clase.
	 */
	public Clase(String n) {
		
		nombre = n;
	}
		
	/**
	 * Este método retorna el nombre de la clase
	 * @return El nombre de la clase en una variable de tipo String 
	 */
	public String getNombre() {
		
		return nombre;
	}
	
	/**
	 * Este método setea el LOC de la clase
	 */
	public void setLoc(int l) {
		
		loc = l;
	}
	
	/**
	 * Este método retorna el LOC de la clase
	 * @return El LOC de la clase en una variable de tipo int.
	 */
	public int getLoc() {
		
		return loc;
	}
	
	/**
	 * Este métoodo setea el número de metodos que tiene la clase
	 */
	public void setNumMet(int n) {
		
		numMet = n;
	}
	
	/**
	 * Este método retorna el número de métodos que tiene la clase
	 * @return Regreswa el número de métodos en una variable de tipo int.
	 */
	public int getNumMet() {
		
		return numMet;
	}
}
