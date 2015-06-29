package grupo8.TPAnual.model.Commands;

import java.util.List;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

public interface TratamientoDeConsultas {

	public void agregarAccionARealizar(Usuario usuario, Filtro filtro,
			List<Receta> recetasFiltradas);
	
	public void ejecutar();
}
