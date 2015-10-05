package grupo8.TPAnual;

import static org.junit.Assert.*;
import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.Builders.UsuarioBuilder;
import grupo8.TPAnual.model.CondicionesPreexistentes.Celiaco;
import grupo8.TPAnual.model.CondicionesPreexistentes.Condicion;
import grupo8.TPAnual.model.CondicionesPreexistentes.Diabetico;
import grupo8.TPAnual.model.CondicionesPreexistentes.Hipertenso;
import grupo8.TPAnual.model.CondicionesPreexistentes.Vegano;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import Persistencia.EntityManager;

public class TestPersistencia {
	
	EntityManager entityManager;
	Receta recetaSinSubrecetas, recetaInvalida, recetaConSubreceta, caramelo,
	caldoSalado, aireCalorico;
	ComponenteDeReceta arroz, leche, azucar, grasa, sal, caldo;
	Celiaco celiaco;
	Diabetico diabetico;
	Hipertenso hipertenso;
	Vegano vegano;
	Usuario pepe;
	UsuarioBuilder pepeBuilder;

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
		
		entityManager = new EntityManager();
		
		arroz = new ComponenteDeReceta("gramos de arroz", 50.0, 100.0);
		leche = new ComponenteDeReceta("tazas de leche", 3.0, 250.0);
		azucar = new ComponenteDeReceta("azucar", 120.0, 130.0);
		grasa = new ComponenteDeReceta("cucharadas de grasa", 300.0, 7500.0);
		sal = new ComponenteDeReceta("sal", 10.0, 30.0);
		caldo = new ComponenteDeReceta("caldo", 4.0, 50.0);

		
		pepe = new UsuarioBuilder()
		.setPeso(70.0)
		.setAltura(1.70)
		.setNombre("Pepex")
		.setSexo("masculino")
		.setFechaDeNacimiento(LocalDate.of(1990, 4, 2))
		.agregarPreferenciaAlimenticia("asado")
		.agregarPreferenciaAlimenticia("chivito")
		.agregarCondicion(new Hipertenso())
		.setRutina(Rutina.LEVE)
		.build();

		
		caramelo = new RecetaBuilder()
		.setNombre("Caramelo")
		.agregarIngrediente(grasa)
		.agregarCondimento(azucar)
		.setCalorias(3000.0)
		.setSubidaPorSistema(true)
		.build();
		pepe.agregarReceta(caramelo);
		
		caldoSalado = new RecetaBuilder()
		.setNombre("CaldoSalado")
		.agregarIngrediente(caldo)
		.agregarCondimento(sal)
		.setCalorias(700.0)
		.setSubidaPorSistema(true)
		.build();
		pepe.agregarReceta(caldoSalado);
		
		recetaSinSubrecetas = new RecetaBuilder()
		.setNombre("Arroz con leche")
		.agregarIngrediente(arroz)
		.agregarIngrediente(leche)
		.agregarCondimento(azucar)
		.setCalorias(150.0)
		.setSubidaPorSistema(true)
		.build();
		pepe.agregarReceta(recetaSinSubrecetas);
		
		recetaConSubreceta = new RecetaBuilder()
		.setNombre("Arroz con leche y caramelo")
		.agregarIngrediente(arroz)
		.agregarIngrediente(leche)
		.agregarCondimento(azucar)
		.setCalorias(4500.0)
		.setSubidaPorSistema(true)
		.agregarSubreceta(caramelo)
		.agregarSubreceta(caldoSalado)
		.build();
		pepe.agregarReceta(recetaConSubreceta);

	}

	@Ignore
	@Test
	public void persisteUsuario(){
		entityManager.registrar(pepe);
		assertEquals(entityManager.obtenerUsuario(pepe.getId()), pepe);
	}
	
}
