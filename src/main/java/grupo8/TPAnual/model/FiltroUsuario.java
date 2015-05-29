package grupo8.TPAnual.model;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroUsuario implements Filtro {
	
	public RepositorioRecetas repositorio;

	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario) {
		return repositorio.listarRecetas().stream().filter(receta -> receta.puedeSerVistaOModificadaPor(usuario))
				.collect(Collectors.toList());
	}

}
