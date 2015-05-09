package grupo8.TPAnual.model;

import grupo8.TPAnual.exceptions.RecetaInvalidaException;

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
			List<ComponenteDeReceta> condimentos) {
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.condimentos = condimentos;

		if (!this.esValida()) {
			throw new RecetaInvalidaException(
					"Receta invalida, por favor ingrese los datos correctamente");
		}
	}

	public Receta(String nombre, List<ComponenteDeReceta> ingredientes,
			List<ComponenteDeReceta> condimentos, String preparacion,
			String dificultad, Temporada temporada, Usuario creador,
			Boolean subidaPorSistema, List<Receta> subrecetas) {
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.condimentos = condimentos;
		this.preparacion = preparacion;
		this.dificultad = dificultad;
		this.temporada = temporada;
		this.creador = creador;
		this.subidaPorSistema = subidaPorSistema;
		this.subrecetas = subrecetas;


	}

	public boolean esValida() {
		return (this.tieneAlMenosUnIngrediente() && this.tieneCaloriasEntre(10,
				5000));
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

	public boolean esPublica() {
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
		// no entiendo por que tengo que poner el (List<Condicion>) para que
		// funcione
	}

	public void agregarSubreceta(Receta receta) {
		subrecetas.add(receta);
	}

	public void agregarIngrediente(ComponenteDeReceta ingrediente) {
		ingredientes.add(ingrediente);
	}
	
	public void agregarCondimento(ComponenteDeReceta condimento) {
		condimentos.add(condimento);
	}
	
	public void modificarPreparacion(String nuevaPreparacion){
		preparacion = nuevaPreparacion;
	}

	public Receta clonar(Usuario creador) {
		
		return new Receta(this.nombre, this.ingredientes,
				this.condimentos, this.preparacion,
				this.dificultad, this.temporada, creador,
				this.subidaPorSistema, this.subrecetas);
	
		
	}
}

