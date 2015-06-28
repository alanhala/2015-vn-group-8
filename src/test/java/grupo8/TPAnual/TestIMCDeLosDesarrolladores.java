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
		
		felipeBuilder = new UsuarioBuilder();
		felipeBuilder.setPeso(55.1);
		felipeBuilder.setAltura(1.69);
		felipeBuilder.setSexo("masculino");
		felipeBuilder.setNombre("Felipe");
		felipeBuilder.setFechaDeNacimiento(LocalDate.of(1994, 9, 23));
		felipeBuilder.setRutina(Rutina.LEVE);
		felipe = felipeBuilder.build();
		
		
		crespoBuilder = new UsuarioBuilder();
		crespoBuilder.setPeso(72.2);
		crespoBuilder.setAltura(1.81);
		crespoBuilder.setSexo("masculino");
		crespoBuilder.setNombre("Juan Manuel");
		crespoBuilder.setFechaDeNacimiento(LocalDate.of(1994, 11, 14));
		crespoBuilder.setRutina(Rutina.LEVE);
		crespo = crespoBuilder.build();
		
		
		ivoBuilder = new UsuarioBuilder();
		ivoBuilder.setPeso(65.2);
		ivoBuilder.setAltura(1.78);
		ivoBuilder.setNombre("Ivo");
		ivoBuilder.setFechaDeNacimiento(LocalDate.of(1994, 12, 12));
		ivoBuilder.setSexo("masculino");
		ivoBuilder.setRutina(Rutina.LEVE);
		ivo = ivoBuilder.build();
		
		
		alanBuilder = new UsuarioBuilder();
		alanBuilder.setPeso(90.0);
		alanBuilder.setAltura(1.80);
		alanBuilder.setNombre("Alan");
		alanBuilder.setFechaDeNacimiento(LocalDate.of(1994, 10, 28));
		alanBuilder.setSexo("masculino");
		alanBuilder.setRutina(Rutina.LEVE);
		alan = alanBuilder.build();
		
		
		diegoBuilder = new UsuarioBuilder();
		diegoBuilder.setPeso(62.00);
		diegoBuilder.setAltura(1.75);
		diegoBuilder.setNombre("Diego");
		diegoBuilder.setFechaDeNacimiento(LocalDate.of(1995, 6, 3));
		diegoBuilder.setSexo("masculino");
		diegoBuilder.setRutina(Rutina.LEVE);
		diego = diegoBuilder.build();
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
