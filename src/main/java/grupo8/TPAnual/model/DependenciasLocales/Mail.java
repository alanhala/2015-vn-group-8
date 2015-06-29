package grupo8.TPAnual.model.DependenciasLocales;

import java.util.List;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;

public class Mail {
	
	private Filtro parametrosDeBusqueda;
	private int cantidadDeResultados;
	
	public Mail(Filtro parametrosDeBusqueda, List<Receta> recetasFiltradas) {
		this.parametrosDeBusqueda = parametrosDeBusqueda;
		this.cantidadDeResultados = recetasFiltradas.size();
	}

}
