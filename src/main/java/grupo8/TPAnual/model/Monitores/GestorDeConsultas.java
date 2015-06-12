package grupo8.TPAnual.model.Monitores;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GestorDeConsultas {
	private static GestorDeConsultas instance;
	private List<Monitor> monitores;
	
	private GestorDeConsultas(){
		monitores = new ArrayList<Monitor>();
	}
	
	public static GestorDeConsultas getInstance(){
		if (instance == null){
			instance = new GestorDeConsultas();
		}
		
		return instance;
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
