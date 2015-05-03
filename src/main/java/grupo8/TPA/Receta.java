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
		return ( (limiteInferiorDelRango < this.caloriasTotales()) && ( this.caloriasTotales() < limiteSuperiorDelRango));
	}
	
	
	double caloriasTotales()
	{
		return (ingredientes.stream().map(Ingrediente::calorias).reduce(( (ingrediente1,ingrediente2) -> ingrediente1 + ingrediente2)).get()) + (condimentos.stream().map(Condimento::calorias).reduce(( (condimento1,condimento2) -> condimento1 + condimento2)).get());
	}
	
	boolean puedeSerVistaPor(Usuario usuario)
	{
		return (subidaPorSistema == true) || usuario == creador;
	}
	
	boolean puedeSerModificadaPor(Usuario usuario)
	{
		return puedeSerVistaPor(usuario);
	}
	
	// quiero ver cómo quedan modelados los usuarios antes de agregar las condiciones preexistentes
	// no para hipertensos: contiene sal o caldo
	// no para diabéticos: +100gr de azúcar como condimento
	// no para veganos: pollo, carne, chivito, chori como ingredientes
	
}
