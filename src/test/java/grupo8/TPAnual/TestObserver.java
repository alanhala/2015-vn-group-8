package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import grupo8.TPAnual.model.Monitores.GestorDeConsultas;
import grupo8.TPAnual.model.Monitores.MonitorConsultasDeVeganosPorRecetasDificiles;
import grupo8.TPAnual.model.Monitores.MonitorConsultasPorHora;
import grupo8.TPAnual.model.Monitores.MonitorRecetasMasConsultadas;
import grupo8.TPAnual.model.Monitores.MonitorRecetasSegunSexo;
import grupo8.TPAnual.model.Repositorios.RepoRecetas;
import grupo8.TPAnual.model.Repositorios.RepoUsuarios;
import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.Builders.UsuarioBuilder;
import grupo8.TPAnual.model.CondicionesPreexistentes.Condicion;
import grupo8.TPAnual.model.CondicionesPreexistentes.Vegano;
import grupo8.TPAnual.model.Decorators.FiltroNulo;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Dificultad;
import grupo8.TPAnual.model.Dominio.Grupo;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Dominio.Usuario;

import org.junit.Before;
import org.junit.Test;


public class TestObserver {

	MonitorConsultasDeVeganosPorRecetasDificiles consultasDeVeganosPorRecetasDificiles;
	MonitorConsultasPorHora consultasPorHora;
	MonitorRecetasMasConsultadas recetasMasConsultadas;
	MonitorRecetasSegunSexo recetasSegunSexo;
	Usuario veganoVerdulero, veganaFrutera, adictoAlCafe;
	UsuarioBuilder veganoVerduleroBuilder, veganaFruteraBuilder, adictoAlCafeBuilder;
	Vegano vegano;
	RepoRecetas repoRecetas;
	GestorDeConsultas gestorDeConsultas = new GestorDeConsultas();
	
	Receta caramelo, caldoSalado, cafeConLeche;
	ComponenteDeReceta arroz, leche, azucar, grasa, sal, caldo, cafe;
	
	List<ComponenteDeReceta> ingredientes = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentos = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesDeCaramelo = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosDeCaramelo = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesDeCaldoSalado = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosDeCaldoSalado = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesDeCafeConLeche = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosDeCafeConLeche = new ArrayList<ComponenteDeReceta>();
	List<Condicion> condiciones = new ArrayList<Condicion>();
	
	@Before
	public void setup() {
		
		repoRecetas = new RepoRecetas();
		
		consultasDeVeganosPorRecetasDificiles = new MonitorConsultasDeVeganosPorRecetasDificiles();
		consultasPorHora = new MonitorConsultasPorHora();
		recetasMasConsultadas = new MonitorRecetasMasConsultadas();
		recetasSegunSexo = new MonitorRecetasSegunSexo();
		
		gestorDeConsultas.agregarMonitor(consultasDeVeganosPorRecetasDificiles);
		gestorDeConsultas.agregarMonitor(consultasPorHora);
		gestorDeConsultas.agregarMonitor(recetasMasConsultadas);
		gestorDeConsultas.agregarMonitor(recetasSegunSexo);	
		
		arroz = new ComponenteDeReceta("gramos de arroz", 50.0, 100.0);
		leche = new ComponenteDeReceta("tazas de leche", 3.0, 250.0);
		azucar = new ComponenteDeReceta("azucar", 120.0, 130.0);
		grasa = new ComponenteDeReceta("cucharadas de grasa", 300.0, 7500.0);
		sal = new ComponenteDeReceta("sal", 10.0, 30.0);
		caldo = new ComponenteDeReceta("caldo", 4.0, 50.0);
		cafe = new ComponenteDeReceta("cafe", 3.0, 20.0);

		ingredientes.add(arroz);
		ingredientes.add(leche);
		condimentos.add(azucar);
		ingredientesDeCaramelo.add(grasa);
		condimentosDeCaramelo.add(azucar);
		ingredientesDeCaldoSalado.add(caldo);
		condimentosDeCaldoSalado.add(sal);
		
		ingredientesDeCafeConLeche.add(cafe);
		ingredientesDeCafeConLeche.add(leche);
		condimentosDeCafeConLeche.add(azucar);
		
	 	//public Receta(String nombre, List<ComponenteDeReceta> ingredientes,
			//List<ComponenteDeReceta> condimentos, Double calorias,
			//String preparacion, Dificultad dificultad, Temporada temporada,
			//Usuario creador, Boolean subidaPorSistema, List<Receta> subrecetas) {

		
		veganoVerdulero = new UsuarioBuilder()
		.setPeso(80.5)
		.setAltura(1.80)
		.setNombre("Verdulero")
		.setSexo("masculino")
		.setFechaDeNacimiento(LocalDate.of(1994, 9, 24))
		.agregarPreferenciaAlimenticia("lechuga")
		.agregarPreferenciaAlimenticia("tomate")
		.agregarPreferenciaAlimenticia("rucula")
		.agregarDisgustoAlimenticio("carne")
		.agregarDisgustoAlimenticio("pollo")
		.agregarCondicion(new Vegano())
		.setRutina(Rutina.LEVE)
		.build();

		
		veganaFrutera = new UsuarioBuilder()
		.setPeso(80.5)
		.setAltura(1.80)
		.setNombre("Frutero")
		.setSexo("femenino")
		.setFechaDeNacimiento(LocalDate.of(1994, 9, 24))
		.agregarPreferenciaAlimenticia("manzana")
		.agregarPreferenciaAlimenticia("banana")
		.agregarPreferenciaAlimenticia("kiwi")
		.agregarDisgustoAlimenticio("pescado")
		.agregarDisgustoAlimenticio("chivito")
		.agregarCondicion(new Vegano())
		.setRutina(Rutina.INTENSIVO)
		.build();

		
		adictoAlCafe = new UsuarioBuilder()
		.setPeso(43.2)
		.setAltura(1.42)
		.setNombre("Cafetero")
		.setSexo("masculino")
		.setFechaDeNacimiento(LocalDate.of(1992, 3, 6))
		.agregarPreferenciaAlimenticia("cafe")
		.agregarPreferenciaAlimenticia("leche")
		.agregarPreferenciaAlimenticia("cafe con leche")
		.agregarDisgustoAlimenticio("nesquik")
		.agregarDisgustoAlimenticio("no cafe")
		.setRutina(Rutina.NADA)
		.build();

		
		veganoVerdulero.setGestorDeConsultas(gestorDeConsultas);
		veganaFrutera.setGestorDeConsultas(gestorDeConsultas);
		adictoAlCafe.setGestorDeConsultas(gestorDeConsultas);
		
		veganoVerdulero.agregarRepositorio(repoRecetas);
		veganaFrutera.agregarRepositorio(repoRecetas);
		adictoAlCafe.agregarRepositorio(repoRecetas);
		
		caramelo = new RecetaBuilder()
		.setNombre("caramelo")
		.setIngredientes(ingredientesDeCaramelo)
		.setCondimentos(condimentosDeCaramelo)
		.setCalorias(3000.0)
		.setPreparacion("hacer caramelo")
		.setDificultad(Dificultad.DIFICIL)
		.setTemporada(null)
		.setCreador(veganaFrutera)
		.setSubidaPorSistema(false)
		.setRepositorio(repoRecetas)
		.build();
		
		caldoSalado = new RecetaBuilder()
		.setNombre("caldo salado")
		.setIngredientes(ingredientesDeCaldoSalado)
		.setCondimentos(condimentosDeCaldoSalado)
		.setCalorias(700.0)
		.setPreparacion("hacer caldo salado")
		.setDificultad(Dificultad.DIFICIL)
		.setTemporada(null)
		.setCreador(veganoVerdulero)
		.setSubidaPorSistema(false)
		.setRepositorio(repoRecetas)
		.build();
		
		cafeConLeche = new RecetaBuilder()
		.setNombre("cafe con leche")
		.setIngredientes(ingredientesDeCafeConLeche)
		.setCondimentos(condimentosDeCafeConLeche)
		.setCalorias(50.0)
		.setPreparacion("mezclar cafe y leche")
		.setDificultad(Dificultad.FACIL)
		.setTemporada(null)
		.setCreador(adictoAlCafe)
		.setSubidaPorSistema(false)
		.setRepositorio(repoRecetas)
		.build();
		
		
		
	}

	@Test
	public void dosVeganosConsultanTresVeces() {
		
		veganaFrutera.filtrarRecetas(new FiltroNulo());
		veganaFrutera.filtrarRecetas(new FiltroNulo());
		veganoVerdulero.filtrarRecetas(new FiltroNulo());
	
		assertEquals(consultasDeVeganosPorRecetasDificiles.cantidadDeVeganosConRecetasDificiles(), (Integer) 2 );
		
	}
	
	@Test
	public void elCarameloEsElMasConsultado() {
		
		veganaFrutera.filtrarRecetas(new FiltroNulo());
		veganaFrutera.filtrarRecetas(new FiltroNulo());
		veganoVerdulero.filtrarRecetas(new FiltroNulo());
		adictoAlCafe.filtrarRecetas(new FiltroNulo());
		
		assertTrue(recetasMasConsultadas.recetaMasConsultada().equalsIgnoreCase("caramelo"));
	}
	@Test
	public void consultamosAEstaHora() {
		
		veganaFrutera.filtrarRecetas(new FiltroNulo());
		veganaFrutera.filtrarRecetas(new FiltroNulo());
		veganoVerdulero.filtrarRecetas(new FiltroNulo());
		
		assertEquals(consultasPorHora.cantidadDeConsultasPorHora(Calendar.HOUR_OF_DAY), (Integer) 3 );
		
	}
	
	@Test
	public void lasMujeresConsultanMasElCaramelo() {
		
		veganaFrutera.filtrarRecetas(new FiltroNulo());
		
		assertTrue(recetasSegunSexo.recetaMasConsultadaPorMujeres().equalsIgnoreCase("caramelo"));
		
	}
	
	@Test
	public void losHombresConsultanMasElCaldoSalado() {
		
		veganoVerdulero.filtrarRecetas(new FiltroNulo());
		veganoVerdulero.filtrarRecetas(new FiltroNulo());
		adictoAlCafe.filtrarRecetas(new FiltroNulo());
		
		assertTrue(recetasSegunSexo.recetaMasConsultadaPorHombres().equalsIgnoreCase("caldo salado"));
			
	}
}
