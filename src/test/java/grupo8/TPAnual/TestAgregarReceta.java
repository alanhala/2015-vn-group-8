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
	UsuarioBuilder juanBuilder, pedroBuilder;
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
		
		/* TODO Tooooodos los test que arreglaron con el builder quedan un poco m�s amigables asi:
		 Yo los cambiar�a para que queden m�s entendibles.
		 
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
		 */
	
		pedroBuilder = new UsuarioBuilder();
		pedroBuilder.setPeso(72.2);
		pedroBuilder.setAltura(1.81);
		pedroBuilder.setNombre("Pedro");
		pedroBuilder.setSexo("masculino");
		pedroBuilder.setFechaDeNacimiento(LocalDate.of(1994, 11, 14));
		pedroBuilder.agregarPreferenciaAlimenticia("sopa");
		pedroBuilder.agregarPreferenciaAlimenticia("pasta");
		pedroBuilder.agregarDisgustoAlimenticio("polenta");
		pedroBuilder.agregarDisgustoAlimenticio("pollo");
		pedroBuilder.setRutina(Rutina.LEVE);
		pedro = pedroBuilder.build();


		arroz = new ComponenteDeReceta("gramos de arroz", 50.0, 100.0);
		leche = new ComponenteDeReceta("tazas de leche", 3.0, 250.0);
		azucar = new ComponenteDeReceta("gramos de azucar", 10.0, 30.0);
		
		arrozConLechePublicaBuilder = new RecetaBuilder();
		arrozConLechePublicaBuilder.setNombre("Arroz con leche");
		arrozConLechePublicaBuilder.agregarIngrediente(arroz);
		arrozConLechePublicaBuilder.agregarIngrediente(leche);
		arrozConLechePublicaBuilder.agregarCondimento(azucar);
		arrozConLechePublicaBuilder.setCalorias(350.0);
		arrozConLechePublicaBuilder.setCreador(juan);
		arrozConLechePublicaBuilder.setSubidaPorSistema(true);
		arrozConLechePublicaBuilder.setRepositorio(repositorio);
		arrozConLechePublica = arrozConLechePublicaBuilder.build();
		
		arrozConLechePrivadaBuilder = new RecetaBuilder();
		arrozConLechePrivadaBuilder.setNombre("Arroz con leche");
		arrozConLechePrivadaBuilder.agregarIngrediente(arroz);
		arrozConLechePrivadaBuilder.agregarIngrediente(leche);
		arrozConLechePrivadaBuilder.agregarCondimento(azucar);
		arrozConLechePrivadaBuilder.setCalorias(350.0);
		arrozConLechePrivadaBuilder.setCreador(juan);
		arrozConLechePrivadaBuilder.setSubidaPorSistema(false);
		arrozConLechePrivadaBuilder.setRepositorio(repositorio);
		arrozConLechePrivada = arrozConLechePrivadaBuilder.build();
		
		//TODO hasta si miran el commit, el codigo se les recontra agrad� por esto. 
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
