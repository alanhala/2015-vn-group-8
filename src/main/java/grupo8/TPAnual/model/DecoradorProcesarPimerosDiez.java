package grupo8.TPAnual.model;

import java.util.List;
import java.util.stream.Collectors;

public class DecoradorProcesarPimerosDiez implements Filtro {

	private Filtro filtro;
	
	public DecoradorProcesarPimerosDiez(Filtro unFiltro) {
		this.filtro = unFiltro;
	}

	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario, List<Receta> recetasAFiltrar) {
		List<Receta> recetasFiltradasPorDecorador = filtro.filtrarRecetasDe(usuario, recetasAFiltrar);
		return recetasFiltradasPorDecorador.stream().limit(10).collect(Collectors.toList());
	}

}
