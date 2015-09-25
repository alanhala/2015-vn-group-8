package grupo8.TPAnual.model.Adapters;

import grupo8.TPAnual.model.Builders.RecetaBuilder;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Repositorios.Recetario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import queComemos.entrega3.dominio.Dificultad;

public class RecetaAdapter {
	
	private String nombre;
	private List<String> ingredientes;
	private int tiempoPreparacion;
	private int totalCalorias;
	private Dificultad dificultadReceta;
	private String autor;
	private int anioReceta;
	private RecetaBuilder recetaBuilder;
	
	public Receta getReceta(){
		Usuario usuarioExterno = new Usuario(2.0,2.0,"Usuario externo", LocalDate.of(1994, 11, 14), Rutina.LEVE);
		
		recetaBuilder = new RecetaBuilder();
		recetaBuilder.setNombre(nombre);
		recetaBuilder.setIngredientes(this.crearComponentes());
		recetaBuilder.setCondimentos(this.crearComponentes());
		recetaBuilder.setCalorias((double) totalCalorias);
		recetaBuilder.setSubidaPorSistema(false);
		
		return recetaBuilder.build();
	}
	
	public List<ComponenteDeReceta> crearComponentes(){
		List<ComponenteDeReceta> componentes = new ArrayList<ComponenteDeReceta>();
		ingredientes.forEach(ingrediente -> componentes.add(new ComponenteDeReceta(ingrediente,0.0,0.0)));
		return componentes;
	}
	
}