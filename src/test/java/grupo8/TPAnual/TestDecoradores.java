package grupo8.TPAnual;

import static org.junit.Assert.assertTrue;
import grupo8.TPAnual.model.Builders.RecetaBuilder;
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
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Repositorios.RepoRecetas;
import grupo8.TPAnual.model.Repositorios.RepoUsuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestDecoradores {

	Usuario roberto, sistema;
	Receta arrozConLeche, grasaConAzucar, chivitoConCebolla,
			caldoSalado, chivitoSalado, tortaDeChocolate,
			polloAlHornoConPapas, guisoDeLentejas, fideosConManteca, mondongoConTabasco,
			porotosConPimenton;

	RecetaBuilder arrozConLecheBuilder, grasaConAzucarBuilder,
		chivitoConCebollaBuilder, caldoSaladoBuilder,
		chivitoSaladoBuilder, tortaDeChocolateBuilder,
		polloAlHornoConPapasBuilder, guisoDeLentejasBuilder,
		fideosConMantecaBuilder, mondongoConTabascoBuilder,
		porotosConPimentonBuilder;
	
	ComponenteDeReceta arroz, leche, azucar, grasa, sal, caldo, aji, cebolla,
			chivito, chocolate, harina, dulceDeLeche, pollo, papas, lentejas,
			chorizoColorado, salsaDeTomate, fideos, manteca, mondongo, tabasco,
			porotos, pimenton;
	
	Hipertenso hipertenso;
	Vegano vegano;

	List<Condicion> condiciones = new ArrayList<Condicion>();

	@Before
	public void init() {

		hipertenso = new Hipertenso();
		vegano = new Vegano();

		condiciones.add(hipertenso);
		condiciones.add(vegano);

		roberto = new Usuario(140.0, 1.80, "Roberto", "masculino",
				LocalDate.of(1994, 9, 24), Arrays.asList("queso", "pescado",
						"frutas"), Arrays.asList("polenta", "fideos"),
				condiciones, new ArrayList<Receta>(), Rutina.INTENSIVO,
				new ArrayList<Grupo>());

		RepoUsuarios repoUsuarios = new RepoUsuarios();
		repoUsuarios.add(roberto);
		
		RepoRecetas repositorio = new RepoRecetas();

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

		arrozConLecheBuilder = new RecetaBuilder();
		arrozConLecheBuilder.setNombre("Arroz con leche");
		arrozConLecheBuilder.agregarIngrediente(arroz);
		arrozConLecheBuilder.agregarIngrediente(leche);
		arrozConLecheBuilder.agregarCondimento(azucar);
		arrozConLecheBuilder.setCalorias(30.0);
		arrozConLecheBuilder.setCreador(roberto);
		arrozConLecheBuilder.setSubidaPorSistema(false);
		arrozConLecheBuilder.setRepositorio(repositorio);
		arrozConLeche = arrozConLecheBuilder.build();
		
		grasaConAzucarBuilder = new RecetaBuilder();
		grasaConAzucarBuilder.setNombre("Grasa con azucar");
		grasaConAzucarBuilder.agregarIngrediente(grasa);
		grasaConAzucarBuilder.agregarCondimento(azucar);
		grasaConAzucarBuilder.setCalorias(70.0);
		grasaConAzucarBuilder.setCreador(roberto);
		grasaConAzucarBuilder.setSubidaPorSistema(false);
		grasaConAzucarBuilder.setRepositorio(repositorio);
		grasaConAzucar = grasaConAzucarBuilder.build();
		
		chivitoConCebollaBuilder = new RecetaBuilder();
		chivitoConCebollaBuilder.setNombre("Chivito con cebolla");
		chivitoConCebollaBuilder.agregarIngrediente(chivito);
		chivitoConCebollaBuilder.agregarCondimento(cebolla);
		chivitoConCebollaBuilder.setCalorias(40.0);
		chivitoConCebollaBuilder.setCreador(roberto);
		chivitoConCebollaBuilder.setSubidaPorSistema(false);
		chivitoConCebollaBuilder.setRepositorio(repositorio);
		chivitoConCebolla = chivitoConCebollaBuilder.build();
		
		caldoSaladoBuilder = new RecetaBuilder();
		caldoSaladoBuilder.setNombre("Caldo salado");
		caldoSaladoBuilder.agregarIngrediente(caldo);
		caldoSaladoBuilder.agregarCondimento(aji);
		caldoSaladoBuilder.agregarCondimento(sal);
		caldoSaladoBuilder.setCalorias(250.0);
		caldoSaladoBuilder.setCreador(roberto);
		caldoSaladoBuilder.setSubidaPorSistema(false);
		caldoSaladoBuilder.setRepositorio(repositorio);
		caldoSalado = caldoSaladoBuilder.build();
		
		chivitoSaladoBuilder = new RecetaBuilder();
		chivitoSaladoBuilder.setNombre("Chivito salado");
		chivitoSaladoBuilder.agregarIngrediente(chivito);
		chivitoSaladoBuilder.agregarCondimento(sal);
		chivitoSaladoBuilder.setCalorias(550.0);
		chivitoSaladoBuilder.setCreador(roberto);
		chivitoSaladoBuilder.setSubidaPorSistema(false);
		chivitoSaladoBuilder.setRepositorio(repositorio);
		chivitoSalado = chivitoSaladoBuilder.build();
		
		tortaDeChocolateBuilder = new RecetaBuilder();
		tortaDeChocolateBuilder.setNombre("Torta de chocolate");
		tortaDeChocolateBuilder.agregarIngrediente(harina);
		tortaDeChocolateBuilder.agregarIngrediente(chocolate);
		tortaDeChocolateBuilder.agregarCondimento(dulceDeLeche);
		tortaDeChocolateBuilder.setCalorias(900.0);
		tortaDeChocolateBuilder.setCreador(roberto);
		tortaDeChocolateBuilder.setSubidaPorSistema(false);
		tortaDeChocolateBuilder.setRepositorio(repositorio);
		tortaDeChocolate = tortaDeChocolateBuilder.build();
		
		polloAlHornoConPapasBuilder = new RecetaBuilder();
		polloAlHornoConPapasBuilder.setNombre("Pollo al horno con papas");
		polloAlHornoConPapasBuilder.agregarIngrediente(pollo);
		polloAlHornoConPapasBuilder.agregarIngrediente(papas);
		polloAlHornoConPapasBuilder.agregarCondimento(aji);
		polloAlHornoConPapasBuilder.agregarCondimento(sal);
		polloAlHornoConPapasBuilder.setCalorias(1500.0);
		polloAlHornoConPapasBuilder.setCreador(roberto);
		polloAlHornoConPapasBuilder.setSubidaPorSistema(true);
		polloAlHornoConPapasBuilder.setRepositorio(repositorio);
		polloAlHornoConPapas = polloAlHornoConPapasBuilder.build();
		
		guisoDeLentejasBuilder = new RecetaBuilder();
		guisoDeLentejasBuilder.setNombre("Guiso de lentejas");
		guisoDeLentejasBuilder.agregarIngrediente(lentejas);
		guisoDeLentejasBuilder.agregarIngrediente(chorizoColorado);
		guisoDeLentejasBuilder.agregarCondimento(salsaDeTomate);
		guisoDeLentejasBuilder.setCalorias(600.0);
		guisoDeLentejasBuilder.setCreador(roberto);
		guisoDeLentejasBuilder.setSubidaPorSistema(true);
		guisoDeLentejasBuilder.setRepositorio(repositorio);
		guisoDeLentejas = guisoDeLentejasBuilder.build();
		
		fideosConMantecaBuilder = new RecetaBuilder();
		fideosConMantecaBuilder.setNombre("Fideos con manteca");
		fideosConMantecaBuilder.agregarIngrediente(fideos);
		fideosConMantecaBuilder.agregarCondimento(manteca);
		fideosConMantecaBuilder.setCalorias(500.0);
		fideosConMantecaBuilder.setCreador(roberto);
		fideosConMantecaBuilder.setSubidaPorSistema(true);
		fideosConMantecaBuilder.setRepositorio(repositorio);
		fideosConManteca = fideosConMantecaBuilder.build();
		
		mondongoConTabascoBuilder = new RecetaBuilder();
		mondongoConTabascoBuilder.setNombre("Mondongo con tabasco");
		mondongoConTabascoBuilder.agregarIngrediente(mondongo);
		mondongoConTabascoBuilder.agregarCondimento(tabasco);
		mondongoConTabascoBuilder.setCalorias(1330.0);
		mondongoConTabascoBuilder.setCreador(roberto);
		mondongoConTabascoBuilder.setSubidaPorSistema(true);
		mondongoConTabascoBuilder.setRepositorio(repositorio);
		mondongoConTabasco = mondongoConTabascoBuilder.build();
		
		porotosConPimentonBuilder = new RecetaBuilder();
		porotosConPimentonBuilder.setNombre("Porotos con pimenton");
		porotosConPimentonBuilder.agregarIngrediente(porotos);
		porotosConPimentonBuilder.agregarCondimento(pimenton);
		porotosConPimentonBuilder.setCalorias(4999.9);
		porotosConPimentonBuilder.setCreador(roberto);
		porotosConPimentonBuilder.setSubidaPorSistema(true);
		porotosConPimentonBuilder.setRepositorio(repositorio);
		porotosConPimenton = porotosConPimentonBuilder.build();
		
		roberto.agregarRepositorio(repositorio);
	}

	@Test
	public void filtroRecetasParaUsuariosConSobrepeso() {
		DecoradorFiltroSobrepeso procesarRecetasParaUsuariosConSobrepeso = new DecoradorFiltroSobrepeso(
				new FiltroNulo());

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(procesarRecetasParaUsuariosConSobrepeso);

		assertTrue(recetasFiltradas.containsAll(Arrays.asList(arrozConLeche,
				grasaConAzucar, chivitoConCebolla, caldoSalado)));
	}

	public void filtroRecetasParesParaUsuariosConSobrepeso() {
		DecoradorProcesarPares procesarRecetasParesParaUsuariosConSobrepeso = new DecoradorProcesarPares(
				new DecoradorFiltroSobrepeso(new FiltroNulo()));
		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(procesarRecetasParesParaUsuariosConSobrepeso);

		assertTrue(recetasFiltradas.containsAll(Arrays.asList(grasaConAzucar,
				caldoSalado)));
	}

	@Test
	public void filtroDeRecetasPorCondicionesPreexistentesFiltraRecetasRoberto1Roberto2Roberto6YRecetaPublica2() {
		DecoradorFiltroCondicionesPreexistentes filtroDeRecetasPorCondicionesPreexistentes = new DecoradorFiltroCondicionesPreexistentes(
				new FiltroNulo());
		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroDeRecetasPorCondicionesPreexistentes);
		assertTrue(recetasFiltradas.containsAll(Arrays.asList(arrozConLeche,
				grasaConAzucar, tortaDeChocolate, guisoDeLentejas)));
	}

	@Test
	public void filtroDeRecetasConIngredientesCarosFiltraReceta1Receta2Receta4DeRobertoYRecetasPublicas1Y2() {
		List<String> ingredientesCaros = new ArrayList<String>();

		ingredientesCaros.addAll(Arrays.asList("chivito", "harina"));

		DecoradorFiltroIngredientesCaros filtroDeRecetasPorIngredientesCaros = new DecoradorFiltroIngredientesCaros(
				new FiltroNulo(), ingredientesCaros);

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroDeRecetasPorIngredientesCaros);

		assertTrue(recetasFiltradas.containsAll(Arrays.asList(arrozConLeche,
				grasaConAzucar, caldoSalado, polloAlHornoConPapas,
				guisoDeLentejas)));
	}

	@Test
	public void filtroDeRecetasPorDisgustosAlimenticionsFiltraTodasMenosRecetaPublica3() {
		DecoradorFiltroDisgusto filtroDeRecetasPorDisgustosAlimenticios = new DecoradorFiltroDisgusto(
				new FiltroNulo());

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroDeRecetasPorDisgustosAlimenticios);

		assertTrue(recetasFiltradas.containsAll(Arrays.asList(arrozConLeche,
				grasaConAzucar, chivitoConCebolla, caldoSalado,
				chivitoSalado, tortaDeChocolate, polloAlHornoConPapas,
				guisoDeLentejas)));
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
	//FIXME este es el único test que no anda (por assertion)
	@Test
	public void filtroDeRecetasConIngredientesCarosOrdenadasPorCalorias() {
		List<String> ingredientesCaros = new ArrayList<String>();

		ingredientesCaros.addAll(Arrays.asList("chivito", "harina"));

		DecoradorProcesarOrdenamiento filtroDeRecetasPorIngredientesCarosOrdenadasPorCalorias = new DecoradorProcesarOrdenamiento(
				new DecoradorFiltroIngredientesCaros(new FiltroNulo(),
						ingredientesCaros), new ComparatorRecetasPorCalorias());

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroDeRecetasPorIngredientesCarosOrdenadasPorCalorias);

		assertTrue(recetasFiltradas.indexOf(arrozConLeche) == 0
				&& recetasFiltradas.indexOf(grasaConAzucar) == 1
				&& recetasFiltradas.indexOf(caldoSalado) == 2
				&& recetasFiltradas.indexOf(polloAlHornoConPapas) == 6
				&& recetasFiltradas.indexOf(guisoDeLentejas) == 4
				&& recetasFiltradas.indexOf(fideosConManteca) == 3
				&& recetasFiltradas.indexOf(mondongoConTabasco) == 5
				&& recetasFiltradas.indexOf(porotosConPimenton) == 7);
		
	}

	@Test
	public void filtroRecetasDeRobertoPorDisgustosAlimenticiosYSobrepeso() {

		DecoradorFiltroDisgusto filtroPorDisgustoYSobrepeso = new DecoradorFiltroDisgusto(
				new DecoradorFiltroSobrepeso(new FiltroNulo()));

		List<Receta> recetasFiltradas = roberto
				.filtrarRecetas(filtroPorDisgustoYSobrepeso);

		assertTrue((recetasFiltradas.containsAll(Arrays.asList(
				arrozConLeche, grasaConAzucar, chivitoConCebolla,
				caldoSalado)) && (recetasFiltradas.size() == 4)));
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

		assertTrue(recetasFiltradas.indexOf(arrozConLeche) == 0
				&& recetasFiltradas.indexOf(grasaConAzucar) == 3
				&& recetasFiltradas.indexOf(caldoSalado) == 1
				&& recetasFiltradas.indexOf(chivitoConCebolla) == 2);
	}

}
