//*****************************************************************
// Program Assignment:  01                                        
// Name: Gustavo Alberto Suarez Pinto                             
// Date: 05/04/2014                                               
// Description: Esta clase sirve de interfaz para el programa comparador 
// 				solicitando las rutas de las dos versiones del programa a    
//				comparar y mostrando el resultado de dicha comparación.                                 
//*****************************************************************
// Listing Contents                                               
//                                                                
//                                                                
// Methods:                                                       
// generarSalida()
//*****************************************************************

package co.edu.uniandes;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class UseComparador 
{
	/**Este método sirve de interfaz para el programa comparador
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String rutaProgModificado=null;
		String rutaProgOriginal = null;
		//@dsanchez se agrega JfileChosser para seleccionar los archivos
		
		// ruta programa origen
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		//Titulo que llevara la ventana
		chooser.setDialogTitle("Seleccione ruta para programa origen");
		//Elegiremos archivos del directorio
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
		{
			//Si no seleccionamos nada retornaremos No seleccion
			 rutaProgOriginal = chooser.getSelectedFile().toString();
		} 
		else 
		{
			System.out.println("No seleccion ");
		}
		
		
		// ruta programa modificado
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		//Titulo que llevara la ventana
		chooser.setDialogTitle("Seleccione ruta para programa modificado");
		//Elegiremos archivos del directorio
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
		{
			//Si no seleccionamos nada retornaremos No seleccion
			 rutaProgModificado = chooser.getSelectedFile().toString();
		} 
		else 
		{
			System.out.println("No seleccion ");
		}
	
		
		/*
		// Corresponde a la ruta donde se encuentra la versión NO modificada del programa a comparar
		String rutaProgOriginal =  JOptionPane.showInputDialog("Digite la ruta del programa sin modificar");

		// Corresponde a la ruta donde se encuentra la versión modificada del programa a comparar 		
		String rutaProgModificado =  JOptionPane.showInputDialog("Digite la ruta del programa sin modificar");*/

		ManejoArchivos myManejoArchivos = new ManejoArchivos();
		//myManejoArchivos.iniciarComparacion(rutaProgOriginal, rutaProgOriginal);
		myManejoArchivos.iniciarComparacion(rutaProgOriginal, rutaProgModificado);

		// Enviamos la lista de listas para que sea impresa
		generarSalida(myManejoArchivos.getSalida(), rutaProgModificado);

	}

	/**
	 * Este método se encarga de visualizar el contenido de la lista de listas de líneas
	 * @param salida Corresponde a la lista de listas de líneas.
	 */
	public static void generarSalida(ArrayList salida, String ruta)
	{
		ArrayList<ArrayList> listaDeListas = salida;

		// Imprimimos primero las líneas adicionadas
		System.out.println("****************************************************");
		System.out.println("Estas son las l�neas adicionadas: ");
		int contAdi = 0;
		for(int i = 0; i < listaDeListas.size(); i++)
		{
			ArrayList<Linea> myListOfLines = listaDeListas.get(i);

			for(int j = 0; j < myListOfLines.size(); j++)
			{
				Linea myLine = myListOfLines.get(j);
				if(myLine.getTipo().equals("adicionada"))
				{
					contAdi++;
				}
			}			
		}
		System.out.println(contAdi);
		System.out.println("****************************************************");

		// Ahora imprimimos las líneas eliminadas
		System.out.println("****************************************************");
		System.out.println("Estas son las l�neas eliminadas: ");
		int contElim = 0;
		for(int i = 0; i < listaDeListas.size(); i++)
		{
			ArrayList<Linea> myListOfLines = listaDeListas.get(i);

			for(int j = 0; j < myListOfLines.size(); j++)
			{
				Linea myLine = myListOfLines.get(j);
				if(myLine.getTipo().equals("eliminada"))
				{
					contElim++;
				}
			}			
			
		}
		System.out.println(contElim);
		System.out.println("****************************************************");
		
		System.out.println("****************************************************");
		System.out.println("Este es el total de l�neas de cada clase del programa modificado: ");
		
		Contador myContador = new Contador();
		myContador.obtenerClases(ruta);	
		myContador.contarLocClase();
		myContador.contarMetodos();
		
		System.out.println("Tama�o total en LOC del programa: " + myContador.totalLoc());
		ArrayList<Clase> myList = myContador.getLista();			
		for(int i = 0; i < myList.size(); i++)
		{
			Clase myClass = (Clase) myList.get(i);
			System.out.println("Ruta y nombre de la clase: " + myClass.getNombre());
			System.out.println("Tama�o en LOC de la clase: " + myClass.getLoc());			
		}
		
	}

}
