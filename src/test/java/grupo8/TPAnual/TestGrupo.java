package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.model.ComponenteDeReceta;
import grupo8.TPAnual.model.Grupo;
import grupo8.TPAnual.model.Hipertenso;
import grupo8.TPAnual.model.Receta;
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
	Receta flanConDulceDeLeche, carneAlHornoConPapas;
	ComponenteDeReceta flan, dulceDeLeche, caramelo, colitaDeCuadril, papa;
	
	List<ComponenteDeReceta> ingredientes1 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentos1 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientes2 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentos2 = new ArrayList<ComponenteDeReceta>();
	
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
		
		grupoJPO = new Grupo("JPO", integrantes, Arrays.asList("sopa", "pasta", "Papa", "Colita de cuadril"));
		juan.agregarAUnGrupo(grupoJPO);
		oscar.agregarAUnGrupo(grupoJPO);
		pepe.agregarAUnGrupo(grupoJPO);	
		
		//Inicializacion de recetas
		
		dulceDeLeche = new ComponenteDeReceta("dulceDeLeche", 100.0, 500.0);
		flan = new ComponenteDeReceta("flan", 400.0, 800.0);
		caramelo = new ComponenteDeReceta("caramelo", 50.0, 420.0);
		
		ingredientes1.add(dulceDeLeche);
		ingredientes1.add(flan);
		condimentos1.add(caramelo);
		
		flanConDulceDeLeche = new Receta("Flan con dulce de leche", ingredientes1, condimentos1, 1720.0);
		
		colitaDeCuadril = new ComponenteDeReceta("Colita de cuadril", 500.0, 1000.0);
		papa = new ComponenteDeReceta("Papa", 200.0, 350.0);
		ingredientes2.add(colitaDeCuadril);
		ingredientes2.add(papa);
				
		carneAlHornoConPapas = new Receta("Carne al horno con papas", ingredientes2, condimentos2, 1350.0);
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
	
	@Test
	public void alGrupoSinPepeLeGustaElFlanConDulceDeLeche() {
		assertTrue(grupoSinPepe.leGusta(flanConDulceDeLeche));
	}
	
	@Test
	public void alGrupoJPOSeLePuedeSugerirLaCarneAlHornoConPapas() {
		assertTrue(grupoJPO.seLePuedeSugerir(carneAlHornoConPapas));
	}
	
}
