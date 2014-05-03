package co.edu.uniandes;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ContadorTest {
	
	private Contador contador;
	
	@Before
	public void init(){
		contador = new Contador();
	}
	
	@Test
	public void cargarClases(){
		List results;
		
		contador.obtenerClases(ConfigTest.RUTA_V1);
		
		results = contador.getLista();
		
		assertTrue(results.size() == 7);
	}
	
	@Test
	public void cargarClasesRutaBase(){
		List results;
		
		contador.obtenerClases(ConfigTest.RUTA_BASE);
		
		results = contador.getLista();
		
		assertTrue(results.size() == 7);
	}
	
	@Test
	public void noCargaRutaFalsa(){
		List results;
		
		contador.obtenerClases(ConfigTest.FAKE_PATH);
		
		results = contador.getLista();
		
		assertTrue(results.size() == 0);
	}
	
	@Test
	public void contarTotalLOC(){
		
		contador.obtenerClases(ConfigTest.RUTA_V1);
		contador.contarLocClase();
		
		assertTrue(contador.totalLoc() > 0);
	}
	
	@Test
	public void contarTotalLOCRutaBase(){
		
		contador.obtenerClases(ConfigTest.RUTA_BASE);
		contador.contarLocClase();
		
		assertTrue(contador.totalLoc() > 0);
	}

}
