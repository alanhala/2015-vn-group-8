package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;

import grupo8.TPAnual.exceptions.UsuarioInvalidoException;
import grupo8.TPAnual.model.ComponenteDeReceta;
import grupo8.TPAnual.model.Hipertenso;
import grupo8.TPAnual.model.Receta;
import grupo8.TPAnual.model.Temporada;
import grupo8.TPAnual.model.Usuario;
import grupo8.TPAnual.model.Usuario.Rutina;
import grupo8.TPAnual.model.Vegano;

import org.junit.Before;
import org.junit.Test;

public class TestUsuarioValido {
	private Usuario juan, oscar;
	private Hipertenso hipertenso;
	private Vegano vegano; 
			
	@Before
	public void init() {
		hipertenso = new Hipertenso();
		vegano = new Vegano();
		juan = new Usuario(72.2, 1.81, "Juan Manuel", "masculino", LocalDate.of(1994, 11, 14), Arrays.asList("sopa", "pasta"), Arrays.asList("polenta", "pollo"), Arrays.asList(hipertenso, vegano), Arrays.asList(), Rutina.LEVE);
		}


	@Test
	public void juanEsValido() {
		assertTrue(juan.esValido());
	}
	
	@Test
	public void creacionDeOscarTiraExcepcion() {
		try {
		oscar = new Usuario(80.5, 1.80, "Oscar", "masculino", LocalDate.of(1994, 9, 24), Arrays.asList("carne", "pollo"), Arrays.asList("queso", "pescado"), Arrays.asList(vegano), Arrays.asList(), Rutina.LEVE);
		} catch (UsuarioInvalidoException e) {
			return;
		}
			 fail();
	}
	
}