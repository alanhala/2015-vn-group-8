package grupo8.TPAnual.model.Decorators;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DecoradorProcesarOrdenamiento implements Filtro {

	private Filtro filtro;
	private Comparator<Receta> comparator;
	
	public DecoradorProcesarOrdenamiento(Filtro unFiltro, Comparator<Receta> unComparator) {
		this.filtro = unFiltro;
		this.comparator = unComparator;
	}
	
	@Override
	public List<Receta> filtrarRecetasDe(Usuario usuario, List<Receta> recetasAFiltrar) {
		List<Receta> recetasFiltradasPorDecorador = filtro.filtrarRecetasDe(usuario, recetasAFiltrar);
		Collections.sort(recetasFiltradasPorDecorador, comparator);
		return recetasFiltradasPorDecorador;
	}

}
