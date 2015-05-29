package grupo8.TPAnual.model;

import java.util.List;
import java.util.stream.Collectors;

public class DecoradorFiltroCondicionesPreexistentes implements Filtro {
	
	private Filtro filtro;
	
	public DecoradorFiltroCondicionesPreexistentes(Filtro filtro){
		this.filtro = filtro;
	}

	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario) {
		List<Receta> recetasFiltradas = filtro.filtrarRecetasDe(usuario);
		return recetasFiltradas.stream().filter(receta -> receta.tieneCondicionesAdecuadasPara(usuario)).collect(Collectors.toList());
	}

}
