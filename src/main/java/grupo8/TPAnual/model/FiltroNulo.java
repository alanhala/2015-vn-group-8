package grupo8.TPAnual.model;

import java.util.List;

public class FiltroNulo implements Filtro {

	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario,
			List<Receta> recetasAFiltrar) {
		return recetasAFiltrar;
	}
}
