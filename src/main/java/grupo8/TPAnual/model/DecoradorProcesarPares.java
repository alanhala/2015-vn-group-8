package grupo8.TPAnual.model;

import java.util.List;
import java.util.stream.Collectors;

public class DecoradorProcesarPares implements Filtro {

	private Filtro filtro;

	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario) {
		List<Receta> resultadosFiltrados = filtro.filtrarRecetasDe(usuario);
		return resultadosFiltrados
				.stream()
				.filter(resultado -> resultadosFiltrados.indexOf(resultado) % 2 == 0)
				.collect(Collectors.toList());
	}

}
