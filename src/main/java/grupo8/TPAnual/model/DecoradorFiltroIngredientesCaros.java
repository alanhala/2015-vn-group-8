package grupo8.TPAnual.model;

import java.util.List;
import java.util.stream.Collectors;

public class DecoradorFiltroIngredientesCaros implements Filtro {
	
	private Filtro filtro;
	private List<String> ingredientesCaros;
	
	public DecoradorFiltroIngredientesCaros(Filtro filtro, List <String> ingredientesCaros){
		this.filtro = filtro;
		this.ingredientesCaros = ingredientesCaros;
	}

	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario, List<Receta> recetasAFiltrar) {
		List<Receta> recetasAFiltrarPorDecorador = recetasAFiltrar
				.stream()
				.filter(receta -> !receta
						.tieneEstosIngredientes(ingredientesCaros))
				.collect(Collectors.toList());
		return filtro.filtrarRecetasDe(usuario, recetasAFiltrarPorDecorador);
	}

}