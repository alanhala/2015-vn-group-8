package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import grupo8.TPAnual.model.ComponenteDeReceta;
import grupo8.TPAnual.model.Receta;

import org.junit.Before;
import org.junit.Test;

public class TestRecetaValida {

	Receta receta, recetaInvalida;
	ComponenteDeReceta arroz, leche, azucar, grasa;
	List<ComponenteDeReceta> ingredientes = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentos = new ArrayList<ComponenteDeReceta>();

	@Before
	public void init() {
		arroz = new ComponenteDeReceta("gramos de arroz", 50.0, 100.0);
		leche = new ComponenteDeReceta("tazas de leche", 3.0, 250.0);
		azucar = new ComponenteDeReceta("gramos de azucar", 10.0, 30.0);
		grasa = new ComponenteDeReceta("cucharadas de grasa", 300.0, 7500.0);
		ingredientes.add(arroz);
		ingredientes.add(leche);
		condimentos.add(azucar);
		receta = new Receta("Arroz con leche", ingredientes, condimentos);
	}

	@Test
	public void recetaEsValida() {
		assertTrue(receta.esValida());
	}

	@Test(expected = Exception.class)
	public void creacionDeRecetaConMasDe5000CaloriasTiraExcepcion() {
		recetaInvalida = new Receta("grasa en balde", Arrays.asList(grasa),
				Collections.emptyList());

	}

}
