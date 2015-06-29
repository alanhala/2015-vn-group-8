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
import grupo8.TPAnual.model.Repositorios.RepoRecetas;

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
		
		juanBuilder = new UsuarioBuilder();
		juanBuilder.setPeso(72.2);
		juanBuilder.setAltura(1.81);
		juanBuilder.setNombre("Juan Manuel");
		juanBuilder.setSexo("masculino");
		juanBuilder.setFechaDeNacimiento(LocalDate.of(1994, 11, 14));
		juanBuilder.agregarPreferenciaAlimenticia("sopa");
		juanBuilder.agregarPreferenciaAlimenticia("pasta");
		juanBuilder.agregarDisgustoAlimenticio("polenta");
		juanBuilder.agregarDisgustoAlimenticio("pollo");
		juanBuilder.setRutina(Rutina.LEVE);
		juan = juanBuilder.build();
		
		
		oscarBuilder = new UsuarioBuilder();
		oscarBuilder.setPeso(80.5);
		oscarBuilder.setAltura(1.80);
		oscarBuilder.setNombre("Oscar");
		oscarBuilder.setSexo("masculino");
		oscarBuilder.setFechaDeNacimiento(LocalDate.of(1994, 9, 24));
		oscarBuilder.agregarPreferenciaAlimenticia("queso");
		oscarBuilder.agregarPreferenciaAlimenticia("pescado");
		oscarBuilder.agregarPreferenciaAlimenticia("frutas");
		oscarBuilder.agregarDisgustoAlimenticio("polenta");
		oscarBuilder.agregarDisgustoAlimenticio("fideos");
		oscarBuilder.agregarCondicion(hipertenso);
		oscarBuilder.agregarCondicion(vegano);
		oscarBuilder.setRutina(Rutina.INTENSIVO);
		oscar = oscarBuilder.build();

		
		pepeBuilder = new UsuarioBuilder();
		pepeBuilder.setPeso(70.0);
		pepeBuilder.setAltura(1.70);
		pepeBuilder.setNombre("Pepex");
		pepeBuilder.setSexo("masculino");
		pepeBuilder.setFechaDeNacimiento(LocalDate.of(1990,4, 2));
		pepeBuilder.agregarPreferenciaAlimenticia("asado");
		pepeBuilder.agregarPreferenciaAlimenticia("chivito");
		pepeBuilder.agregarCondicion(hipertenso);
		pepeBuilder.setRutina(Rutina.LEVE);
		pepe = pepeBuilder.build();

		
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
