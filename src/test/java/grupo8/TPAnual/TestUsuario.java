package grupo8.TPAnual;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.exceptions.VeganoInvalidoException;
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
	Receta carneAlHornoConPapas;
	List<ComponenteDeReceta> ingredientes = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentos = new ArrayList<ComponenteDeReceta>();
	ComponenteDeReceta colitaDeCuadril, papa;

	@Before
	public void init() {
		
		//Inicializacion de usuarios
		
		hipertenso = new Hipertenso();
		vegano = new Vegano();
		
		juan = new Usuario(72.2, 1.81, "Juan Manuel", "masculino",
				LocalDate.of(1994, 11, 14), Arrays.asList("sopa", "pasta"),
				Arrays.asList("polenta", "pollo"), Arrays.asList(),
				Arrays.asList(), Rutina.LEVE, Arrays.asList());

		oscar = new Usuario(80.5, 1.80, "Oscar", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("queso", "pescado", "frutas"),
				Arrays.asList("polenta", "fideos"), Arrays.asList(vegano,
						hipertenso), Arrays.asList(), Rutina.INTENSIVO, Arrays.asList());

		pepe = new Usuario(70.0, 1.70, "Pepex", "masculino", LocalDate.of(1990,
				4, 2), Arrays.asList("asado", "chivito"), Arrays.asList(),
				Arrays.asList(hipertenso), Arrays.asList(), Rutina.LEVE, Arrays.asList());
		
		//Inicializacion de recetas
		
		colitaDeCuadril = new ComponenteDeReceta("Colita de cuadril", 500.0, 1000.0);
		papa = new ComponenteDeReceta("Papa", 200.0, 350.0);
		ingredientes.add(colitaDeCuadril);
		ingredientes.add(papa);
		
		carneAlHornoConPapas = new Receta("Carne al horno con papas", ingredientes, condimentos, 1350.0,juan,true);
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
	
	@Test
	public void aJuanNoLeDisgustaLaCarneAlHornoConPapas() {
		assertFalse(juan.leDisgusta(carneAlHornoConPapas));
	}
	
	@Test
	public void aPepeSeLePuedeSugerirLaCarneAlHornoConPapas() {
		assertTrue(pepe.seLePuedeSugerir(carneAlHornoConPapas));
	}
	
	@Test(expected = VeganoInvalidoException.class)
	public void oscarEsVeganoInvalidoPorComerCarneYPollo() {
		oscar = new Usuario(80.5, 1.80, "Oscar", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("carne", "pollo"), Arrays.asList(
				"queso", "pescado"), Arrays.asList(vegano), Arrays.asList(),
				Rutina.LEVE, null);
		
		oscar.esValido();
	}

}