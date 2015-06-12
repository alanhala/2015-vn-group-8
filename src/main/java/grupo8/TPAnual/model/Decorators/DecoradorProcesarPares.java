package grupo8.TPAnual.model.Decorators;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class DecoradorProcesarPares implements Filtro {

	private Filtro filtro;

	public DecoradorProcesarPares(Filtro unFiltro) {
		this.filtro = unFiltro;
	}
	
	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario, List<Receta> recetasAFiltrar) {
		List<Receta> recetasFiltradasPorDecorador = filtro.filtrarRecetasDe(usuario, recetasAFiltrar);
		return recetasFiltradasPorDecorador
				.stream()
				.filter(receta -> recetasFiltradasPorDecorador.indexOf(receta) % 2 == 0)
				.collect(Collectors.toList());
	}

}
