package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.CondicionesPreexistentes.Hipertenso;
import grupo8.TPAnual.model.CondicionesPreexistentes.Vegano;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Grupo;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Repositorios.RepoRecetas;

import org.junit.Before;
import org.junit.Test;

public class TestGrupo {
	
	Usuario juan, oscar, pepe;
	Hipertenso hipertenso;
	Vegano vegano;
	Grupo grupoJPO;
	Grupo grupoSinPepe;
	
	Receta flanConDulceDeLeche, carneAlHornoConPapas;
	RepoRecetas repositorio = new RepoRecetas();
	ComponenteDeReceta flan, dulceDeLeche, caramelo, colitaDeCuadril, papa;
	RecetaBuilder carneAlHornoConPapasBuilder;
	RecetaBuilder flanConDulceDeLecheBuilder;
	List<Receta> recetas = new ArrayList<Receta>();
	
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
				recetas, Rutina.LEVE, grupos);

		oscar = new Usuario(80.5, 1.80, "Oscar", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("queso", "pescado", "frutas"),
				Arrays.asList("polenta", "fideos"), Arrays.asList(vegano,
						hipertenso), recetas, Rutina.INTENSIVO, grupos);

		pepe = new Usuario(70.0, 1.70, "Pepex", "masculino", LocalDate.of(1990,
				4, 2), Arrays.asList("asado", "chivito"), Arrays.asList(),
				Arrays.asList(hipertenso), recetas, Rutina.LEVE, grupos);
		
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
		
		flanConDulceDeLecheBuilder = new RecetaBuilder();
		flanConDulceDeLecheBuilder.setNombre("Flan con dulce de leche");
		flanConDulceDeLecheBuilder.agregarIngrediente(flan);
		flanConDulceDeLecheBuilder.agregarIngrediente(dulceDeLeche);
		flanConDulceDeLecheBuilder.agregarCondimento(caramelo);
		flanConDulceDeLecheBuilder.setCalorias(1720.0);
		flanConDulceDeLecheBuilder.setCreador(juan);
		flanConDulceDeLecheBuilder.setSubidaPorSistema(true);
		flanConDulceDeLecheBuilder.setRepositorio(repositorio);	
		flanConDulceDeLeche = flanConDulceDeLecheBuilder.build();
		
		
		colitaDeCuadril = new ComponenteDeReceta("Colita de cuadril", 500.0, 1000.0);
		papa = new ComponenteDeReceta("Papa", 200.0, 350.0);

		carneAlHornoConPapasBuilder = new RecetaBuilder();
		carneAlHornoConPapasBuilder.setNombre("Carne al horno con papas");
		carneAlHornoConPapasBuilder.agregarIngrediente(colitaDeCuadril);
		carneAlHornoConPapasBuilder.agregarIngrediente(papa);
		carneAlHornoConPapasBuilder.setCalorias(1350.0);
		carneAlHornoConPapasBuilder.setCreador(pepe);
		carneAlHornoConPapasBuilder.setSubidaPorSistema(true);
		carneAlHornoConPapasBuilder.setRepositorio(repositorio);	
		carneAlHornoConPapas = carneAlHornoConPapasBuilder.build();
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
