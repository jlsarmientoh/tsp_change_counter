//*****************************************************************
// Program Assignment:  02                                        
// Name: Gustavo Alberto Su�rez Pinto                             
// Date: 04/02/2014                                               
// Description: Este programa tiene la informaci�n una clase del  
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
	 * Este m�todo setea el nombre de la clase.
	 */
	public Clase(String n) {
		
		nombre = n;
	}
		
	/**
	 * Este m�todo retorna el nombre de la clase
	 * @return El nombre de la clase en una variable de tipo String 
	 */
	public String getNombre() {
		
		return nombre;
	}
	
	/**
	 * Este m�todo setea el LOC de la clase
	 */
	public void setLoc(int l) {
		
		loc = l;
	}
	
	/**
	 * Este m�todo retorna el LOC de la clase
	 * @return El LOC de la clase en una variable de tipo int.
	 */
	public int getLoc() {
		
		return loc;
	}
	
	/**
	 * Este m�toodo setea el n�mero de metodos que tiene la clase
	 */
	public void setNumMet(int n) {
		
		numMet = n;
	}
	
	/**
	 * Este m�todo retorna el n�mero de m�todos que tiene la clase
	 * @return Regreswa el n�mero de m�todos en una variable de tipo int.
	 */
	public int getNumMet() {
		
		return numMet;
	}
}
