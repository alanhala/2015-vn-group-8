package grupo8.TPA;

import java.util.List;

public class Receta {
	
	String nombre;
	List<Ingrediente> ingredientes;
	List<Condimento> condimentos;
	String preparacion;
	String dificultad;
	Temporada temporada;
	Usuario creador;
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
		return ( (limiteInferiorDelRango < this.caloriasTotales()) && ( this.caloriasTotales() < limiteSuperiorDelRango));
	}
	
	
	double caloriasTotales()
	{
		return (ingredientes.stream().map(Ingrediente::calorias).reduce(( (ingrediente1,ingrediente2) -> ingrediente1 + ingrediente2)).get()) + (condimentos.stream().map(Condimento::calorias).reduce(( (condimento1,condimento2) -> condimento1 + condimento2)).get());
	}
	
	

}
