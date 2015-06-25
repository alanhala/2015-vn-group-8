package grupo8.TPAnual.model.Monitores;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GestorDeConsultas {
	private List<Monitor> monitores;

	public GestorDeConsultas() {
		monitores = new ArrayList<Monitor>();
	}

	public void agregarMonitor(Monitor unMonitor) {
		monitores.add(unMonitor);
	}

	public void removerMonitor(Monitor unMonitor) {
		monitores.remove(unMonitor);
	}

	public void notificar(Usuario usuario, List<Receta> consulta) {
		monitores.forEach(monitor -> monitor.actualizar(usuario, consulta));
	}
}
