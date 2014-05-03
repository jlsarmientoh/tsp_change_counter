/*
 * @(#)Discapacidad.java 1.7 * 
 * Copyright Sun Microsystems, Inc. All rights reserved.    
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * Clase que realiza el proceso de comparacion entre los 2 archivos (Origen, Destino)
 * @author EAIT
 * @version 2.0, 04/27/14
 * @since 1.0
 */

package co.edu.uniandes;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Diff {
	private ArrayList<Linea> myLinea;

	/**
	 * Este metodo se encarga de comparar los mapas de lineas identificando cuales son lineas 
	 * adicionadas y cuales son lineas eliminadas
	 * @param map1 corresponde al mapa con la lineas del archivo origen
	 * @param map2 corresponde al mapa con la lineas del archivo modificado
	 * @param map2 corresponde al nombre del archivo
	 * @return retorna una lista que contiene el consolidado de lineas adicionadas y eliminadas resultado de la comparacion
	 */
	public ArrayList<Linea> comparar(Map map1, Map map2, String nombre){

		// La variable band sirve como bandera para identificar si hubo un error en buscarLineasAdicionadas
		boolean band=false;
		Map mapaArchivo=null;
		if(map1!=null && map2!=null){
			myLinea = new ArrayList<Linea>();
			band=buscarLineasAdicionadas(map1, map2);
			if(band){	
				band=buscarLineasEliminadas(map1, map2);
			}

			if(!band){
				return null;
			}

		}
		else{
			System.out.println("Los maps no pueden ser nulos");
		}

		if(map1.size()>map2.size()){
			mapaArchivo = map1;
		}
		else{
			mapaArchivo= map2;
		}
		//imprimirLista();
		if(new Archivo().generarArchivoSalida(nombre, mapaArchivo, myLinea)){
			System.out.println("Archivo generado en el repositorio");
		}
		else{
			System.out.println("no se pude generar el archivo");
		}
		return myLinea;
	}

	/**
	 * Metodo encargado de registar una linea eliminada
	 * @param linea linea eliminada
	 * @param numLinea número de la linea eliminada
	 */
	public void identificarEliminadas(String linea, int numLinea){

		myLinea.add(new Linea("eliminada",linea, numLinea));
	}

	/**
	 * Metodo encargado de registar una linea adicionada
	 * @param linea linea adicionada
	 * @param numLinea número de la linea adicionada
	 */
	public void identificarAdicionadas(String linea, int numLinea){
		myLinea.add(new Linea("adicionada",linea,numLinea));
	}

	//Prueba local
	public void imprimirLista(){
		for(int i=0;i<myLinea.size();i++){
			System.out.println("Linea");
			System.out.println("tipo " + myLinea.get(i).getTipo());
			System.out.println("name " + myLinea.get(i).getContenido());
			System.out.println("NumLinea " + myLinea.get(i).getNumLinea());
		}
	}

	/**
	 * Metodo que se encarga de buscar las lineas adicionadas 
	 * @param map1 mapa origen
	 * @param map2 mapa modificado
	 * @return true si todo le proceso fue satisfactorio, en caso contrario false
	 */
	public boolean buscarLineasAdicionadas(Map map1, Map map2)
	{
		Iterator itOrig = null;
		Iterator itMod = null;
		String lineaAux = null;
		boolean band=false;
		int cont=1;

		try
		{
			// Inicializamos el valor del iterador del programa modificado
			itMod = map2.entrySet().iterator();

			while (itMod.hasNext()) {
				//obtemos el campos modificado a comparar
				Map.Entry e = (Map.Entry)itMod.next();

				//inicamos el iterador para el mapa origen
				itOrig = map1.entrySet().iterator();
				//seteamos como false la bandera antes de entrar al while de comparacion
				band=false;

				// entramos al while de comparacion entre el mapa de modificado y le mapa de origen
				while (itOrig.hasNext() && !band) {
					//objetemos el campos del mapa origen a comparar
					Map.Entry e2 = (Map.Entry)itOrig.next();

					//realizamos la comparacion	
					if(e.getValue().toString().equals(e2.getValue()))
					{
						band=true;
					}

				}

				//si la bandera el false es porque no encontro pareja en el mapa origen
				//por lo tanto es una linea adicionada
				if(!band){
					identificarAdicionadas(e.getValue().toString(), cont);
				}
				cont++;
			}

			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	/**
	 * metodo que se encarga de buscar las lineas eliminadas
	 * @param map1 mapa origen
	 * @param map2 mapa mpodificado
	 * @return true si todo le proceso fue satisfactorio, en caso contrario false
	 */
	public boolean buscarLineasEliminadas(Map map1, Map map2)
	{
		Iterator itOrig = null;
		Iterator itMod = null;
		String lineaAux = null;
		boolean band=false;
		int cont=1;

		try
		{
			//inicamos el iterador para el mapa origen
			itOrig = map1.entrySet().iterator();

			while (itOrig.hasNext()) {
				//obtenemos el campos origen a comparar
				Map.Entry e = (Map.Entry)itOrig.next();

				//inicamos el iterador para el mapa modificado
				itMod = map2.entrySet().iterator();

				//seteamos como false la bandera antes de entrar al while de comparacion
				band=false;

				// entramos al while de comparacion entre el mapa de origen y le mapa de modificado
				while (itMod.hasNext() && !band) {
					//objetemos el campos del mapa modificado a comparar
					Map.Entry e2 = (Map.Entry)itMod.next();

					//realizamos la comparacion	
					if(e.getValue().toString().equals(e2.getValue())){
						band=true;
					}

				}

				//si la bandera el false es porque no encontro pareja en el mapa modificado
				//por lo tanto es una linea eliminada
				if(!band){
					identificarEliminadas(e.getValue().toString(),cont);
				}
				// incremento el contador que hace referencia al numero de linea
				cont++;
			}

			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	/*
	public static void main(String args[]){
		Diff d = new Diff();
		d.comparar(new HashMap<String,Object>(), new HashMap<String,Object>());
	}*/

}
