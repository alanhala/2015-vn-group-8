package grupo8.TPAnual;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.Commands.EnviarMail;
import grupo8.TPAnual.model.Commands.LogConsulta;
import grupo8.TPAnual.model.Commands.MarcarComoFavoritas;
import grupo8.TPAnual.model.Decorators.FiltroNulo;
import grupo8.TPAnual.model.DependenciasLocales.Mail;
import grupo8.TPAnual.model.DependenciasLocales.MailSender;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Monitores.GestorDeConsultas;
import grupo8.TPAnual.model.Repositorios.RepoRecetas;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestCommands {

	private MailSender mailSender;
	private FiltroNulo filtro;
	private Usuario ernesto;
	private Receta receta1, receta2;
	private RecetaBuilder receta1Builder, receta2Builder;
	private EnviarMail tratamientoEnviarMail;
	private MarcarComoFavoritas tratamientoMarcarComoFavoritas;
	private ComponenteDeReceta pollo, papa, merluza, cebolla;
	private RepoRecetas repoRecetas;
	private GestorDeConsultas gestor;
	private List<Receta> recetasFiltradas;

	@Before
	public void setup() {
		repoRecetas = new RepoRecetas();
		gestor = new GestorDeConsultas();
		mailSender = mock(MailSender.class);
		EnviarMail.setMailSender(mailSender);
		tratamientoEnviarMail = mock(EnviarMail.class);
		tratamientoMarcarComoFavoritas = new MarcarComoFavoritas();
		filtro = new FiltroNulo();

		pollo = new ComponenteDeReceta("pollo", 1000.0, 500.0);
		papa = new ComponenteDeReceta("papa", 500.0, 250.0);
		merluza = new ComponenteDeReceta("merluza", 750.0, 200.0);
		cebolla = new ComponenteDeReceta("cebolla", 100.0, 50.0);

		ernesto = new Usuario(66.0, 177.0, "Ernesto",
				LocalDate.of(1994, 9, 24), Rutina.INTENSIVO);
		ernesto.setGestorDeConsultas(gestor);
		ernesto.setRepositorio(repoRecetas);

		receta1Builder = new RecetaBuilder();
		receta1Builder.setNombre("Pollo con papas");
		receta1Builder.agregarIngrediente(pollo);
		receta1Builder.agregarIngrediente(papa);
		receta1Builder.setCalorias(1300.0);
		receta1Builder.setCreador(ernesto);
		receta1Builder.setSubidaPorSistema(true);
		receta1Builder.setRepositorio(repoRecetas);
		receta1 = receta1Builder.build();

		receta2Builder = new RecetaBuilder();
		receta2Builder.setNombre("filet de merluza con cebolla");
		receta2Builder.agregarIngrediente(merluza);
		receta2Builder.agregarCondimento(cebolla);
		receta2Builder.setCalorias(1300.0);
		receta2Builder.setCreador(ernesto);
		receta2Builder.setSubidaPorSistema(true);
		receta2Builder.setRepositorio(repoRecetas);
		receta2 = receta2Builder.build();

	}

	@Test
	public void seGeneraUnMailYQuedaEnEsperaParaSerEnviado() {
		ernesto.agregarTratamientoDeConsultas(tratamientoEnviarMail);
		recetasFiltradas = ernesto.filtrarRecetas(filtro);
		verify(tratamientoEnviarMail).agregarAccionARealizar(ernesto, filtro,
				recetasFiltradas);
	}

	@Test
	public void seEnvianLosMailsPendientes() {
		ernesto.agregarTratamientoDeConsultas(tratamientoEnviarMail);
		recetasFiltradas = ernesto.filtrarRecetas(filtro);
		gestor.ejecutarAcciones();
		assertFalse(gestor.hayAccionesARealizar());
	}

	@Test
	public void marcaComoFavoritasLasRecetasFiltradas() {
		ernesto.agregarTratamientoDeConsultas(tratamientoMarcarComoFavoritas);
		ernesto.filtrarRecetas(filtro);
		gestor.ejecutarAcciones();
		assertTrue(ernesto.getRecetasFavoritas().containsAll(
				Arrays.asList(receta1, receta2)));
	}

	@Test
	public void seGeneraLaTareaPendienteDeLoggearUnaConsulta() {
		// Agrego 100 recetas para que la consulta devuelva 102 filtradas
		// y asi se cree el log (ya habia 2 creadas en el before)

		for (int i = 0; i < 100; i++) {
			receta2Builder.build();
		}
		
		//Esto es feucho pero bueno :)
		recetasFiltradas = ernesto.filtrarRecetas(filtro);
		assertEquals(LogConsulta.class, ernesto.getGestorDeConsultas()
				.getAccionesARealizar().get(0).getClass());

	}
}
