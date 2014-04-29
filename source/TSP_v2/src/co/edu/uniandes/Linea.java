/*
 * @(#)Discapacidad.java 1.7 * 
 * Copyright Sun Microsystems, Inc. All rights reserved.    
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * Esta clase  se utiliza para representar una linea y sus acciones
 * @author EAIT
 * @version 2.0, 04/27/14
 * @since 1.0
 */
package co.edu.uniandes;


public class Linea {
	private String tipo;
	private String contenido;
	private int numLinea;
	
	// TODO
	
	/**
	 * constructor de la clase linea
	 * @param tipo tipo de linea eliminada o adicionada
	 * @param contenido contenido de la linea
	 * @param numLinea numero de la linea
	 */
	public Linea(String tipo, String contenido, int numLinea){
		this.tipo=tipo;
		this.contenido=contenido;
		this.numLinea = numLinea;
	}
	
	/**
	 * retorna el tipo de la linea
	 * @return tipo de linea
	 */
	public String getTipo(){
		return tipo;
	}
	
	/**
	 * retorna el numero de la linea
	 * @return numero de linea
	 */
	public int getNumLinea() {
		return numLinea;
	}
	
	/**
	 * actualiza el numero de la linea
	 * @param numLinea
	 */
	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}
	
	/**
	 * retorna el contenido de la linea
	 * @return contenido de la linea
	 */
	public String getContenido() {
		return contenido;
	}
	
	/**
	 * actualiza el contenido de la linea
	 * @param contenido
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
}
