package grupo8.TPAnual.model;

import java.util.List;

public class Receta {

	private String nombre;
	private List<ComponenteDeReceta> ingredientes;
	private List<ComponenteDeReceta> condimentos;
	private String preparacion;
	private String dificultad;
	private Temporada temporada;
	private Usuario creador;
	private Boolean subidaPorSistema;
	private List<Receta> subrecetas;

	// los warnings son porque esos atributos no se usaron aun
	
	public Receta(String nombre, List<ComponenteDeReceta> ingredientes,
			List<ComponenteDeReceta> condimentos, String preparacion,
			String dificultad, Temporada temporada) {
		super();
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.condimentos = condimentos;
		this.preparacion = preparacion;
		this.dificultad = dificultad;
		this.temporada = temporada;
	}

	public boolean esValida() {
		return (this.tieneAlMenosUnIngrediente() && this.tieneCaloriasEntre(10,
				500));
	}

	public boolean tieneAlMenosUnIngrediente() {
		return ingredientes.size() >= 1;
	}

	public boolean tieneCaloriasEntre(int limiteInferiorDelRango,
			int limiteSuperiorDelRango) {
		return ((limiteInferiorDelRango < this.caloriasTotalesDeLaReceta()) && (this
				.caloriasTotalesDeLaReceta() < limiteSuperiorDelRango));
	}

	public double caloriasTotalesDeLaReceta() {
		return (this.caloriasTotalesDe(ingredientes) + this
				.caloriasTotalesDe(condimentos));
	}

	public double caloriasTotalesDe(List<ComponenteDeReceta> unosComponentes) {
		return (unosComponentes
				.stream()
				.map(ComponenteDeReceta::calorias)
				.reduce((componente1, componente2) -> componente1 + componente2)
				.get());
	}
	
	public boolean esPublica(){
		return subidaPorSistema;
	}

	public boolean puedeSerVistaPor(Usuario usuario) {
		return (esPublica()) || usuario == creador;
	}

	public boolean puedeSerModificadaPor(Usuario usuario) {
		return puedeSerVistaPor(usuario);
	}

	public boolean tieneSalOCaldo() {
		return condimentos.stream().anyMatch(
				condimento -> condimento.nombre() == "sal")
				|| ingredientes.stream().anyMatch(
						ingrediente -> ingrediente.nombre() == "caldo");
	}

	public boolean tieneMasDe100GramosDeAzucar() {
		return condimentos.stream().anyMatch(
				condimento -> (condimento.nombre() == "azucar" && condimento
						.cantidad() > 100));
	}

	public boolean tieneEstosIngredientes(List<String> estosIngredientes) {
		return ingredientes.stream()
				.anyMatch(
						ingrediente -> estosIngredientes.contains(ingrediente
								.nombre()));
	}

	public List<Condicion> condicionesInadecuadas(
			List<Condicion> condicionesPreexistentes) {
		return (List<Condicion>) condicionesPreexistentes.stream().filter(
				condicion -> condicion.esInadecuadaParaUnaReceta(this));
		//no entiendo por que tengo que poner el (List<Condicion>) para que funcione
	}
	
	public void agregarSubreceta(Receta receta){
		subrecetas.add(receta);
	}
	
	

}
