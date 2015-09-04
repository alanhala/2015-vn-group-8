package grupo8.TPAnual;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.exceptions.VeganoInvalidoException;
import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.Builders.UsuarioBuilder;
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
	
	UsuarioBuilder juanBuilder, oscarBuilder, pepeBuilder, osquiBuilder;
	
	RecetaBuilder carneAlHornoConPapasBuilder;
	List<Receta> recetas = new ArrayList<Receta>();
	
	@Before
	public void init() {

		// Inicializacion de usuarios

		hipertenso = new Hipertenso();
		vegano = new Vegano();

		
		// Usar fixtures?
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
		.setRutina(Rutina.INTENSIVO)
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
		.agregarCondicion(hipertenso)
		.agregarCondicion(vegano)
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
		.agregarCondicion(hipertenso)
		.setRutina(Rutina.LEVE)
		.build();

		osqui = new UsuarioBuilder()
		.setPeso(80.0)
		.setAltura(1.90)
		.setNombre("Juan Manuel Oscar")
		.setSexo("masculino")
		.setFechaDeNacimiento(LocalDate.of(1985, 10, 11))
		.agregarCondicion(vegano)
		.agregarCondicion(hipertenso)
		.agregarCondicion(diabetico)
		.setRutina(Rutina.MEDIANO)
		.build();

		
		repoUsuarios.agregarAPendienteDeAprobacion(juan);
		repoUsuarios.agregarAPendienteDeAprobacion(oscar);
		repoUsuarios.agregarAPendienteDeAprobacion(pepe);
		repoUsuarios.agregarAPendienteDeAprobacion(osqui);
		
		repositorio = new RepoRecetas();

		colitaDeCuadril = new ComponenteDeReceta("Colita de cuadril", 500.0,
				1000.0);
		papa = new ComponenteDeReceta("Papa", 200.0, 350.0);
		
		carneAlHornoConPapas = new RecetaBuilder()
		.setNombre("Carne al horno con papas")
		.agregarIngrediente(colitaDeCuadril)
		.agregarIngrediente(papa)
		.setCalorias(1350.0)
		.setCreador(juan)
		.setSubidaPorSistema(true)
		.setRepositorio(repositorio)	
		.build();
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