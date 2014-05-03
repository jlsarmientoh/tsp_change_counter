//*****************************************************************
// Program Assignment:  02                                        
// Name: Gustavo Alberto Suárez Pinto                             
// Date: 04/02/2014                                               
// Description: Este programa se encarga de contar el LOC de cada 
// 				clase, el número de métodos y el total de LOC de  
//				todo el programa.                                 
//*****************************************************************
// Listing Contents                                               
//                                                                
//                                                                
// Methods:                                                       
// contarLocClase()                                              
// contarMetodos()                                               
// obtenerClases()                                               
// crearNodo()                                                   
// totalLoc()                                                    
//*****************************************************************

package co.edu.uniandes;

import java.util.ArrayList;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

public class Contador 
{
	private ArrayList<Clase> clases = new ArrayList<Clase>();
	private Scanner myScanner;
	private Scanner myScanner2;
	
	/**
	 * <b>Pre:</b> Se tuvo que haber ingresado una ruta valida. 
	 * Este método obtiene todas las clases que se encuentren en la ruta especificada.
	 */
	public void obtenerClases(String p)
	{
		File path = new File(p);
		File[] files = path.listFiles(new FilenameFilter() 
		{			
			public boolean accept(File path, String file) 
			{
				return file.endsWith(".java");
			}
		});	
		
		for(File javaFiles : files)
		{
			String aux = javaFiles.toString();
			System.out.print(aux);
			crearNodo(aux);
		}		
	}
	
	/**
	 * <b>Pre:</b> Se tuvo que haber ingresado una ruta valida.
	 * <b>Pre:</b> En la ruta valida hay por lo menos un archivo .java.
	 * Este método crea un nuevo nodo en la lista de clases.
	 */
	private void crearNodo(String n)
	{
		Clase myClass = new Clase(n);
		clases.add(myClass);
	}
	
	/**
	 * <b>Pre:</b> La lista tiene por lo menos un elemento.
	 * Este método se encarga de contar LOC de  cada una de las clases de la lista.
	 */	
	public void contarLocClase()
	{		
		int contLoc;
		for(int i = 0; i < clases.size(); i++)
		{
			contLoc = 0;
			Clase myClass = (Clase) clases.get(i);
			File myFile = new File(myClass.getNombre());			
			try
			{
				myScanner = new Scanner(myFile);
				while(myScanner.hasNextLine())
				{
					String aux = myScanner.nextLine();
					aux = aux.trim();
					if(! "".equals(aux) && ! "{".equals(aux) && ! "}".equals(aux))
					{
						if(! "/**".equals(aux) && ! "*/".equals(aux) && ! aux.startsWith("})"))
						{
							if(! "//".equals(aux) && ! aux.startsWith("//*"))
							{
								contLoc++;
							}							
						}						
					}					
				}
			}
			catch (IOException e)
			{
				System.out.println("Se produjo el siguiente error");
			}			
			myClass.setLoc(contLoc);
		}
	}
	
	/**
	 * <b>Pre:</b> La lista tiene por lo menos un elemento.
	 * Este método se encarga de contar el número de métodos que tiene cada clase.
	 */
	public void contarMetodos()
	{
		int contMet;
		for(int i = 0; i < clases.size(); i++)
		{
			contMet = 0;
			Clase myClass = (Clase) clases.get(i);
			File myFile = new File(myClass.getNombre());
			
			try
			{
				myScanner2 = new Scanner(myFile);				
				while(myScanner2.hasNextLine())
				{
					String aux = myScanner2.nextLine();
					aux = aux.trim();
					if(aux.startsWith("public") || aux.startsWith("private"))
					{
						if(aux.endsWith(")"))
						{
							contMet++;
						}
					}
				}
			}
			catch(IOException e)
			{
				System.out.println("Se produjo el siguiente error " + e);
			}
			myClass.setNumMet(contMet);
		}
	}
	
	/**
	 * Este método se encarga de contar el total de líneas que tiene el programa.
	 */
	public int totalLoc()
	{
		int totalLoc = 0;
		for(int i = 0; i < clases.size(); i++)
		{
			Clase myClass = (Clase) clases.get(i);
			totalLoc += myClass.getLoc();
		}
		
		return totalLoc;
	}
	
	/**
	 * Este método retorna la lista de clases.
	 */
	public ArrayList<Clase> getLista()
	{		
		return clases;
	}
}
