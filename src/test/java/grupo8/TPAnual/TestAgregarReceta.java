package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.Builders.UsuarioBuilder;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Repositorios.RepoRecetas;

import org.junit.Before;
import org.junit.Test;

public class TestAgregarReceta {
	Usuario juan, pedro;
	Receta arrozConLechePrivada, arrozConLechePublica;
	
	ComponenteDeReceta arroz, leche, azucar, grasa;
	List<ComponenteDeReceta> ingredientes = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentos = new ArrayList<ComponenteDeReceta>();
	List<Receta> recetas = new ArrayList<Receta>();
	List<Receta> subrecetas = new ArrayList<Receta>();
	RepoRecetas repositorio = new RepoRecetas();
	RecetaBuilder arrozConLechePublicaBuilder;
	RecetaBuilder arrozConLechePrivadaBuilder;

	@Before
	public void init() {

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

	
		pedro = new UsuarioBuilder()
		.setPeso(72.2)
		.setAltura(1.81)
		.setNombre("Pedro")
		.setSexo("masculino")
		.setFechaDeNacimiento(LocalDate.of(1994, 11, 14))
		.agregarPreferenciaAlimenticia("sopa")
		.agregarPreferenciaAlimenticia("pasta")
		.agregarDisgustoAlimenticio("polenta")
		.agregarDisgustoAlimenticio("pollo")
		.setRutina(Rutina.LEVE)
		.build();


		arroz = new ComponenteDeReceta("gramos de arroz", 50.0, 100.0);
		leche = new ComponenteDeReceta("tazas de leche", 3.0, 250.0);
		azucar = new ComponenteDeReceta("gramos de azucar", 10.0, 30.0);
		
		arrozConLechePublica = new RecetaBuilder()
		.setNombre("Arroz con leche")
		.agregarIngrediente(arroz)
		.agregarIngrediente(leche)
		.agregarCondimento(azucar)
		.setCalorias(350.0)
		.setCreador(juan)
		.setSubidaPorSistema(true)
		.setRepositorio(repositorio)
		.build();
		
		arrozConLechePrivada = new RecetaBuilder()
		.setNombre("Arroz con leche")
		.agregarIngrediente(arroz)
		.agregarIngrediente(leche)
		.agregarCondimento(azucar)
		.setCalorias(350.0)
		.setCreador(juan)
		.setSubidaPorSistema(false)
		.setRepositorio(repositorio)
		.build();
	}

	@Test
	public void juanTieneUnaReceta() {
		assertTrue(juan.tenesUnaReceta(arrozConLechePrivada));
	}

	@Test
	public void esPublicaUnaRecetaPublica() {
		assertTrue(arrozConLechePublica.esPublica());
	}

	@Test
	public void seCreaUnaRecetaPublicaYSeAgregaAlRepositorio() {
		assertTrue(repositorio.tieneUnaReceta(arrozConLechePublica));
	}
	
	@Test
	public void pedroPuedeVerRecetaPublica() {
		pedro.puedeVerOModificar(arrozConLechePublica);
	}
	
	@Test
	public void pedroAgregoRecetaParaModificar() {
		int tamanio = pedro.getRecetas().size();
		pedro.modificarRecetaPublica(arrozConLechePublica);
		assertTrue(pedro.getRecetas().size() == (tamanio + 1));
		
	}
	

}
