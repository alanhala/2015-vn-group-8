package grupo8.TPAnual.model.Dominio;

import grupo8.TPAnual.exceptions.RecetaConCaloriasFueraDelRangoException;
import grupo8.TPAnual.exceptions.RecetaSinIngredientesException;
import grupo8.TPAnual.model.CondicionesPreexistentes.Condicion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import Persistencia.PersistentEntity;

@Entity
@Table(name="Recetas")
public class Receta extends PersistentEntity {
	
	private String nombre;
	
	@OneToMany
	@JoinColumn(name="receta_id")
	private List<ComponenteDeReceta> ingredientes;
	
	@OneToMany
	@JoinColumn(name="receta_id")
	private List<ComponenteDeReceta> condimentos;
	
	private Double calorias;
	private String preparacion;
	@Enumerated
	private Dificultad dificultad;
	private String temporada;
	@Transient
	private List<Receta> subrecetas;
	private Boolean publica;
	
	public Receta(){
		//Lo necesito para Hibernate
	}

	public Receta(String nombre, List<ComponenteDeReceta> ingredientes,
			List<ComponenteDeReceta> condimentos, Double calorias,
			String preparacion, Dificultad dificultad, String temporada,
			Boolean publica, List<Receta> subrecetas) {
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.condimentos = condimentos;
		this.calorias = calorias;
		this.preparacion = preparacion;
		this.dificultad = dificultad;
		this.temporada = temporada;
		this.subrecetas = subrecetas;
		this.publica = publica;
	}

	public Double calorias() {
		return calorias;
	}

	public boolean esPublica() {
		return publica;
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
		List<Condicion> condiciones = condicionesPreexistentes.stream()
				.filter(condicion -> condicion.esInadecuadaParaUnaReceta(this))
				.collect(Collectors.toList());
		return condiciones;
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

	public Receta clonar() {

		return new Receta(this.nombre, this.ingredientes, this.condimentos,
				this.calorias, this.preparacion, this.dificultad,
				this.temporada, false, this.subrecetas);
	}

	public List<ComponenteDeReceta> getIngredientes() {
		return Collections.unmodifiableList(ingredientes);
	}

	public boolean cumpleCondicionesPara(Usuario usuario) {
		return this.condicionesInadecuadas(usuario.getCondiciones()).isEmpty();
	}

	public boolean esParaPersonaConSobrepeso(Usuario usuario) {
		return usuario.tieneSobrePeso() && calorias < 500;
	}

	public boolean tieneCondicionesAdecuadasPara(Usuario usuario) {
		return this.condicionesInadecuadas(usuario.getCondiciones()).size() == 0;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean esDificil() {
		return this.dificultad == Dificultad.DIFICIL;
	}
}
