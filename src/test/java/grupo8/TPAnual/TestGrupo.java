package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.Builders.UsuarioBuilder;
import grupo8.TPAnual.model.CondicionesPreexistentes.Hipertenso;
import grupo8.TPAnual.model.CondicionesPreexistentes.Vegano;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Grupo;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Repositorios.Recetario;

import org.junit.Before;
import org.junit.Test;

public class TestGrupo {
	
	Usuario juan, oscar, pepe;
	UsuarioBuilder juanBuilder, oscarBuilder, pepeBuilder;
	Hipertenso hipertenso;
	Vegano vegano;
	Grupo grupoJPO;
	Grupo grupoSinPepe;
	
	Receta flanConDulceDeLeche, carneAlHornoConPapas;
	Recetario repositorio = new Recetario();
	ComponenteDeReceta flan, dulceDeLeche, caramelo, colitaDeCuadril, papa;
	RecetaBuilder carneAlHornoConPapasBuilder;
	RecetaBuilder flanConDulceDeLecheBuilder;
	List<Receta> recetas = new ArrayList<Receta>();
	
	List<Usuario> integrantes = new ArrayList<Usuario>();
	List<Usuario> integrantesSinPepe = new ArrayList<Usuario>();
	List<Grupo> grupos = new ArrayList<Grupo>();
	

	@Before
	public void init(){
		
		juan = new UsuarioBuilder()
		.setPeso(72.2)
		.setAltura(1.81)
		.setNombre("Juan Manuel")
		.setSexo("masculino")
		.setFechaDeNacimiento(LocalDate.of(1994, 11, 14))
		.agregarPreferenciaAlimenticia("sopa")
		.agregarPreferenciaAlimenticia("pasta")
		.agregarDisgustoAlimenticio("polenta")
		.agregarDisgustoAlimenticio("pollo")
		.setRutina(Rutina.LEVE)
		.build();
		
		
		oscar = new UsuarioBuilder()
		.setPeso(80.5)
		.setAltura(1.80)
		.setNombre("Oscar")
		.setSexo("masculino")
		.setFechaDeNacimiento(LocalDate.of(1994, 9, 24))
		.agregarPreferenciaAlimenticia("queso")
		.agregarPreferenciaAlimenticia("pescado")
		.agregarPreferenciaAlimenticia("frutas")
		.agregarDisgustoAlimenticio("polenta")
		.agregarDisgustoAlimenticio("fideos")
		.agregarCondicion(new Hipertenso())
		.agregarCondicion(new Vegano())
		.setRutina(Rutina.INTENSIVO)
		.build();

		
		pepe = new UsuarioBuilder()
		.setPeso(70.0)
		.setAltura(1.70)
		.setNombre("Pepex")
		.setSexo("masculino")
		.setFechaDeNacimiento(LocalDate.of(1990,4, 2))
		.agregarPreferenciaAlimenticia("asado")
		.agregarPreferenciaAlimenticia("chivito")
		.agregarCondicion(new Hipertenso())
		.setRutina(Rutina.LEVE)
		.build();

		
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
		
		flanConDulceDeLeche = new RecetaBuilder()
		.setNombre("Flan con dulce de leche")
		.agregarIngrediente(flan)
		.agregarIngrediente(dulceDeLeche)
		.agregarCondimento(caramelo)
		.setCalorias(1720.0)
		.setSubidaPorSistema(true)
		.build();
		juan.agregarReceta(flanConDulceDeLeche);
		
		colitaDeCuadril = new ComponenteDeReceta("Colita de cuadril", 500.0, 1000.0);
		papa = new ComponenteDeReceta("Papa", 200.0, 350.0);

		carneAlHornoConPapas = new RecetaBuilder()
		.setNombre("Carne al horno con papas")
		.agregarIngrediente(colitaDeCuadril)
		.agregarIngrediente(papa)
		.setCalorias(1350.0)
		.setSubidaPorSistema(true)
		.build();
		pepe.agregarReceta(carneAlHornoConPapas);
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
