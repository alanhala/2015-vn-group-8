package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;

import grupo8.TPAnual.model.Builders.UsuarioBuilder;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Dominio.Rutina;

import org.junit.Before;
import org.junit.Test;

public class TestIMCDeLosDesarrolladores {
	
	Usuario felipe, crespo, ivo, alan, diego;
	UsuarioBuilder felipeBuilder, crespoBuilder, ivoBuilder, alanBuilder, diegoBuilder;
	

	@Before
	public void init() {
		
		felipe = new UsuarioBuilder()
		.setPeso(55.1)
		.setAltura(1.69)
		.setSexo("masculino")
		.setNombre("Felipe")
		.setFechaDeNacimiento(LocalDate.of(1994, 9, 23))
		.setRutina(Rutina.LEVE)
		.build();
		
		
		crespo = new UsuarioBuilder()
		.setPeso(72.2)
		.setAltura(1.81)
		.setSexo("masculino")
		.setNombre("Juan Manuel")
		.setFechaDeNacimiento(LocalDate.of(1994, 11, 14))
		.setRutina(Rutina.LEVE)
		.build();
		
		
		ivo= new UsuarioBuilder()
		.setPeso(65.2)
		.setAltura(1.78)
		.setNombre("Ivo")
		.setFechaDeNacimiento(LocalDate.of(1994, 12, 12))
		.setSexo("masculino")
		.setRutina(Rutina.LEVE)
		.build();
		
		
		alan = new UsuarioBuilder()
		.setPeso(90.0)
		.setAltura(1.80)
		.setNombre("Alan")
		.setFechaDeNacimiento(LocalDate.of(1994, 10, 28))
		.setSexo("masculino")
		.setRutina(Rutina.LEVE)
		.build();
		
		
		diego = new UsuarioBuilder()
		.setPeso(62.00)
		.setAltura(1.75)
		.setNombre("Diego")
		.setFechaDeNacimiento(LocalDate.of(1995, 6, 3))
		.setSexo("masculino")
		.setRutina(Rutina.LEVE)
		.build();
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
