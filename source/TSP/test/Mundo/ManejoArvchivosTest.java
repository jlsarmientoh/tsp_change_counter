package Mundo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ManejoArvchivosTest {
	
	public static final String RUTA_ARCHIVO_ORIGINAL1 = "../../archivos_prueba/manejoArchivos.java";
	public static final String RUTA_ARCHIVO_MODIFICADO1 = "../../archivos_prueba/manejoArchivos_v2.java";
	public static final String RUTA_ARCHIVO_ORIGINAL2 = "../../archivos_prueba/useComparador.java";
	public static final String RUTA_ARCHIVO_NO_MODIFICADO = "../../archivos_prueba/useComparador_v2.java";

	@Test
	public void compararVersionesDiferentes() {
		manejoArchivos myManejoArchivos = new manejoArchivos();
		myManejoArchivos.iniciarComparacion(RUTA_ARCHIVO_ORIGINAL1, RUTA_ARCHIVO_MODIFICADO1);
	}
	
	@Test
	public void compararVersionesSinCambios() {
		manejoArchivos myManejoArchivos = new manejoArchivos();
		myManejoArchivos.iniciarComparacion(RUTA_ARCHIVO_ORIGINAL2, RUTA_ARCHIVO_NO_MODIFICADO);
	}

}
