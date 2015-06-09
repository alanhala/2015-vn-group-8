package grupo8.TPAnual.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroNulo implements Filtro {

	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario, List<Receta> recetasAFiltrar) {
		return recetasAFiltrar;
	}
}
