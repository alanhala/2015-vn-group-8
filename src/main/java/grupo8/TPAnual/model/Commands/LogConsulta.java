package grupo8.TPAnual.model.Commands;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.List;

import org.apache.commons.logging.Log;

public class LogConsulta implements TratamientoDeConsultas {

	private static Log log;
	private List<Receta> recetasConsultadas;

	public LogConsulta(List<Receta> recetasConsultadas) {
		this.recetasConsultadas = recetasConsultadas;
	}

	@Override
	public void agregarAccionARealizar(Usuario usuario, Filtro filtro,
			List<Receta> recetasFiltradas) {
		usuario.getGestorDeConsultas().agregarAccionARealizar(
				new LogConsulta(recetasConsultadas));

	}

	@Override
	public void ejecutar() {
		log.info(recetasConsultadas);
	}
}
