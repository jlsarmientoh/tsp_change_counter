//*****************************************************************
// Program Assignment:  01                                        
// Name: Gustavo Alberto Su�rez Pinto                             
// Date: 05/04/2014                                               
// Description: Esta clase sirve de interfaz para el programa comparador 
// 				solicitando las rutas de las dos versiones del programa a    
//				comparar y mostrando el resultado de dicha comparaci�n.                                 
//*****************************************************************
// Listing Contents                                               
//                                                                
//                                                                
// Methods:                                                       
// generarSalida()
//*****************************************************************

package Mundo;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class useComparador 
{
	/**Est� m�todo sirve de interfaz para el programa comparador
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
		// Corresponde a la ruta donde se encuentra la versi�n NO modificada del programa a comparar
		String rutaProgOriginal =  JOptionPane.showInputDialog("Digite la ruta del programa sin modificar");

		// Corresponde a la ruta donde se encuentra la versi�n modificada del programa a comparar 		
		String rutaProgModificado =  JOptionPane.showInputDialog("Digite la ruta del programa sin modificar");*/

		manejoArchivos myManejoArchivos = new manejoArchivos();
		//myManejoArchivos.iniciarComparacion(rutaProgOriginal, rutaProgOriginal);
		myManejoArchivos.iniciarComparacion(rutaProgOriginal, rutaProgModificado);

		// Enviamos la lista de listas para que sea impresa
		generarSalida(myManejoArchivos.getSalida());

	}

	/**
	 * Este m�todo se encarga de visualizar el contenido de la lista de listas de l�neas
	 * @param salida Corresponde a la lista de listas de l�neas.
	 */
	public static void generarSalida(ArrayList salida)
	{
		ArrayList<ArrayList> listaDeListas = salida;

		// Imprimimos primero las l�neas adicionadas
		System.out.println("****************************************************");
		System.out.println("Estas son las l�neas adicionadas: ");
		for(int i = 0; i < listaDeListas.size(); i++)
		{
			ArrayList<Linea> myListOfLines = listaDeListas.get(i);

			for(int j = 0; j < myListOfLines.size(); j++)
			{
				Linea myLine = myListOfLines.get(j);
				if(myLine.getTipo().equals("adicionada"))
				{
					System.out.println(myLine.getName());
				}
			}
		}
		System.out.println("****************************************************");

		// Ahora imprimimos las l�neas eliminadas
		System.out.println("****************************************************");
		System.out.println("Estas son las l�neas eliminadas: ");
		for(int i = 0; i < listaDeListas.size(); i++)
		{
			ArrayList<Linea> myListOfLines = listaDeListas.get(i);

			for(int j = 0; j < myListOfLines.size(); j++)
			{
				Linea myLine = myListOfLines.get(j);
				if(myLine.getTipo().equals("eliminada"))
				{
					System.out.println(myLine.getName());
				}
			}
		}
		System.out.println("****************************************************");
	}

}