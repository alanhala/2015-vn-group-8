package grupo8.TPAnual.model.Builders;

import java.util.ArrayList;
import java.util.List;

import grupo8.TPAnual.exceptions.RecetaConCaloriasFueraDelRangoException;
import grupo8.TPAnual.exceptions.RecetaSinIngredientesException;
import grupo8.TPAnual.model.Dominio.ComponenteDeReceta;
import grupo8.TPAnual.model.Dominio.Dificultad;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Temporada;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Repositorios.RepoRecetas;

public class RecetaBuilder {

	private Receta receta;
	
	private String nombre;
	private List<ComponenteDeReceta> ingredientes;
	private List<ComponenteDeReceta> condimentos;
	private Double calorias;
	private String preparacion;
	private Dificultad dificultad;
	private Temporada temporada;
	private Usuario creador;
	private Boolean subidaPorSistema;
	private List<Receta> subrecetas;
	private RepoRecetas repositorio;
	
	public RecetaBuilder(){
	
	ingredientes = new ArrayList<ComponenteDeReceta>();
	condimentos = new ArrayList<ComponenteDeReceta>();
	subrecetas = new ArrayList<Receta>();
	
	}
	
	public Receta build(){
		
		this.esValida();
			
		receta = new Receta(nombre, ingredientes, condimentos, calorias,
							preparacion, dificultad, temporada, creador,
							subidaPorSistema, subrecetas, repositorio);
		
		return receta;
	}

	public void esValida() {
		this.tieneAlMenosUnIngrediente();
		this.tieneCaloriasEntre(10, 5000);
	}

	public void tieneAlMenosUnIngrediente() {
		if (!(ingredientes.size() >= 1))
			throw new RecetaSinIngredientesException(
					"La receta no tiene ingredientes");
	}

	public void tieneCaloriasEntre(int limiteInferiorDelRango,
			int limiteSuperiorDelRango) {
		if (!((limiteInferiorDelRango < calorias) && (calorias < limiteSuperiorDelRango)))
			throw new RecetaConCaloriasFueraDelRangoException(
					"Las calorias deben estar entre los valores "
							+ limiteInferiorDelRango + " y "
							+ limiteSuperiorDelRango);
	}
	
	public RecetaBuilder setNombre(String nuevoNombre){
		nombre = nuevoNombre;
		return this;
	}
	
	public RecetaBuilder agregarIngrediente(ComponenteDeReceta ingrediente) {
		ingredientes.add(ingrediente);
		return this;
	}
	
	public RecetaBuilder setIngredientes(List<ComponenteDeReceta> nuevosIngredientes) {
		ingredientes = nuevosIngredientes;
		return this;
	}
	
	public RecetaBuilder agregarCondimento(ComponenteDeReceta condimento) {
		condimentos.add(condimento);
		return this;
	}
	
	public RecetaBuilder setCondimentos(List<ComponenteDeReceta> nuevosCondimentos) {
		ingredientes = nuevosCondimentos;
		return this;
	}
	
	public RecetaBuilder setCalorias(Double nuevasCalorias){
		calorias = nuevasCalorias;
		return this;
	}
	
	public RecetaBuilder setPreparacion(String nuevaPreparacion){
		preparacion = nuevaPreparacion;
		return this;
	}
	
	public RecetaBuilder setDificultad(Dificultad nuevaDificultad){
		dificultad = nuevaDificultad;
		return this;
	}
	
	public RecetaBuilder setTemporada(Temporada nuevaTemporada){
		temporada = nuevaTemporada;
		return this;
	}
	
	public RecetaBuilder setCreador(Usuario nuevoCreador){
		creador = nuevoCreador;
		return this;
	}
	
	public RecetaBuilder setSubidaPorSistema(Boolean esSubidaPorSistema){
		subidaPorSistema = esSubidaPorSistema;
		return this;
	}
	
	public RecetaBuilder agregarSubreceta(Receta receta) {
		subrecetas.add(receta);
		return this;
	}
	
	public RecetaBuilder setRepositorio(RepoRecetas nuevoRepositorio){
		repositorio = nuevoRepositorio;
		return this;
	}
}

