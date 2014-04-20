package Mundo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Diff 
{
	private ArrayList<Linea> myLinea;

	/**
	 * Este metodo se encarga de comparar los mapas de lineas identificando cuales son lineas 
	 * adicionadas y cuales son lineas eliminadas
	 * @param map1 corresponde al mapa con la lineas del archivo origen
	 * @param map2 corresponde al mapa con la lineas del archivo modificado
	 * @return retorna una lista que contiene el consolidado de lineas adicionadas y eliminadas resultado de la comparacion
	 */
	public ArrayList<Linea> comparar(Map map1, Map map2)
	{
		Iterator itOrig = null;
		Iterator itMod = null;
		String lineaAux = null;
		boolean band=false;

		//Prueba local
		/*map1 = new TreeMap<Integer,Object>();
		map2 = new TreeMap<Integer,Object>();

		map1.put(1,"valor1");
		map1.put(2,"valor2");
		map1.put(3,"valor5");

		map2.put(1,"valor1");
		map2.put(2,"valor2");
		map2.put(3,"valor4");*/

		if(map1!=null && map2!=null)
		{
			myLinea = new ArrayList<Linea>();

			// System.out.println("-----------comparacion Eliminadas----------------");

			//inicamos el iterador para el mapa origen
			itOrig = map1.entrySet().iterator();

			while (itOrig.hasNext()) 
			{
				//obtemos el campos origen a comparar
				Map.Entry e = (Map.Entry)itOrig.next();

				//inicamos el iterador para el mapa modificado
				itMod = map2.entrySet().iterator();

				//seteamos como false la bandera antes de entrar al while de comparacion
				band=false;

				// entramos al while de comparacion entre el mapa de origen y le mapa de modificado
				while (itMod.hasNext() && !band) 
				{
					//objetemos el campos del mapa modificado a comparar
					Map.Entry e2 = (Map.Entry)itMod.next();

					//realizamos la comparacion	
					if(e.getValue().toString().equals(e2.getValue()))
					{
						band=true;
					}

				}

				//si la bandera el false es porque no encontro pareja en el mapa modificado
				//por lo tanto es una linea eliminada
				if(!band)
				{
					identificarEliminadas(e.getValue().toString());
				}

			}

			// System.out.println("-----------comparacion Adicionadas----------------");

			//inicamos el iterador para el mapa modificado
			itMod = map2.entrySet().iterator();

			while (itMod.hasNext()) 
			{
				//obtemos el campos modificado a comparar
				Map.Entry e = (Map.Entry)itMod.next();

				//inicamos el iterador para el mapa origen
				itOrig = map1.entrySet().iterator();
				//seteamos como false la bandera antes de entrar al while de comparacion
				band=false;

				// entramos al while de comparacion entre el mapa de modificado y le mapa de origen
				while (itOrig.hasNext() && !band) 
				{
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
				if(!band)
				{
					identificarAdicionadas(e.getValue().toString());
				}

			}
		}
		else
		{
			System.out.println("Los maps no pueden ser nulos");
		}
		
		// imprimirLista();
		return myLinea;
	}

	/**
	 * Metodo encargado de registar una linea eliminada
	 * @param linea linea eliminada
	 */
	public void identificarEliminadas(String linea)
	{
		// System.out.println("*************************Linea Eliminada " + linea);
		myLinea.add(new Linea("eliminada",linea));
	}

	/**
	 * Metodo encargado de registar una linea adicionada
	 * @param linea linea adicionada
	 */
	public void identificarAdicionadas(String linea)
	{
		// System.out.println("+++++++++++++++++++++++++Linea Adicionada " + linea);
		myLinea.add(new Linea("adicionada",linea));
	}

	//Prueba local
	public void imprimirLista()
	{
		for(int i=0;i<myLinea.size();i++)
		{
			System.out.println("Linea");
			System.out.println("tipo " + myLinea.get(i).getTipo());
			System.out.println("name " + myLinea.get(i).getName());
		}
	}

	public static void main(String args[])
	{
		diff d = new diff();
		d.comparar(new HashMap<String,Object>(), new HashMap<String,Object>());
	}

}
