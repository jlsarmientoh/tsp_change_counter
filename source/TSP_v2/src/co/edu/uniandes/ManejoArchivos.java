//*****************************************************************
// Program Assignment:  01                                        
// Name: Gustavo Alberto Su�rez Pinto                             
// Date: 05/04/2014                                               
// Description: Esta clase se encarga de buscarlos archivos fuentes, 
// 				compara los archivos de las dos rutas, los que son iguales,   
//				los carga en memoria y los env�a a comparar.                                 
//*****************************************************************
// Listing Contents                                               
//                                                                
//                                                                
// Methods:                                                       
// buscarArchivos()                                              
// compararListas()                                               
// cargarContenido()                                               
// iniciarComparacion()                                                   
// getSalida()                                                    
//*****************************************************************

package co.edu.uniandes;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ManejoArchivos 
{
	ArrayList<ArrayList> salida = new ArrayList<ArrayList>();

	/**
	 * Este m�todo se encarga de buscar los archivos .java de forma recursiva que se encuentren en la 
	 * ruta que se le especifique.
	 * @param p Corresponde a una ruta en donde se desee buscar archivos .java
	 * @return Retorna una lista con la ruta y el nombre de cada archivo encontrado en la ruta especificada.
	 */
	public ArrayList<Archivo> buscarArchivos(String p)
	{	
		// Se crea el arreglo que tendr� los archivos.
		ArrayList<Archivo> archivos = new ArrayList<Archivo>();

		// Creamos el arreglo en el cual vamos a almacenar los archivos que encontremos en la ruta especificada.
		Archivo myArchivo;

		// String que contrandra el valor de la ruta en la cual se encuentran los fuentes del programa.
		String rutaArchivo = "";

		// String que contendra los fuentes del programa.
		String archivo = "";

		// Cargamos el path sobre el cual vamos a buscar los archivos .java
		File path = new File(p);

		// Sacamos un listado de todos los elementos que se encuentran en la ruta.
		File[] files = path.listFiles();

		// Obtenemos la longitud de caracteres de la ruta que se le pasa como parametro al m�todo
		int posicion = p.length() + 1;

		// Comenzamos a iterar sobre la lista de elementos encontrados en la ruta que se le paso al m�todo.
		//@dsanchez
		//se agrega if para valida cuando la lista llega vacia
		if(files!=null)
		{
			for(File f : files)
			{
				myArchivo = new Archivo();

				// Si el elemento es de tipo carpeta
				if(f.isDirectory())
				{
					// De forma recursiva se llama al mismo m�todo pero esta vez con la ruta del elemento de tipo carpeta
					ArrayList<Archivo> aux = buscarArchivos(f.getAbsolutePath());
					for(int i = 0; i < aux.size(); i++)
					{
						archivos.add(aux.get(0));
					}
				}
				else
				{
					// Almacenamos la ruta del elemento de tipo archivo.
					rutaArchivo = f.getPath();

					// Buscamos solo aquellos elementos de tipo archivo que sean de java
					if(rutaArchivo.endsWith(".java"))
					{
						// Obtenemos s�lo el nombre del Archivo, sin la ruta en la cual se encuentra.
						archivo = rutaArchivo.substring(posicion);					

						// Almacenamos la ruta y el nombre del archivo en el arreglo de archivos
						myArchivo.setRuta(rutaArchivo);
						myArchivo.setNombre(archivo);
						archivos.add(myArchivo);
					}				
				}
			}
		}
		else 
		{
			System.out.println("la ruta no contiene archivos...");
		}

		return archivos;		

	}

	/**
	 * Este m�todo compara dos listas de archivos, buscando aquellos archivos que se encuentren en las dos
	 * listas, posteriormente env�a cada pareja a comparar su contenido.
	 * @param archivosProgOri Corresponde a la lista de archivos que pertenecen al programa original.
	 * @param archivosProgMod Corresponde a la lista de archivos que pertenecen al programa modificado.
	 * @return Retorna una lista de lineas de todas las parejas que fueron comparadas por su contenido.
	 */
	public void compararListas(ArrayList<Archivo> archivosProgOri, ArrayList<Archivo> archivosProgMod)
	{
		ArrayList<Linea> listOfLines = new ArrayList<Linea>();

		// Comenzamos a iterar la primera lista de archivos
		for(int i = 0; i < archivosProgOri.size(); i++)
		{
			Archivo myFile = archivosProgOri.get(i);

			// Comenzamos a iterar la segunda lista de archivos
			for(int j = 0; j < archivosProgMod.size(); j++)
			{
				Archivo myFileMod = archivosProgMod.get(j);

				// Si el nombre de un elemento de una lista coincide con la otra, se cargan en memoria
				// y se envian a comparar
				if(myFile.getNombre().equals(myFileMod.getNombre()))
				{
					Diff myDiff = new Diff();

					// Guardamos el resultado de la comparacion en una lista de lineas
					listOfLines = myDiff.comparar(cargarContenido(myFile.getRuta()), 
							cargarContenido(myFileMod.getRuta()), "ResultadoComparacion_" + j);
					salida.add(listOfLines);
				}
			}
		}
		
	}

	/**
	 * Este m�todo se encarga de recibir un archivo y cargar su contenido en un mapa de java.
	 * @param r Corresponde a la ruta en donde se encuentra el archivo que tiene que cargar en el 
	 * mapa de java.
	 * @return Retorna el mapa una vez carg� el contenido del archivo en �l.
	 */
	public Map<Integer, String> cargarContenido(String r)
	{
		File myFile = new File(r);
		Scanner myScanner;
		Map<Integer, String> myMap = new TreeMap<Integer, String>();
		int key = 0;
		try
		{
			myScanner = new Scanner(myFile);
			while(myScanner.hasNextLine())
			{
				String line = myScanner.nextLine();
				myMap.put(key, line);
				key++;
			}
		}
		catch (Exception e)
		{
			System.out.println("Se presento el siguiente error " + e);
		}		

		return myMap;

	}

	/**
	 * Este m�todo se encarga de iniciar la comparaci�n de los fuentes de las dos versiones del programa
	 * y adiciona el resultado de la comparacion de cada pareja de fuentes en una lista de listas de l�neas.
	 * @param rutaProgOriginal Corresponde a la ruta del programa original
	 * @param rutaProgModificado Corresponde a la ruta del programa modificado
	 */
	public void iniciarComparacion(String rutaProgOriginal, String rutaProgModificado)
	{
		compararListas(buscarArchivos(rutaProgOriginal), 
				buscarArchivos(rutaProgModificado));
	}	

	/**
	 * Este m�todo retorna la lista de listas de l�neas
	 * @return Lista de Listas de l�neas
	 */
	public ArrayList<ArrayList> getSalida()
	{
		return salida;
	}

}
