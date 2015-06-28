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

		
		// FIXME Usar fixtures?
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
		juanBuilder.setRutina(Rutina.INTENSIVO);
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

		osquiBuilder = new UsuarioBuilder();
		osquiBuilder.setPeso(80.0);
		osquiBuilder.setAltura(1.90);
		osquiBuilder.setNombre("Juan Manuel Oscar");
		osquiBuilder.setSexo("masculino");
		osquiBuilder.setFechaDeNacimiento(LocalDate.of(1985, 10, 11));
		osquiBuilder.agregarCondicion(vegano);
		osquiBuilder.agregarCondicion(hipertenso);
		osquiBuilder.agregarCondicion(diabetico);
		osquiBuilder.setRutina(Rutina.MEDIANO);
		osqui = osquiBuilder.build();

		
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