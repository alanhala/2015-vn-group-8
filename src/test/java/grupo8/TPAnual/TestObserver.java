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

	private MonitorConsultasDeVeganosPorRecetasDificiles consultasDeVeganosPorRecetasDificiles;
	private MonitorConsultasPorHora consultasPorHora;
	private MonitorRecetasMasConsultadas recetasMasConsultadas;
	private MonitorRecetasSegunSexo recetasSegunSexo;
	private Usuario veganoVerdulero;
	private Usuario veganaFrutera;
	private Usuario adictoAlCafe;
	private Vegano vegano;
	private RepoRecetas repoRecetas;
	private GestorDeConsultas gestorDeConsultas = new GestorDeConsultas();
	
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

		vegano = new Vegano();
		
		veganoVerdulero = new Usuario(80.5, 1.80, "Verdulero", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("lechuga", "tomate", "r�cula"),
				Arrays.asList("carne", "pollo"), Arrays.asList(vegano),
				new ArrayList<Receta>(), Rutina.LEVE, new ArrayList<Grupo>());
		
		veganaFrutera = new Usuario(80.5, 1.80, "Frutero", "femenino", LocalDate.of(
				1994, 9, 24), Arrays.asList("manzana", "banana", "kiwi"),
				Arrays.asList("pescado", "beb�s"), Arrays.asList(vegano),
				new ArrayList<Receta>(), Rutina.INTENSIVO, new ArrayList<Grupo>());
		
		adictoAlCafe = new Usuario(43.2, 1.42, "Cafetero", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("cafe", "leche", "cafeConLeche"),
				Arrays.asList("nesquik", "noCafe"), Arrays.asList(),
				new ArrayList<Receta>(), Rutina.NADA, new ArrayList<Grupo>());
		
		veganoVerdulero.setGestorDeConsultas(gestorDeConsultas);
		veganaFrutera.setGestorDeConsultas(gestorDeConsultas);
		adictoAlCafe.setGestorDeConsultas(gestorDeConsultas);
		
		veganoVerdulero.agregarRepositorio(repoRecetas);
		veganaFrutera.agregarRepositorio(repoRecetas);
		adictoAlCafe.agregarRepositorio(repoRecetas);
		
		
		caramelo = new Receta("caramelo", ingredientesDeCaramelo,
				condimentosDeCaramelo, 3000.0, "hacer caramelo", Dificultad.DIFICIL, null,
				veganaFrutera, false, Arrays.asList(),repoRecetas);
		
		caldoSalado = new Receta("caldo salado", ingredientesDeCaldoSalado,
				condimentosDeCaldoSalado, 700.0, "hacer caldo salado", Dificultad.DIFICIL,
				null, veganoVerdulero, false, Arrays.asList(), repoRecetas);
		
		cafeConLeche = new Receta("cafe con leche", ingredientesDeCafeConLeche,
				condimentosDeCafeConLeche, 50.0, "mezclar cafe y leche", Dificultad.FACIL,
				null, adictoAlCafe, false, Arrays.asList(), repoRecetas);
		
		
		
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
