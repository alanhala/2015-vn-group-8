package grupo8.TPAnual;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.exceptions.VeganoInvalidoException;
import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.CondicionesPreexistentes.Diabetico;
import grupo8.TPAnual.model.CondicionesPreexistentes.Hipertenso;
import grupo8.TPAnual.model.CondicionesPreexistentes.Vegano;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Repositorios.RepoRecetas;
import grupo8.TPAnual.model.Repositorios.RepoUsuarios;

import org.junit.Before;
import org.junit.Test;

public class TestUsuario {
	
	Usuario juan, oscar, pepe, osqui;
	Hipertenso hipertenso;
	Vegano vegano;
	Diabetico diabetico;
	
	Receta carneAlHornoConPapas;
	ComponenteDeReceta colitaDeCuadril, papa;
	
	RepoUsuarios repoUsuarios = new RepoUsuarios();
	RepoRecetas repositorio;
	
	RecetaBuilder carneAlHornoConPapasBuilder;
	List<Receta> recetas = new ArrayList<Receta>();
	
	@Before
	public void init() {

		// Inicializacion de usuarios

		hipertenso = new Hipertenso();
		vegano = new Vegano();

		
		// FIXME Usar fixtures?
		juan = new Usuario(72.2, 1.81, "Juan Manuel", "masculino",
				LocalDate.of(1994, 11, 14), Arrays.asList("sopa", "pasta"),
				Arrays.asList("polenta", "pollo"), Arrays.asList(),
				recetas, Rutina.LEVE, Arrays.asList());

		oscar = new Usuario(80.5, 1.80, "Oscar", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("queso", "pescado", "frutas"),
				Arrays.asList("polenta", "fideos"), Arrays.asList(vegano,
						hipertenso), recetas, Rutina.INTENSIVO,
				Arrays.asList());

		pepe = new Usuario(70.0, 1.70, "Pepex", "masculino", LocalDate.of(1990,
				4, 2), Arrays.asList("asado", "chivito"), Arrays.asList(),
				Arrays.asList(hipertenso), recetas, Rutina.LEVE,
				Arrays.asList());

		osqui = new Usuario(80.0, 1.90, "Juan Manuel Oscar", "masculino",
				LocalDate.of(1985, 10, 11), Arrays.asList(), Arrays.asList(),
				Arrays.asList(vegano, hipertenso, diabetico), recetas,
				Rutina.MEDIANO, Arrays.asList());
		
		repoUsuarios.agregarAPendienteDeAprobacion(juan);
		repoUsuarios.agregarAPendienteDeAprobacion(oscar);
		repoUsuarios.agregarAPendienteDeAprobacion(pepe);
		repoUsuarios.agregarAPendienteDeAprobacion(osqui);
		
		repositorio = new RepoRecetas();

		colitaDeCuadril = new ComponenteDeReceta("Colita de cuadril", 500.0,
				1000.0);
		papa = new ComponenteDeReceta("Papa", 200.0, 350.0);
		
		carneAlHornoConPapasBuilder = new RecetaBuilder();
		carneAlHornoConPapasBuilder.setNombre("Carne al horno con papas");
		carneAlHornoConPapasBuilder.agregarIngrediente(colitaDeCuadril);
		carneAlHornoConPapasBuilder.agregarIngrediente(papa);
		carneAlHornoConPapasBuilder.setCalorias(1350.0);
		carneAlHornoConPapasBuilder.setCreador(juan);
		carneAlHornoConPapasBuilder.setSubidaPorSistema(true);
		carneAlHornoConPapasBuilder.setRepositorio(repositorio);	
		carneAlHornoConPapas = carneAlHornoConPapasBuilder.build();
	}

	@Test
	public void juanTieneNombreValido() {
		juan.tieneNombreValido();
	}

	@Test
	public void juanTieneFechaDeNacimientoValida() {
		juan.tieneFechaNacimientoValida();
	}

	@Test
	public void juanTieneCamposObligatorios() {
		juan.tieneCamposObligatorios();
	}

	@Test
	public void juanTienePreferenciasAlimenticias() {
		assertTrue(juan.tienePreferenciasAlimenticias());
	}

	@Test
	public void juanTieneSexo() { // XD
		assertTrue(juan.tieneSexo());
	}

	@Test
	public void aOscarLeGustaElQueso() {
		assertTrue(oscar.leGusta("queso"));
	}

	@Test
	public void aPepeNoLeGustaElChori() {
		assertFalse(pepe.leGusta("chori"));
	}

	@Test
	public void juanEsValido() {
		juan.esValido();
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

	@Test
	public void aJuanNoLeDisgustaLaCarneAlHornoConPapas() {
		assertFalse(juan.leDisgusta(carneAlHornoConPapas));
	}

	@Test
	public void aPepeSeLePuedeSugerirLaCarneAlHornoConPapas() {
		assertTrue(pepe.seLePuedeSugerir(carneAlHornoConPapas));
	}

	@Test(expected = VeganoInvalidoException.class)
	public void oscarEsVeganoInvalidoPorComerCarneYPollo() {
		oscar = new Usuario(80.5, 1.80, "Oscar", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("carne", "pollo"), Arrays.asList(
				"queso", "pescado"), Arrays.asList(vegano), Arrays.asList(),
				Rutina.LEVE, null);

		oscar.esValido();
	}

	@Test
	public void juanPepeYOscarEstanEnSolicitudesPendientes() {
		assertTrue(repoUsuarios.getSolicitudesPendientesDeUsuarios()
				.containsAll(Arrays.asList(juan, pepe, oscar)));
	}

	@Test
	public void juanEsAceptadoYEntraEnElRepoUsuarios() {
		repoUsuarios.aceptarPerfil(juan);

		assertTrue(repoUsuarios.getUsuarios().contains(juan));
	}

	@Test
	public void juanEsAceptadoYEsEliminadoDeLasSolicitudesPendientes() {
		repoUsuarios.aceptarPerfil(juan);
		assertFalse(repoUsuarios.getSolicitudesPendientesDeUsuarios().contains(juan));
	}

	@Test
	public void osquiTieneCondicionesDeOscar() {
		repoUsuarios.aceptarPerfil(oscar);
		repoUsuarios.aceptarPerfil(osqui);

		assertTrue(osqui.tieneLasCondicionesDe(oscar));
	}

	@Test
	public void buscarUsuarioConGetEnRepoUsuarios() {
		repoUsuarios.aceptarPerfil(juan);
		repoUsuarios.aceptarPerfil(oscar);
		repoUsuarios.aceptarPerfil(pepe);

		Usuario usuarioFiltrado = repoUsuarios.get(juan);

		assertTrue(usuarioFiltrado.equals(juan));

	}

	@Test
	public void filtrarUsuariosConNombreYCondiciones() {
		repoUsuarios.aceptarPerfil(juan);
		repoUsuarios.aceptarPerfil(oscar);
		repoUsuarios.aceptarPerfil(pepe);
		repoUsuarios.aceptarPerfil(osqui);

		List<Usuario> usuariosFiltrados = repoUsuarios.list(oscar);

		assertTrue(usuariosFiltrados.containsAll(Arrays.asList(oscar, osqui)));

	}
	
	@Test
	public void pepeYJuanNoEstanDentroDeLosUsuariosFiltradosConNombreYCondiciones(){
		repoUsuarios.aceptarPerfil(juan);
		repoUsuarios.aceptarPerfil(oscar);
		repoUsuarios.aceptarPerfil(pepe);
		repoUsuarios.aceptarPerfil(osqui);

		List<Usuario> usuariosFiltrados = repoUsuarios.list(oscar);
		List<Usuario> usuariosNoFiltrados = new ArrayList<Usuario>();
		usuariosNoFiltrados.add(pepe);
		usuariosNoFiltrados.add(juan);

		assertTrue(!usuariosFiltrados.containsAll(usuariosNoFiltrados));
	}

}