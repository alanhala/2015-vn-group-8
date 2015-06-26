package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.exceptions.RecetaConCaloriasFueraDelRangoException;
import grupo8.TPAnual.exceptions.RecetaSinIngredientesException;
import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.CondicionesPreexistentes.Celiaco;
import grupo8.TPAnual.model.CondicionesPreexistentes.Condicion;
import grupo8.TPAnual.model.CondicionesPreexistentes.Diabetico;
import grupo8.TPAnual.model.CondicionesPreexistentes.Hipertenso;
import grupo8.TPAnual.model.CondicionesPreexistentes.Vegano;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Repositorios.RepoRecetas;

import org.junit.Before;
import org.junit.Test;

public class TestReceta {

	Receta recetaSinSubrecetas, recetaInvalida, recetaConSubreceta, caramelo,
			caldoSalado, aireCalorico;
	ComponenteDeReceta arroz, leche, azucar, grasa, sal, caldo;
	Celiaco celiaco;
	Diabetico diabetico;
	Hipertenso hipertenso;
	Vegano vegano;
	Usuario pepe;
	
	RepoRecetas repositorio;
	
	List<Condicion> condiciones = new ArrayList<Condicion>();
	List<Receta> recetas = new ArrayList<Receta>();
	
	RecetaBuilder builderRecetasSinSubrecetas;
	RecetaBuilder builderRecetasConSubreceta;
	RecetaBuilder builderCaramelo;
	RecetaBuilder builderCaldoSalado;
	RecetaBuilder builderRecetaInvalida;
	RecetaBuilder builderToneladasDeGrasa;
	
	@Before
	public void init() {
		
		repositorio = new RepoRecetas();
		
		arroz = new ComponenteDeReceta("gramos de arroz", 50.0, 100.0);
		leche = new ComponenteDeReceta("tazas de leche", 3.0, 250.0);
		azucar = new ComponenteDeReceta("azucar", 120.0, 130.0);
		grasa = new ComponenteDeReceta("cucharadas de grasa", 300.0, 7500.0);
		sal = new ComponenteDeReceta("sal", 10.0, 30.0);
		caldo = new ComponenteDeReceta("caldo", 4.0, 50.0);

		hipertenso = new Hipertenso();
		
		pepe = new Usuario(70.0, 1.70, "Pepex", "masculino", LocalDate.of(1990,
				4, 2), Arrays.asList("asado", "chivito"), Arrays.asList(),
				Arrays.asList(hipertenso), recetas, Rutina.LEVE, Arrays.asList());
		
		builderCaramelo = new RecetaBuilder();
		builderCaramelo.setNombre("Caramelo");
		builderCaramelo.agregarIngrediente(grasa);
		builderCaramelo.agregarCondimento(azucar);
		builderCaramelo.setCalorias(3000.0);
		builderCaramelo.setCreador(pepe);
		builderCaramelo.setSubidaPorSistema(true);
		builderCaramelo.setRepositorio(repositorio);
		caramelo = builderCaramelo.build();
		
		builderCaldoSalado = new RecetaBuilder();
		builderCaldoSalado.setNombre("CaldoSalado");
		builderCaldoSalado.agregarIngrediente(caldo);
		builderCaldoSalado.agregarCondimento(sal);
		builderCaldoSalado.setCalorias(700.0);
		builderCaldoSalado.setCreador(pepe);
		builderCaldoSalado.setSubidaPorSistema(true);
		builderCaldoSalado.setRepositorio(repositorio);
		caldoSalado = builderCaldoSalado.build();
		
		builderRecetasSinSubrecetas = new RecetaBuilder();
		builderRecetasSinSubrecetas.setNombre("Arroz con leche");
		builderRecetasSinSubrecetas.agregarIngrediente(arroz);
		builderRecetasSinSubrecetas.agregarIngrediente(leche);
		builderRecetasSinSubrecetas.agregarCondimento(azucar);
		builderRecetasSinSubrecetas.setCalorias(150.0);
		builderRecetasSinSubrecetas.setCreador(pepe);
		builderRecetasSinSubrecetas.setSubidaPorSistema(true);
		builderRecetasSinSubrecetas.setRepositorio(repositorio);
		recetaSinSubrecetas = builderRecetasSinSubrecetas.build();
		
		builderRecetasConSubreceta = new RecetaBuilder();
		builderRecetasConSubreceta.setNombre("Arroz con leche y caramelo");
		builderRecetasConSubreceta.agregarIngrediente(arroz);
		builderRecetasConSubreceta.agregarIngrediente(leche);
		builderRecetasConSubreceta.agregarCondimento(azucar);
		builderRecetasConSubreceta.setCalorias(4500.0);
		builderRecetasConSubreceta.setCreador(pepe);
		builderRecetasConSubreceta.setSubidaPorSistema(true);
		builderRecetasConSubreceta.setRepositorio(repositorio);
		builderRecetasConSubreceta.agregarSubreceta(caramelo);
		builderRecetasConSubreceta.agregarSubreceta(caldoSalado);
		recetaConSubreceta = builderRecetasConSubreceta.build();
	

	}

	@Test(expected = RecetaSinIngredientesException.class)
	public void recetaNoTieneIngredientes() {
		builderRecetaInvalida = new RecetaBuilder();
		builderRecetaInvalida.setNombre("Aire");
		builderRecetaInvalida.setCalorias(1.0);
		builderRecetasConSubreceta.setCreador(pepe);
		builderRecetasConSubreceta.setSubidaPorSistema(true);
		builderRecetasConSubreceta.setRepositorio(repositorio);
		recetaInvalida = builderRecetaInvalida.build();
	}

	@Test(expected = RecetaConCaloriasFueraDelRangoException.class)
	public void recetaConSubrecetaTieneMasDe5000Calorias() {
		builderToneladasDeGrasa = new RecetaBuilder();
		builderToneladasDeGrasa.setNombre("Toneladas de grasa");
		builderToneladasDeGrasa.agregarIngrediente(grasa);
		builderToneladasDeGrasa.setCalorias(7284.0);
		builderToneladasDeGrasa.setCreador(pepe);
		builderToneladasDeGrasa.setSubidaPorSistema(true);
		builderToneladasDeGrasa.setRepositorio(repositorio);
		aireCalorico = builderToneladasDeGrasa.build();
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
	
	@Test
	public void recetaConSubrecetaTieneMasDe100GrDeAzucar() {
		assertTrue(recetaConSubreceta.tieneMasDe100GramosDeAzucar());
	}
	
	@Test
	public void recetaSinSubrecetaTieneMasDe100GrDeAzucar() {
		assertTrue(recetaSinSubrecetas.tieneMasDe100GramosDeAzucar());
	}
	
	@Test
	public void recetaSinSubrecetasTieneCondicionesInadecuadas() {
		celiaco = new Celiaco();
		diabetico = new Diabetico();
		hipertenso = new Hipertenso();
		vegano = new Vegano();
		
		condiciones.add(celiaco);
		condiciones.add(diabetico);
		condiciones.add(hipertenso);
		condiciones.add(vegano);
		
		assertTrue(recetaSinSubrecetas.condicionesInadecuadas(condiciones).contains(diabetico));
		assertFalse(recetaSinSubrecetas.condicionesInadecuadas(condiciones).contains(celiaco));
		assertFalse(recetaSinSubrecetas.condicionesInadecuadas(condiciones).contains(hipertenso));
		assertFalse(recetaSinSubrecetas.condicionesInadecuadas(condiciones).contains(vegano));
	}
	
	@Test
	public void carameloCumpleCondicionesParaPepe() {
		assertTrue(caramelo.cumpleCondicionesPara(pepe));
	}
}
