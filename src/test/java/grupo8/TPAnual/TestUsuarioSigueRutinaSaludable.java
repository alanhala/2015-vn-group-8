package grupo8.TPAnual;

import static org.junit.Assert.*;
import grupo8.TPAnual.exceptions.UsuarioInvalidoException;
import grupo8.TPAnual.model.ComponenteDeReceta;
import grupo8.TPAnual.model.Hipertenso;
import grupo8.TPAnual.model.Receta;
import grupo8.TPAnual.model.Temporada;
import grupo8.TPAnual.model.Usuario;
import grupo8.TPAnual.model.Vegano;
import grupo8.TPAnual.model.Usuario.Rutina;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestUsuarioSigueRutinaSaludable {
	private Usuario juan, oscar, pepe;
	private Hipertenso hipertenso;
	private Vegano vegano;

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

}