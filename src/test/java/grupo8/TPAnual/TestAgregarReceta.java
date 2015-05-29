package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.model.ComponenteDeReceta;
import grupo8.TPAnual.model.Receta;
import grupo8.TPAnual.model.RepositorioRecetas;
import grupo8.TPAnual.model.Usuario;
import grupo8.TPAnual.model.Usuario.Rutina;

import org.junit.Before;
import org.junit.Test;

public class TestAgregarReceta {
	Usuario juan;
	Receta arrozConLechePrivada, arrozConLechePublica;
	ComponenteDeReceta arroz, leche, azucar, grasa;
	List<ComponenteDeReceta> ingredientes = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentos = new ArrayList<ComponenteDeReceta>();
	List<Receta> recetas = new ArrayList<Receta>();
	List<Receta> subrecetas = new ArrayList<Receta>();

	@Before
	public void init() {

		arroz = new ComponenteDeReceta("gramos de arroz", 50.0, 100.0);
		leche = new ComponenteDeReceta("tazas de leche", 3.0, 250.0);
		azucar = new ComponenteDeReceta("gramos de azucar", 10.0, 30.0);
		ingredientes.add(arroz);
		ingredientes.add(leche);
		condimentos.add(azucar);

		juan = new Usuario(72.2, 1.81, "Juan Manuel", "masculino",
				LocalDate.of(1994, 11, 14), Arrays.asList("sopa", "pasta"),
				Arrays.asList("polenta", "pollo"), Arrays.asList(), recetas,
				Rutina.LEVE, Arrays.asList());

	}

	@Test
	public void juanTieneUnaReceta() {

		arrozConLechePrivada = new Receta("Arroz con leche", ingredientes,
				condimentos, 350.00, juan, false);
		assertTrue(juan.tenesUnaReceta(arrozConLechePrivada));
	}

	@Test
	public void seCreaUnaRecetaPublica() {
		arrozConLechePublica = new Receta("Arroz con leche", ingredientes,
				condimentos, 350.00, juan, true);
		assertTrue(arrozConLechePublica.esPublica());

	}

	@Test
	public void seCreaUnaRecetaPublicaYSeAgregaAlRepositorio() {
		arrozConLechePublica = new Receta("Arroz con leche", ingredientes,
				condimentos, 350.00, juan, true);
		assertTrue(RepositorioRecetas.tieneUnaReceta(arrozConLechePublica));
	}

}
