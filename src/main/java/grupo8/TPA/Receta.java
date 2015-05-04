package grupo8.TPA;

import java.util.List;

public class Receta {
	
	String nombre;
	List<ComponenteDeReceta> ingredientes;
	List<ComponenteDeReceta> condimentos;
	String preparacion;
	String dificultad;
	Temporada temporada;
	Usuario creador;
	Boolean subidaPorSistema;
	List<Receta> subrecetas;
	
	boolean esValida()
	{
		return (this.tieneAlMenosUnIngrediente() && this.tieneCaloriasEntre(10, 500));
	}
	
	boolean tieneAlMenosUnIngrediente()
	{
		return ingredientes.size() >= 1;
	}
	
	boolean tieneCaloriasEntre(int limiteInferiorDelRango, int limiteSuperiorDelRango)
	{
		return ( (limiteInferiorDelRango < this.caloriasTotalesDeLaReceta()) && ( this.caloriasTotalesDeLaReceta() < limiteSuperiorDelRango));
	}
	
	double caloriasTotalesDeLaReceta()
	{
		return (this.caloriasTotalesDe(ingredientes) + this.caloriasTotalesDe(condimentos));
	}
	
	double caloriasTotalesDe(List<ComponenteDeReceta> unosComponentes)
	{
		return (unosComponentes.stream().map(ComponenteDeReceta::calorias).reduce((componente1,componente2) -> componente1 + componente2).get());
	}
	
	boolean puedeSerVistaPor(Usuario usuario)
	{
		return (subidaPorSistema == true) || usuario == creador;
	}
	
	boolean puedeSerModificadaPor(Usuario usuario)
	{
		return puedeSerVistaPor(usuario);
	}
	
	// quiero ver c�mo quedan modelados los usuarios antes de agregar las condiciones preexistentes
	// no para hipertensos: contiene sal o caldo
	// no para diab�ticos: +100gr de az�car como condimento
	// no para veganos: pollo, carne, chivito, chori como ingredientes
	
}
