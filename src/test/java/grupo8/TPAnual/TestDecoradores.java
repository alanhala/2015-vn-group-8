package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.model.CondicionesPreexistentes.Condicion;
import grupo8.TPAnual.model.CondicionesPreexistentes.Hipertenso;
import grupo8.TPAnual.model.CondicionesPreexistentes.Vegano;
import grupo8.TPAnual.model.Decorators.ComparatorRecetasAlfabeticamente;
import grupo8.TPAnual.model.Decorators.ComparatorRecetasPorCalorias;
import grupo8.TPAnual.model.Decorators.DecoradorFiltroCondicionesPreexistentes;
import grupo8.TPAnual.model.Decorators.DecoradorFiltroDisgusto;
import grupo8.TPAnual.model.Decorators.DecoradorFiltroIngredientesCaros;
import grupo8.TPAnual.model.Decorators.DecoradorFiltroSobrepeso;
import grupo8.TPAnual.model.Decorators.DecoradorProcesarOrdenamiento;
import grupo8.TPAnual.model.Decorators.DecoradorProcesarPares;
import grupo8.TPAnual.model.Decorators.DecoradorProcesarPimerosDiez;
import grupo8.TPAnual.model.Decorators.FiltroNulo;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Grupo;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Repositorios.RepoRecetas;

import org.junit.Before;
import org.junit.Test;

public class TestDecoradores {

	Usuario roberto, sistema;
	Receta recetaDeRoberto1, recetaDeRoberto2, recetaDeRoberto3,
			recetaDeRoberto4, recetaDeRoberto5, recetaDeRoberto6,
			recetaPublica1, recetaPublica2, recetaPublica3, recetaPublica4,
			recetaPublica5;

	ComponenteDeReceta arroz, leche, azucar, grasa, sal, caldo, aji, cebolla,
			chivito, chocolate, harina, dulceDeLeche, pollo, papas, lentejas,
			chorizoColorado, salsaDeTomate, fideos, manteca, mondongo, tabasco,
			porotos, pimenton;

	Hipertenso hipertenso;
	Vegano vegano;

	List<Condicion> condiciones = new ArrayList<Condicion>();
	List<ComponenteDeReceta> ingredientesRoberto1 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosRoberto1 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesRoberto2 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosRoberto2 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesRoberto3 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosRoberto3 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesRoberto4 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosRoberto4 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesRoberto5 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosRoberto5 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesRoberto6 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosRoberto6 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesPublica1 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosPublica1 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesPublica2 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosPublica2 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesPublica3 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosPublica3 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesPublica4 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosPublica4 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesPublica5 = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosPublica5 = new ArrayList<ComponenteDeReceta>();

	@Before
	public void init() {

		// Inicializo los usuarios
		hipertenso = new Hipertenso();
		vegano = new Vegano();

		condiciones.add(hipertenso);
		condiciones.add(vegano);

		roberto = new Usuario(140.0, 1.80, "Roberto", "masculino",
				LocalDate.of(1994, 9, 24), Arrays.asList("queso", "pescado",
						"frutas"), Arrays.asList("polenta", "fideos"),
				condiciones, new ArrayList<Receta>(), Rutina.INTENSIVO,
				new ArrayList<Grupo>());

		// Inicializo las recetas

		arroz = new ComponenteDeReceta("gramos de arroz", 50.0, 100.0);
		leche = new ComponenteDeReceta("tazas de leche", 3.0, 250.0);
		azucar = new ComponenteDeReceta("azucar", 120.0, 130.0);
		grasa = new ComponenteDeReceta("cucharadas de grasa", 300.0, 7500.0);
		sal = new ComponenteDeReceta("sal", 10.0, 30.0);
		caldo = new ComponenteDeReceta("caldo", 4.0, 50.0);
		aji = new ComponenteDeReceta("aji", 5.0, 150.0);
		cebolla = new ComponenteDeReceta("cebolla", 9.0, 190.0);
		chivito = new ComponenteDeReceta("chivito", 1.0, 4900.0);
		chocolate = new ComponenteDeReceta("chocolate", 10.0, 190.0);
		harina = new ComponenteDeReceta("harina", 90.0, 40.0);
		dulceDeLeche = new ComponenteDeReceta("dulce de leche", 8.0, 300.0);
		pollo = new ComponenteDeReceta("pollo", 1.0, 900.0);
		papas = new ComponenteDeReceta("papas", 5.0, 400.0);
		lentejas = new ComponenteDeReceta("lentejas", 50.0, 20.0);
		chorizoColorado = new ComponenteDeReceta("chorizo colorado", 1.0, 150.0);
		salsaDeTomate = new ComponenteDeReceta("salsa de tomate", 40.0, 120.0);
		fideos = new ComponenteDeReceta("fideos", 100.0, 500.0);
		manteca = new ComponenteDeReceta("manteca", 40.0, 300.0);
		mondongo = new ComponenteDeReceta("mondongo", 500.0, 1250.0);
		tabasco = new ComponenteDeReceta("tabasco", 25.0, 80.0);
		porotos = new ComponenteDeReceta("porotos", 100.0, 2500.0);
		pimenton = new ComponenteDeReceta("pimenton", 20.0, 2500.0);

		ingredientesRoberto1.add(arroz);
		ingredientesRoberto1.add(leche);
		condimentosRoberto1.add(azucar);

		ingredientesRoberto2.add(grasa);
		condimentosRoberto2.add(azucar);

		ingredientesRoberto3.add(chivito);
		condimentosRoberto3.add(cebolla);

		ingredientesRoberto4.add(caldo);
		condimentosRoberto4.add(aji);
		condimentosRoberto4.add(sal);

		ingredientesRoberto5.add(chivito);
		condimentosRoberto5.add(sal);

		ingredientesRoberto6.add(harina);
		ingredientesRoberto6.add(chocolate);
		condimentosRoberto6.add(dulceDeLeche);

		ingredientesPublica1.add(pollo);
		ingredientesPublica1.add(papas);
		condimentosPublica1.add(aji);
		condimentosPublica1.add(sal);

		ingredientesPublica2.add(lentejas);
		ingredientesPublica2.add(chorizoColorado);
		condimentosPublica2.add(salsaDeTomate);

		ingredientesPublica3.add(fideos);
		condimentosPublica3.add(manteca);

		ingredientesPublica4.add(mondongo);
		condimentosPublica4.add(tabasco);

		ingredientesPublica5.add(porotos);
		condimentosPublica5.add(pimenton);

		recetaDeRoberto1 = new Receta("arroz con leche", ingredientesRoberto1,
				condimentosRoberto1, 30.0, roberto, false);

		recetaDeRoberto2 = new Receta("grasa con azucar", ingredientesRoberto2,
				condimentosRoberto2, 70.0, roberto, false);

		recetaDeRoberto3 = new Receta("chivito con cebolla",
				ingredientesRoberto3, condimentosRoberto3, 40.0, roberto, false);

		recetaDeRoberto4 = new Receta("caldo salado", ingredientesRoberto4,
				condimentosRoberto4, 250.0, roberto, false);

		recetaDeRoberto5 = new Receta("chivito salado", ingredientesRoberto5,
				condimentosRoberto5, 550.0, roberto, false);

		recetaDeRoberto6 = new Receta("torta de chocolate",
				ingredientesRoberto6, condimentosRoberto6, 900.0, roberto,
				false);

		recetaPublica1 = new Receta("pollo al horno con papas",
				ingredientesPublica1, condimentosPublica1, 1500.0, roberto,
				true);

		recetaPublica2 = new Receta("guiso de lentejas", ingredientesPublica2,
				condimentosPublica2, 600.0, roberto, true);

		recetaPublica3 = new Receta("fideos con manteca", ingredientesPublica3,
				condimentosPublica3, 500.0, roberto, true);

		recetaPublica4 = new Receta("mondongo con tabasco",
				ingredientesPublica4, condimentosPublica4, 1330.0, roberto,
				true);

		recetaPublica5 = new Receta("porotos con pimenton",
				ingredientesPublica5, condimentosPublica5, 5000.0, roberto,
				true);

		// Inicializo el repo

		roberto.agregarRepositorio(RepoRecetas.getInstance());
	}

	@Test
	public void filtroRecetasParaUsuariosConSobrepeso() {
		DecoradorFiltroSobrepeso procesarRecetasParaUsuariosConSobrepeso = new DecoradorFiltroSobrepeso(
				new FiltroNulo());

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(procesarRecetasParaUsuariosConSobrepeso);

		assertTrue(recetasFiltradas.containsAll(Arrays.asList(recetaDeRoberto1,
				recetaDeRoberto2, recetaDeRoberto3, recetaDeRoberto4)));
	}

	@Test
	public void filtroRecetasParesParaUsuariosConSobrepeso() {
		DecoradorProcesarPares procesarRecetasParesParaUsuariosConSobrepeso = new DecoradorProcesarPares(
				new DecoradorFiltroSobrepeso(new FiltroNulo()));
		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(procesarRecetasParesParaUsuariosConSobrepeso);

		assertTrue(recetasFiltradas.containsAll(Arrays.asList(recetaDeRoberto2, recetaDeRoberto4
				)));
	}

	@Test
	public void filtroDeRecetasPorCondicionesPreexistentesFiltraRecetasRoberto1Roberto2Roberto6YRecetaPublica2() {
		DecoradorFiltroCondicionesPreexistentes filtroDeRecetasPorCondicionesPreexistentes = new DecoradorFiltroCondicionesPreexistentes(
				new FiltroNulo());
		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroDeRecetasPorCondicionesPreexistentes);
		assertTrue(recetasFiltradas.containsAll(Arrays.asList(recetaDeRoberto1,
				recetaDeRoberto2, recetaDeRoberto6, recetaPublica2)));
	}

	@Test
	public void filtroDeRecetasConIngredientesCarosFiltraReceta1Receta2Receta4DeRobertoYRecetasPublicas1Y2() {
		List<String> ingredientesCaros = new ArrayList<String>();

		ingredientesCaros.addAll(Arrays.asList("chivito", "harina"));

		DecoradorFiltroIngredientesCaros filtroDeRecetasPorIngredientesCaros = new DecoradorFiltroIngredientesCaros(
				new FiltroNulo(), ingredientesCaros);

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroDeRecetasPorIngredientesCaros);

		assertTrue(recetasFiltradas.containsAll(Arrays.asList(recetaDeRoberto1,
				recetaDeRoberto2, recetaDeRoberto4, recetaPublica1,
				recetaPublica2)));
	}

	@Test
	public void filtroDeRecetasPorDisgustosAlimenticionsFiltraTodasMenosRecetaPublica3() {
		DecoradorFiltroDisgusto filtroDeRecetasPorDisgustosAlimenticios = new DecoradorFiltroDisgusto(
				new FiltroNulo());

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroDeRecetasPorDisgustosAlimenticios);

		assertTrue(recetasFiltradas.containsAll(Arrays.asList(recetaDeRoberto1,
				recetaDeRoberto2, recetaDeRoberto3, recetaDeRoberto4,
				recetaDeRoberto5, recetaDeRoberto6, recetaPublica1,
				recetaPublica2)));
	}

	@Test
	public void filtroPrimeras10RecetasDeRoberto() {

		DecoradorProcesarPimerosDiez procesarPrimerasDiezRecetas = new DecoradorProcesarPimerosDiez(
				new FiltroNulo());

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(procesarPrimerasDiezRecetas);

		int posicionDeLaUltimaReceta = roberto.getRecetasAccesibles().size() - 1;

		assertTrue((!recetasFiltradas.contains(roberto.getRecetasAccesibles()
				.get(posicionDeLaUltimaReceta)))
				&& (recetasFiltradas.size() == 10));

	}

	// Orden esperado: Recetas de roberto 1,2,4, recetas publicas 3, 2, 4, 1, 5
	@Test
	public void filtroDeRecetasConIngredientesCarosOrdenadasPorCalorias() {
		List<String> ingredientesCaros = new ArrayList<String>();

		ingredientesCaros.addAll(Arrays.asList("chivito", "harina"));

		DecoradorProcesarOrdenamiento filtroDeRecetasPorIngredientesCarosOrdenadasPorCalorias = new DecoradorProcesarOrdenamiento(
				new DecoradorFiltroIngredientesCaros(new FiltroNulo(),
						ingredientesCaros), new ComparatorRecetasPorCalorias());

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroDeRecetasPorIngredientesCarosOrdenadasPorCalorias);

		assertTrue(recetasFiltradas.indexOf(recetaDeRoberto1) == 0
				&& recetasFiltradas.indexOf(recetaDeRoberto2) == 1
				&& recetasFiltradas.indexOf(recetaDeRoberto4) == 2
				&& recetasFiltradas.indexOf(recetaPublica1) == 6
				&& recetasFiltradas.indexOf(recetaPublica2) == 4
				&& recetasFiltradas.indexOf(recetaPublica3) == 3
				&& recetasFiltradas.indexOf(recetaPublica4) == 5
				&& recetasFiltradas.indexOf(recetaPublica5) == 7);
	}

	@Test
	public void filtroRecetasDeRobertoPorDisgustosAlimenticiosYSobrepeso() {

		DecoradorFiltroDisgusto filtroPorDisgustoYSobrepeso = new DecoradorFiltroDisgusto(
				new DecoradorFiltroSobrepeso(new FiltroNulo()));

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroPorDisgustoYSobrepeso);

		assertTrue((recetasFiltradas.containsAll(Arrays.asList(
				recetaDeRoberto1, recetaDeRoberto2, recetaDeRoberto3,
				recetaDeRoberto4)) && (recetasFiltradas.size() == 4)));
	}

	// Orden esperado: Recetas de roberto 1,4,3,2
	@Test
	public void filtroRecetasDeRobertoPorDisgustosAlimenticiosYSobrepesoOrdenadoAlfabeticamente() {

		DecoradorProcesarOrdenamiento filtroPorDisgustoYSobrepesoOrdenadoAlfabeticamente = new DecoradorProcesarOrdenamiento(
				new DecoradorFiltroDisgusto(new DecoradorFiltroSobrepeso(
						new FiltroNulo())),
				new ComparatorRecetasAlfabeticamente());

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroPorDisgustoYSobrepesoOrdenadoAlfabeticamente);

		assertTrue(recetasFiltradas.indexOf(recetaDeRoberto1) == 0
				&& recetasFiltradas.indexOf(recetaDeRoberto2) == 3
				&& recetasFiltradas.indexOf(recetaDeRoberto4) == 1
				&& recetasFiltradas.indexOf(recetaDeRoberto3) == 2);
	}

}
