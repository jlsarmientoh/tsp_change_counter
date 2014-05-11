/**
 * 
 */
package co.edu.uniandes;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Jorge
 *
 */
public class ManejadorArchivoTest {
	
	
	
	private ManejoArchivos manejador;
	
	@Before
	public void init(){
		manejador = new ManejoArchivos();
	}

	@Test
	@Ignore
	public void cargaArchivos() {
		List result;
		
		result = manejador.buscarArchivos(ConfigTest.RUTA_V1);
		assertNotNull(result);
		
		assertTrue("No ha cargado la totalidad de arvhivos: " + result.size() + " <> 7", result.size() == 7);
		
		result = manejador.buscarArchivos(ConfigTest.RUTA_V2);
		assertNotNull(result);
		
		assertTrue("No ha cargado la totalidad de arvhivos: " + result.size() + " <> 7", result.size() == 7);
	}
	
	@Test 
	public void cargaArvhicoNoExiste(){
		List result;
		
		result = manejador.buscarArchivos(ConfigTest.FAKE_PATH);
		assertNotNull(result);
		
		assertTrue("Se esperaba que no cargara archivos: " + result.size() + " <> 0", result.size() == 0);
	}
	
	@Test
	public void cargaArchivoNoJava(){
		List result;
		
		result = manejador.buscarArchivos(ConfigTest.RUTA_V1);
		assertNotNull(result);
		
		assertTrue("No ha cargado la totalidad de arvhivos: " + result.size() + " <> 7", result.size() == 7);
		
		result = manejador.buscarArchivos(ConfigTest.RUTA_V2);
		assertNotNull(result);
		
		assertTrue("No ha cargado la totalidad de arvhivos: " + result.size() + " <> 7", result.size() == 7);
	}

}
