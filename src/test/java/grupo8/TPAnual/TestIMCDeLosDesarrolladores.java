package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;

import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Dominio.Usuario.Rutina;

import org.junit.Before;
import org.junit.Test;

public class TestIMCDeLosDesarrolladores {
	private Usuario felipe;
	private Usuario crespo;
	private Usuario ivo;
	private Usuario alan;
	private Usuario diego;

	@Before
	public void init() {
		felipe = new Usuario(55.1, 1.69, "Felipe", LocalDate.of(1994, 9, 23), Rutina.LEVE);
		crespo = new Usuario(72.2, 1.81, "Juan Manuel", LocalDate.of(1994, 11, 14), Rutina.LEVE);
		ivo = new Usuario(65.2, 1.78, "Ivo", LocalDate.of(1994, 12, 12), Rutina.LEVE);
		alan = new Usuario(90.00, 1.80, "Alan", LocalDate.of(1994, 10, 28), Rutina.LEVE);
		diego = new Usuario(62.00, 1.75, "Diego", LocalDate.of(1995, 6, 3), Rutina.LEVE);
	}

	@Test
	public void IMCdeFelipe() {
		Double imcDeFelipe = felipe.calcularIMC();

		assertEquals(imcDeFelipe, (Double) 19.292, 0.001);
	}

	@Test
	public void IMCdeCrespo() {
		Double imcDeCrespo = crespo.calcularIMC();

		assertEquals(imcDeCrespo, (Double) 22.038, 0.001);
	}

	@Test
	public void IMCdeIvo() {
		Double imcDeIvo = ivo.calcularIMC();

		assertEquals(imcDeIvo, (Double) 20.578, 0.001);
	}

	@Test
	public void IMCdeAlan() {
		Double imcDeAlan = alan.calcularIMC();

		assertEquals(imcDeAlan, (Double) 27.777, 0.001);
	}

	@Test
	public void IMCdeDiego() {
		Double imcDeDiego = diego.calcularIMC();

		assertEquals(imcDeDiego, (Double) 20.244, 0.001);
	}

}
