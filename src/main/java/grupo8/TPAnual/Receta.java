package grupo8.TPAnual;

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
	
	//los warnings son porque esos atributos no se usaron aun
	
	public boolean esValida()
	{
		return (this.tieneAlMenosUnIngrediente() && this.tieneCaloriasEntre(10, 500));
	}
	
	public boolean tieneAlMenosUnIngrediente()
	{
		return ingredientes.size() >= 1;
	}
	
	public boolean tieneCaloriasEntre(int limiteInferiorDelRango, int limiteSuperiorDelRango)
	{
		return ( (limiteInferiorDelRango < this.caloriasTotalesDeLaReceta()) && ( this.caloriasTotalesDeLaReceta() < limiteSuperiorDelRango));
	}
	
	public double caloriasTotalesDeLaReceta()
	{
		return (this.caloriasTotalesDe(ingredientes) + this.caloriasTotalesDe(condimentos));
	}
	
	public double caloriasTotalesDe(List<ComponenteDeReceta> unosComponentes)
	{
		return (unosComponentes.stream().map(ComponenteDeReceta::calorias).reduce((componente1,componente2) -> componente1 + componente2).get());
	}
	
	public boolean puedeSerVistaPor(Usuario usuario)
	{
		return (subidaPorSistema == true) || usuario == creador;
	}
	
	public boolean puedeSerModificadaPor(Usuario usuario)
	{
		return puedeSerVistaPor(usuario);
	}
	
	// quiero ver como quedan modelados los usuarios antes de agregar las condiciones preexistentes
	// no para hipertensos: contiene sal o caldo
	// no para diabeticos: +100gr de azucar como condimento
	// no para veganos: pollo, carne, chivito, chori como ingredientes
	
}
