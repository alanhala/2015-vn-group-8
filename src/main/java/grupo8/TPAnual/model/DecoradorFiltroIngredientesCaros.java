package grupo8.TPAnual.model;

import java.util.List;
import java.util.stream.Collectors;

public class DecoradorFiltroIngredientesCaros implements Filtro {
	
	private Filtro filtro;
	private List<String> ingredientesCaros;

	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario) {
		List<Receta> recetasFiltradas = filtro.filtrarRecetasDe(usuario);
		return recetasFiltradas.stream().filter(receta -> !receta.tieneEstosIngredientes(ingredientesCaros)).collect(Collectors.toList());
	}

}
