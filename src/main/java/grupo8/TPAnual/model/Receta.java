package grupo8.TPAnual.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Receta {

	private String nombre;
	private List<ComponenteDeReceta> ingredientes;
	private List<ComponenteDeReceta> condimentos;
	private Double calorias;
	private String preparacion;
	private String dificultad;
	private Temporada temporada;
	private Usuario creador;
	private Boolean subidaPorSistema;
	private List<Receta> subrecetas;

	// los warnings son porque esos atributos no se usaron aun

	public Receta(String nombre, List<ComponenteDeReceta> ingredientes,
			List<ComponenteDeReceta> condimentos, Double calorias) {
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.condimentos = condimentos;
		this.calorias = calorias;
		this.subrecetas = new ArrayList<Receta>();
	}

	public Receta(String nombre, List<ComponenteDeReceta> ingredientes,
			List<ComponenteDeReceta> condimentos, Double calorias,
			String preparacion, String dificultad, Temporada temporada,
			Usuario creador, Boolean subidaPorSistema, List<Receta> subrecetas) {
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.condimentos = condimentos;
		this.calorias = calorias;
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
		return ((limiteInferiorDelRango < this.calorias()) && (this.calorias() < limiteSuperiorDelRango));
	}

	private double calorias() {
		return calorias;
	}

	public boolean esPublica() {
		return subidaPorSistema;
	}

	public boolean puedeSerVistaPor(Usuario usuario) {
		return (esPublica()) || usuario == creador || usuario.compartisGrupoCon(creador);
	}

	public boolean puedeSerModificadaPor(Usuario usuario) {
		return puedeSerVistaPor(usuario);
	}

	public boolean tieneSalOCaldo() {
		return condimentos.stream().anyMatch(
				condimento -> condimento.nombre() == "sal")
				|| ingredientes.stream().anyMatch(
						ingrediente -> ingrediente.nombre() == "caldo")
				|| subrecetas.stream().anyMatch(
						receta -> receta.tieneSalOCaldo());
	}

	public boolean tieneMasDe100GramosDeAzucar() {
		return condimentos.stream().anyMatch(
				condimento -> (condimento.nombre() == "azucar" && condimento
						.cantidad() > 100))
				|| subrecetas.stream().anyMatch(
						receta -> receta.tieneMasDe100GramosDeAzucar());

	}

	public boolean tieneEstosIngredientes(List<String> estosIngredientes) {
		return ingredientes.stream()
				.anyMatch(
						ingrediente -> estosIngredientes.contains(ingrediente
								.nombre()))
				|| subrecetas.stream().anyMatch(
						receta -> receta
								.tieneEstosIngredientes(estosIngredientes));
	}

	public List<Condicion> condicionesInadecuadas(
			List<Condicion> condicionesPreexistentes) {
		List<Condicion> condiciones = condicionesPreexistentes.stream().filter(
condicion -> condicion.esInadecuadaParaUnaReceta(this))
				.collect(Collectors.toList());
		return condiciones;
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

	public void modificarPreparacion(String nuevaPreparacion) {
		preparacion = nuevaPreparacion;
	}

	public Receta clonar(Usuario creador) {

		return new Receta(this.nombre, this.ingredientes, this.condimentos,
				this.calorias, this.preparacion, this.dificultad,
				this.temporada, creador, this.subidaPorSistema, this.subrecetas);
	}

}
