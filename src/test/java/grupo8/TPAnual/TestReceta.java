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

public class TestReceta {

	Receta recetaSinSubrecetas, recetaInvalida, recetaConSubreceta, caramelo,
			caldoSalado;
	ComponenteDeReceta arroz, leche, azucar, grasa, sal, caldo;
	List<ComponenteDeReceta> ingredientes = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentos = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesDeCaramelo = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosDeCaramelo = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesDeCaldoSalado = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosDeCaldoSalado = new ArrayList<ComponenteDeReceta>();

	@Before
	public void init() {

		arroz = new ComponenteDeReceta("gramos de arroz", 50.0, 100.0);
		leche = new ComponenteDeReceta("tazas de leche", 3.0, 250.0);
		azucar = new ComponenteDeReceta("gramos de azucar", 10.0, 30.0);
		grasa = new ComponenteDeReceta("cucharadas de grasa", 300.0, 7500.0);
		sal = new ComponenteDeReceta("sal", 10.0, 30.0);
		caldo = new ComponenteDeReceta("caldo", 4.0, 50.0);

		ingredientes.add(arroz);
		ingredientes.add(leche);
		condimentos.add(azucar);
		ingredientesDeCaramelo.add(grasa);
		condimentosDeCaramelo.add(azucar);
		ingredientesDeCaldoSalado.add(caldo);
		condimentosDeCaldoSalado.add(sal);

		recetaSinSubrecetas = new Receta("Arroz con leche", ingredientes,
				condimentos, 150.0);
		recetaConSubreceta = new Receta("Arroz con leche y caramelo",
				ingredientes, condimentos, 5001.0);
		caramelo = new Receta("caramelo", ingredientesDeCaramelo,
				condimentosDeCaramelo, 3000.0);
		caldoSalado = new Receta("caldoSalado", ingredientesDeCaldoSalado,
				condimentosDeCaldoSalado, 700.0);

		recetaConSubreceta.agregarSubreceta(caramelo);
		recetaConSubreceta.agregarSubreceta(caldoSalado);

	}

	@Test
	public void recetaEsValida() {
		assertTrue(recetaSinSubrecetas.esValida());
	}

	@Test
	public void recetaTieneAlMenosUnIngrediente() {
		assertTrue(recetaSinSubrecetas.tieneAlMenosUnIngrediente());
	}

	@Test
	public void recetaNoTieneIngredientes() {
		recetaInvalida = new Receta("Aire", Collections.emptyList(),
				Collections.emptyList(), 1.0);
		assertFalse(recetaInvalida.tieneAlMenosUnIngrediente());
	}

	@Test
	public void recetaSinSubrecetasTieneCaloriasEntre10y5000() {
		assertTrue(recetaSinSubrecetas.tieneCaloriasEntre(10, 5000));
	}

	@Test
	public void recetaConSubrecetaTieneMasDe5000Calorias() {
		assertFalse(recetaConSubreceta.tieneCaloriasEntre(10, 5000));
	}

	@Test
	public void recetaConSubrecetaTieneSalOCaldo() {
		assertTrue(recetaConSubreceta.tieneSalOCaldo());
	}

	@Test
	public void recetaSinSubrecetaNoTieneSalOCaldo() {
		assertFalse(recetaSinSubrecetas.tieneSalOCaldo());
	}

	@Test
	public void recetaConSubrecetaTieneArroz() {
		assertTrue(recetaConSubreceta.tieneEstosIngredientes(Arrays
				.asList("gramos de arroz")));
	}

	@Test
	public void recetaSinSubrecetaNoTieneCaldo() {
		assertFalse(recetaSinSubrecetas.tieneEstosIngredientes(Arrays
				.asList("caldo")));
	}
}
