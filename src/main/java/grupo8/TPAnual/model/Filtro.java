package grupo8.TPAnual.model;

import java.util.List;

public interface Filtro {
		
	public List<Receta> filtrarRecetasDe(Usuario usuario, List<Receta> recetasAFiltrar);
}
