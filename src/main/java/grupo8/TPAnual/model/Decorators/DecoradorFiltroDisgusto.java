package grupo8.TPAnual.model.Decorators;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class DecoradorFiltroDisgusto implements Filtro {

	private Filtro filtro;
	
	public DecoradorFiltroDisgusto(Filtro filtro){
		this.filtro = filtro;
	}
	
	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario, List<Receta> recetasAFiltrar) {
		List<Receta> recetasAFiltrarPorDecorador = recetasAFiltrar.stream().filter(receta -> !usuario.leDisgusta(receta)).collect(Collectors.toList());
		return filtro.filtrarRecetasDe(usuario, recetasAFiltrarPorDecorador);
	}

}
