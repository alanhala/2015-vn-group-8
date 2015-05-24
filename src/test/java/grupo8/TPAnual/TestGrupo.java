package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.model.Grupo;
import grupo8.TPAnual.model.Hipertenso;
import grupo8.TPAnual.model.Usuario;
import grupo8.TPAnual.model.Vegano;
import grupo8.TPAnual.model.Usuario.Rutina;

import org.junit.Before;
import org.junit.Test;

public class TestGrupo {
	
	Usuario juan, oscar, pepe;
	Hipertenso hipertenso;
	Vegano vegano;
	Grupo grupoJPO;
	Grupo grupoSinPepe;

	
	List<Usuario> integrantes = new ArrayList<Usuario>();
	List<Usuario> integrantesSinPepe = new ArrayList<Usuario>();
	List<Grupo> grupos = new ArrayList<Grupo>();
	
	@Before
	public void init(){
		hipertenso = new Hipertenso();
		vegano = new Vegano();
		
		juan = new Usuario(72.2, 1.81, "Juan Manuel", "masculino",
				LocalDate.of(1994, 11, 14), Arrays.asList("sopa", "pasta"),
				Arrays.asList("polenta", "pollo"), Arrays.asList(),
				Arrays.asList(), Rutina.LEVE, grupos);

		oscar = new Usuario(80.5, 1.80, "Oscar", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("queso", "pescado", "frutas"),
				Arrays.asList("polenta", "fideos"), Arrays.asList(vegano,
						hipertenso), Arrays.asList(), Rutina.INTENSIVO, grupos);

		pepe = new Usuario(70.0, 1.70, "Pepex", "masculino", LocalDate.of(1990,
				4, 2), Arrays.asList("asado", "chivito"), Arrays.asList(),
				Arrays.asList(hipertenso), Arrays.asList(), Rutina.LEVE, grupos);
		
		grupoSinPepe = new Grupo("Pepi-no", integrantesSinPepe, Arrays.asList("chocolate", "dulceDeLeche"));
		juan.agregarAUnGrupo(grupoSinPepe);
		oscar.agregarAUnGrupo(grupoSinPepe);
		
		grupoJPO = new Grupo("JPO", integrantes, Arrays.asList("sopa", "pasta"));
		juan.agregarAUnGrupo(grupoJPO);
		oscar.agregarAUnGrupo(grupoJPO);
		pepe.agregarAUnGrupo(grupoJPO);	
	}
	
	@Test
	public void estaPepeEnElGrupo(){
		assertTrue(grupoJPO.perteneceAlGrupo(pepe));
	}
	
	@Test
	public void estaPepeEnElGrupoSinPepe(){
		assertFalse(grupoSinPepe.perteneceAlGrupo(pepe));
	}
	
	@Test
	public void estaOscarEnAmbosGrupos(){
		assertTrue(grupoJPO.perteneceAlGrupo(oscar));
		assertTrue(grupoSinPepe.perteneceAlGrupo(oscar));
	}
	
	@Test
	public void compartePepeGrupoConOscar(){
		assertTrue(pepe.compartisGrupoCon(oscar));
	}
	
}
