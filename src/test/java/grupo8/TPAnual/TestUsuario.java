package grupo8.TPAnual;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;

import grupo8.TPAnual.model.ComponenteDeReceta;
import grupo8.TPAnual.model.Hipertenso;
import grupo8.TPAnual.model.Receta;
import grupo8.TPAnual.model.Usuario;
import grupo8.TPAnual.model.Usuario.Rutina;
import grupo8.TPAnual.model.Vegano;

import org.junit.Before;
import org.junit.Test;

public class TestUsuario {
	Usuario juan, oscar, pepe;
	Hipertenso hipertenso;
	Vegano vegano;

	@Before
	public void init() {
		hipertenso = new Hipertenso();
		vegano = new Vegano();
		
		juan = new Usuario(72.2, 1.81, "Juan Manuel", "masculino",
				LocalDate.of(1994, 11, 14), Arrays.asList("sopa", "pasta"),
				Arrays.asList("polenta", "pollo"), Arrays.asList(),
				Arrays.asList(), Rutina.LEVE);

		oscar = new Usuario(80.5, 1.80, "Oscar", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("queso", "pescado", "frutas"),
				Arrays.asList("polenta", "fideos"), Arrays.asList(vegano,
						hipertenso), Arrays.asList(), Rutina.INTENSIVO);

		pepe = new Usuario(70.0, 1.70, "Pepex", "masculino", LocalDate.of(1990,
				4, 2), Arrays.asList("asado", "chivito"), Arrays.asList(),
				Arrays.asList(hipertenso), Arrays.asList(), Rutina.LEVE);
	}
	
	@Test 
	public void juanTieneNombreValido()	{
		juan.tieneNombreValido();
	}
	
	@Test
	public void juanTieneFechaDeNacimientoValida() {
		juan.tieneFechaNacimientoValida();
	}
	
	@Test
	public void juanTieneCamposObligatorios() {
		juan.tieneCamposObligatorios();
	}
	
	@Test
	public void juanTienePreferenciasAlimenticias() {
		assertTrue(juan.tienePreferenciasAlimenticias());
	}
	
	@Test
	public void juanTieneSexo() { // XD
		assertTrue(juan.tieneSexo());
	}
	
	@Test
	public void aOscarLeGustaElQueso() {
		assertTrue(oscar.leGusta("queso"));
	}
	
	@Test
	public void aPepeNoLeGustaElChori() {
		assertFalse(pepe.leGusta("chori"));
	}
	
	@Test
	public void juanEsValido() {
		juan.esValido();
	}
	
	@Test
	public void juanSigueRutinaSaludable() {
		assertTrue(juan.sigueRutinaSaludable());
	}

	@Test
	public void oscarSigueRutinaSaludable() {
		assertTrue(oscar.sigueRutinaSaludable());
	}

	@Test
	public void pepeNoSigueRutinaSaludable() {
		assertFalse(pepe.sigueRutinaSaludable());
	}
	
	
	/* Esperando a que nos diga nico el tema de si tenemos que tirar excepcion cuando una condicion es invalida
	 * para un usuario o directamente podemos usar booleanos (de eso depende como este programdo el test).
	 */
	
	/*@Test(expected = Exception.class)
	public void creacionDeOscarTiraExcepcion() {
		oscar = new Usuario(80.5, 1.80, "Oscar", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("carne", "pollo"), Arrays.asList(
				"queso", "pescado"), Arrays.asList(vegano), Arrays.asList(),
				Rutina.LEVE);
	}*/

}