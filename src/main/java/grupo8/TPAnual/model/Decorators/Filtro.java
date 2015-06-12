package grupo8.TPAnual.model.Decorators;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.List;

public interface Filtro {
		
	public List<Receta> filtrarRecetasDe(Usuario usuario, List<Receta> recetasAFiltrar);
}
