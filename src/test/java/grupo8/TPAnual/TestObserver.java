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

		vegano = new Vegano();
		
		veganoVerduleroBuilder = new UsuarioBuilder();
		veganoVerduleroBuilder.setPeso(80.5);
		veganoVerduleroBuilder.setAltura(1.80);
		veganoVerduleroBuilder.setNombre("Verdulero");
		veganoVerduleroBuilder.setSexo("masculino");
		veganoVerduleroBuilder.setFechaDeNacimiento(LocalDate.of(1994, 9, 24));
		veganoVerduleroBuilder.agregarPreferenciaAlimenticia("lechuga");
		veganoVerduleroBuilder.agregarPreferenciaAlimenticia("tomate");
		veganoVerduleroBuilder.agregarPreferenciaAlimenticia("rucula");
		veganoVerduleroBuilder.agregarDisgustoAlimenticio("carne");
		veganoVerduleroBuilder.agregarDisgustoAlimenticio("pollo");
		veganoVerduleroBuilder.agregarCondicion(vegano);
		veganoVerduleroBuilder.setRutina(Rutina.LEVE);
		veganoVerdulero = veganoVerduleroBuilder.build();

		
		veganaFruteraBuilder = new UsuarioBuilder();
		veganaFruteraBuilder.setPeso(80.5);
		veganaFruteraBuilder.setAltura(1.80);
		veganaFruteraBuilder.setNombre("Frutero");
		veganaFruteraBuilder.setSexo("femenino");
		veganaFruteraBuilder.setFechaDeNacimiento(LocalDate.of(1994, 9, 24));
		veganaFruteraBuilder.agregarPreferenciaAlimenticia("manzana");
		veganaFruteraBuilder.agregarPreferenciaAlimenticia("banana");
		veganaFruteraBuilder.agregarPreferenciaAlimenticia("kiwi");
		veganaFruteraBuilder.agregarDisgustoAlimenticio("pescado");
		veganaFruteraBuilder.agregarDisgustoAlimenticio("chivito");
		veganaFruteraBuilder.agregarCondicion(vegano);
		veganaFruteraBuilder.setRutina(Rutina.INTENSIVO);
		veganaFrutera = veganaFruteraBuilder.build();

		
		adictoAlCafeBuilder = new UsuarioBuilder();
		adictoAlCafeBuilder.setPeso(43.2);
		adictoAlCafeBuilder.setAltura(1.42);
		adictoAlCafeBuilder.setNombre("Cafetero");
		adictoAlCafeBuilder.setSexo("masculino");
		adictoAlCafeBuilder.setFechaDeNacimiento(LocalDate.of(1992, 3, 6));
		adictoAlCafeBuilder.agregarPreferenciaAlimenticia("cafe");
		adictoAlCafeBuilder.agregarPreferenciaAlimenticia("leche");
		adictoAlCafeBuilder.agregarPreferenciaAlimenticia("cafe con leche");
		adictoAlCafeBuilder.agregarDisgustoAlimenticio("nesquik");
		adictoAlCafeBuilder.agregarDisgustoAlimenticio("no cafe");
		adictoAlCafeBuilder.setRutina(Rutina.NADA);
		adictoAlCafe = adictoAlCafeBuilder.build();

		
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
