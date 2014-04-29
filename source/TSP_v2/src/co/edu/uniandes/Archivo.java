/*
 * @(#)Discapacidad.java 1.7 * 
 * Copyright Sun Microsystems, Inc. All rights reserved.    
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * Esta clase  se utiliza para representar un archivo y sus acciones.
 * @author EAIT
 * @version 2.0, 04/27/14
 * @since 1.0
 */


package Mundo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Archivo {
	private String nombre;
	private String ruta;

	/**
	 * retorna el nombre del archivo
	 * @return nombre del archivo
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * actualiza el nombre del archivo
	 * @param nombre el nuevo nombre del archivo
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * retorna la ruta del archivo
	 * @return ruta del archivo
	 */
	public String getRuta() {
		return ruta;
	}


	/**
	 * actualiza la ruta del archivo
	 * @param ruta nueva ruta para el archivo
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Este metodo se encarga de generar un fichero que contiene el archivo resultado despues de ejecutar el diff
	 * @param nombre nombre del archivo de salida
	 * @param archivo colleccion que contiene el archivo desde el cual se genera la salida
	 * @param lineasModificadas lista con la lineas modificadas resultado del diff
	 * @return true si se genero el archivo correctamente, en caso contratio retorna false
	 */
	public boolean generarArchivoSalida(String nombre, Map archivo, ArrayList<Linea> lineasModificadas){
		Iterator iterador = null;
		int cont=1;
		ArrayList<Linea> linea=null;
		Linea lineaAux=null;

		// inicio el buffer de escritura del archivo de salida
		// se guardara en una carpeta que esta en le raiz del proyecto que se llama repositorio
		// el nombre del archivo debe terminar en .java
		try(BufferedWriter outputFile =  new BufferedWriter(new FileWriter("repositorio/"+nombre))){
			//inicializamos el iterador para recorrer el mapa que contiene el archivo
			iterador = archivo.entrySet().iterator();
			// empezamos a recorrer el mapa
			while (iterador.hasNext()) {

				Map.Entry lineaArchivo = (Map.Entry)iterador.next();
				// en el array de lineas se carga con el resultado de buscar ese numero de linea entre el array de lineas modificadas
				linea= buscarLinea(cont,lineasModificadas);
				// si la lista de linea no es vacia es porque encontro una linea modificada asociada a ese numero de linea
				if(!linea.isEmpty()){
					// recorro el array de lineas encontradas
					for(int i=0; i<linea.size() ; i++){
						lineaAux = linea.get(i);
						// si la linea es de tipo A
						if(lineaAux.getTipo().equals("adicionada")){
							// no se tiene en cuenta las lineas vacias
							if(!lineaAux.getContenido().trim().equals("")){
								//escribo la etiqueta
								outputFile.write("@Adicionada");
								outputFile.newLine();
								//escribo el contenido de la linea del archivo
								outputFile.write(lineaArchivo.getValue().toString());
								outputFile.newLine();
							}
						}
						else{

							// no se tiene en cuenta las lineas vacias
							if(!lineaAux.getContenido().trim().equals("")){
								// escribo la etiqueta
								outputFile.write("@Eliminada");
								outputFile.newLine();
								// escribo el contenido de la linea buscada
								outputFile.write(lineaAux.getContenido());
								outputFile.newLine();
							}

						}
					}
				}
				// si es vacia entonces escribirmos la linea del archivo sin ninguna etiqueta
				else{
					outputFile.write(lineaArchivo.getValue().toString());
					outputFile.newLine();
				}
				cont++;

			}
		}
		catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error al cargar el archivo " + e.getMessage());
			return false;
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error realizando el proceso " + e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Se encarga de buscar lineas dentro de las lineas modificadas a partir de un numero de linea dado
	 * @param numLinea numero de linea a buscar
	 * @param lineasModificadas lineas modificadas
	 * @return Lista que contiene las lineas encontradas
	 */
	public ArrayList<Linea> buscarLinea(int numLinea, ArrayList<Linea> lineasModificadas){
		// creamos un array que contendra las lineas encontradas modificadas 
		// a partir del numero de la linea
		ArrayList<Linea> linea= new ArrayList<>();
		for(int i=0; i< lineasModificadas.size() ; i++){

			if(numLinea == lineasModificadas.get(i).getNumLinea()){
				linea.add(lineasModificadas.get(i));
			}
		}

		return linea;

	}
}
