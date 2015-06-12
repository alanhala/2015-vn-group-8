package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import grupo8.TPAnual.model.Adapters.BuscadorAdapter;
import grupo8.TPAnual.model.Adapters.RecetaAdapter;
import grupo8.TPAnual.model.Monitores.GestorDeConsultas;
import grupo8.TPAnual.model.Monitores.Monitor;
import grupo8.TPAnual.model.Monitores.MonitorConsultasDeVeganosPorRecetasDificiles;
import grupo8.TPAnual.model.Monitores.MonitorConsultasPorHora;
import grupo8.TPAnual.model.Monitores.MonitorRecetasMasConsultadas;
import grupo8.TPAnual.model.Monitores.MonitorRecetasSegunSexo;
import grupo8.TPAnual.model.Repositorios.RepoRecetas;
import grupo8.TPAnual.model.Repositorios.RepoRecetasAdapter;
import grupo8.TPAnual.model.CondicionesPreexistentes.Condicion;
import grupo8.TPAnual.model.CondicionesPreexistentes.Vegano;
import grupo8.TPAnual.model.Decorators.FiltroNulo;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Dificultad;
import grupo8.TPAnual.model.Dominio.Grupo;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Dominio.Temporada;
import grupo8.TPAnual.model.Dominio.Usuario;

import org.junit.Before;
import org.junit.Test;


public class TestObserver {

	private GestorDeConsultas gestorDeConsultas;
	private MonitorConsultasDeVeganosPorRecetasDificiles consultasDeVeganosPorRecetasDificiles;
	private MonitorConsultasPorHora consultasPorHora;
	private MonitorRecetasMasConsultadas recetasMasConsultadas;
	private MonitorRecetasSegunSexo recetasSegunSexo;
	private Usuario veganoVerdulero;
	private Usuario veganaFrutera;
	private Vegano vegano;
	private RepoRecetas repoRecetas;
	
	Receta caramelo, caldoSalado;
	ComponenteDeReceta arroz, leche, azucar, grasa, sal, caldo;
	
	List<ComponenteDeReceta> ingredientes = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentos = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesDeCaramelo = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosDeCaramelo = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> ingredientesDeCaldoSalado = new ArrayList<ComponenteDeReceta>();
	List<ComponenteDeReceta> condimentosDeCaldoSalado = new ArrayList<ComponenteDeReceta>();
	List<Condicion> condiciones = new ArrayList<Condicion>();
	
	
	@Before
	public void setup() {
		
		consultasDeVeganosPorRecetasDificiles = new MonitorConsultasDeVeganosPorRecetasDificiles();
		consultasPorHora = new MonitorConsultasPorHora();
		recetasMasConsultadas = new MonitorRecetasMasConsultadas();
		recetasSegunSexo = new MonitorRecetasSegunSexo();
		
		gestorDeConsultas = new GestorDeConsultas();
		
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

		ingredientes.add(arroz);
		ingredientes.add(leche);
		condimentos.add(azucar);
		ingredientesDeCaramelo.add(grasa);
		condimentosDeCaramelo.add(azucar);
		ingredientesDeCaldoSalado.add(caldo);
		condimentosDeCaldoSalado.add(sal);
		
//	 	public Receta(String nombre, List<ComponenteDeReceta> ingredientes,
			//List<ComponenteDeReceta> condimentos, Double calorias,
			//String preparacion, Dificultad dificultad, Temporada temporada,
			//Usuario creador, Boolean subidaPorSistema, List<Receta> subrecetas) {

		vegano = new Vegano();
		
		veganoVerdulero = new Usuario(80.5, 1.80, "Verdulero", "masculino", LocalDate.of(
				1994, 9, 24), Arrays.asList("lechuga", "tomate", "rúcula"),
				Arrays.asList("carne", "pollo"), Arrays.asList(vegano),
				new ArrayList<Receta>(), Rutina.LEVE, new ArrayList<Grupo>());
		
		veganaFrutera = new Usuario(80.5, 1.80, "Frutero", "femenino", LocalDate.of(
				1994, 9, 24), Arrays.asList("manzana", "banana", "kiwi"),
				Arrays.asList("pescado", "bebés"), Arrays.asList(vegano),
				new ArrayList<Receta>(), Rutina.INTENSIVO, new ArrayList<Grupo>());
		
		
		caramelo = new Receta("caramelo", ingredientesDeCaramelo,
				condimentosDeCaramelo, 3000.0, "hacer caramelo", Dificultad.DIFICIL, null,
				veganaFrutera, false, Arrays.asList());
		
		caldoSalado = new Receta("caldo salado", ingredientesDeCaldoSalado,
				condimentosDeCaldoSalado, 700.0, "hacer caldo salado", Dificultad.DIFICIL,
				null, veganoVerdulero, false, Arrays.asList());
		
		repoRecetas = RepoRecetas.getInstance();
		
		
		
	}

	@Test
	public void dosVeganosConsultanTresVeces() {
		
		gestorDeConsultas.consultarRecetas(veganaFrutera, new FiltroNulo());
		gestorDeConsultas.consultarRecetas(veganaFrutera, new FiltroNulo());
		gestorDeConsultas.consultarRecetas(veganoVerdulero, new FiltroNulo());

		assertEquals(consultasDeVeganosPorRecetasDificiles.cantidadDeVeganosConRecetasDificiles(), (Integer) 2 );
		
	}
	
	@Test
	public void elCarameloEsElMasConsultado() {
		
		gestorDeConsultas.consultarRecetas(veganaFrutera, new FiltroNulo());
		gestorDeConsultas.consultarRecetas(veganaFrutera, new FiltroNulo());
		gestorDeConsultas.consultarRecetas(veganoVerdulero, new FiltroNulo());
		
		assertTrue(recetasMasConsultadas.recetaMasConsultada().equalsIgnoreCase("caramelo"));
	}
	@Test
	public void consultamosAntesDeEntregar() {
		
		gestorDeConsultas.consultarRecetas(veganaFrutera, new FiltroNulo());
		gestorDeConsultas.consultarRecetas(veganaFrutera, new FiltroNulo());
		gestorDeConsultas.consultarRecetas(veganoVerdulero, new FiltroNulo());
		
		assertEquals(consultasPorHora.cantidadDeConsultasPorHora(Calendar.HOUR_OF_DAY), (Integer) 3 );
		
	}
	
	@Test
	public void lasMujeresConsultanMásElCaramelo() {
		
		gestorDeConsultas.consultarRecetas(veganaFrutera, new FiltroNulo());
		
		assertTrue(recetasSegunSexo.recetaMasConsultadaPorMujeres().equalsIgnoreCase("caramelo"));
		
	}
	
	@Test
	public void losHombresConsultanMásElCaldoSalado() {
		
		gestorDeConsultas.consultarRecetas(veganoVerdulero, new FiltroNulo());
			
		assertTrue(recetasSegunSexo.recetaMasConsultadaPorHombres().equalsIgnoreCase("caldo salado"));
			
	}
}
