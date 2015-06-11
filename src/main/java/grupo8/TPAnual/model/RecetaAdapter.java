package grupo8.TPAnual.model;

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
	
	public Receta getReceta(){
		Receta receta = new Receta(nombre,this.crearComponentes(),this.crearComponentes(),(double) totalCalorias,null,false);
		return receta;
	}
	
	public List<ComponenteDeReceta> crearComponentes(){
		List<ComponenteDeReceta> componentes = new ArrayList<ComponenteDeReceta>();
		ingredientes.forEach(ingrediente -> componentes.add(new ComponenteDeReceta(ingrediente,0.0,0.0)));
		return componentes;
	}
	
}