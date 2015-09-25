package grupo8.TPAnual;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.Builders.UsuarioBuilder;
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
import grupo8.TPAnual.model.Repositorios.Recetario;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestCommands {

	private MailSender mailSender;
	private FiltroNulo filtro;
	private Usuario ernesto;
	private UsuarioBuilder ernestoBuilder;
	private Receta receta1, receta2;
	private RecetaBuilder receta1Builder, receta2Builder;
	private EnviarMail tratamientoEnviarMail;
	private MarcarComoFavoritas tratamientoMarcarComoFavoritas;
	private ComponenteDeReceta pollo, papa, merluza, cebolla;
	private Recetario repoRecetas;
	private GestorDeConsultas gestor;
	private List<Receta> recetasFiltradas;

	@Before
	public void setup() {
		repoRecetas = new Recetario();
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

		ernesto = new UsuarioBuilder()
		.setPeso(66.0)
		.setAltura(1.77)
		.setNombre("Ernesto")
		.setSexo("masculino")
		.setFechaDeNacimiento(LocalDate.of(1994, 9, 24))
		.setRutina(Rutina.INTENSIVO)
		.build();
		ernesto.setGestorDeConsultas(gestor);

		receta1 = new RecetaBuilder()
		.setNombre("Pollo con papas")
		.agregarIngrediente(pollo)
		.agregarIngrediente(papa)
		.setCalorias(1300.0)
		.setSubidaPorSistema(true)
		.build();
		repoRecetas.agregar(receta1);

		receta2 = new RecetaBuilder()
		.setNombre("filet de merluza con cebolla")
		.agregarIngrediente(merluza)
		.agregarCondimento(cebolla)
		.setCalorias(1300.0)
		.setSubidaPorSistema(true)
		.build();
		repoRecetas.agregar(receta2);

	}

	@Test
	public void seGeneraUnMailYQuedaEnEsperaParaSerEnviado() {
		ernesto.agregarTratamientoDeConsultas(tratamientoEnviarMail);
		recetasFiltradas = repoRecetas.filtrarRecetas(ernesto, filtro);
		verify(tratamientoEnviarMail).agregarAccionARealizar(ernesto, filtro,
				recetasFiltradas);
	}

	@Test
	public void seEnvianLosMailsPendientes() {
		ernesto.agregarTratamientoDeConsultas(tratamientoEnviarMail);
		recetasFiltradas = repoRecetas.filtrarRecetas(ernesto, filtro);
		gestor.ejecutarAcciones();
		assertFalse(gestor.hayAccionesARealizar());
	}

	@Test
	public void marcaComoFavoritasLasRecetasFiltradas() {
		ernesto.agregarTratamientoDeConsultas(tratamientoMarcarComoFavoritas);
		recetasFiltradas = repoRecetas.filtrarRecetas(ernesto, filtro);
		gestor.ejecutarAcciones();
		assertTrue(ernesto.getRecetasFavoritas().containsAll(
				Arrays.asList(receta1, receta2)));
	}

//	@Test
//	public void seGeneraLaTareaPendienteDeLoggearUnaConsulta() {
//		// Agrego 100 recetas para que la consulta devuelva 102 filtradas
//		// y asi se cree el log (ya habia 2 creadas en el before)
//
//		for (int i = 0; i < 100; i++) {
//			receta2Builder.build();
//		}
//		
//		//Esto es feucho pero bueno :)
//		recetasFiltradas = ernesto.filtrarRecetas(filtro);
//		assertEquals(LogConsulta.class, ernesto.getGestorDeConsultas()
//				.getAccionesARealizar().get(0).getClass());
//		// TODO Medio raro esto jajaj 
//		//de ultima revisen que encuentre una con ese tipo de objeto, pero ese get(0) es medio... casi hardcodeado.
//
//	}
}
