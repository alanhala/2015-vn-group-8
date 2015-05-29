package grupo8.TPAnual.model;

import java.util.List;
import java.util.stream.Collectors;

public class DecoradorProcesarPimerosDiez implements Filtro {

	private Filtro filtro;

	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario) {
		List<Receta> recetasFiltradas = filtro.filtrarRecetasDe(usuario);
		return recetasFiltradas.stream().limit(9).collect(Collectors.toList());
	}

}
