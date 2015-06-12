package grupo8.TPAnual.model;

import java.util.List;

public class GestorDeConsultas {
	private List<Monitor> monitores;
	private Usuario ultimoUsuario;
	private List<Receta> ultimaConsulta;
	
	public void agregarMonitor(Monitor unMonitor) {
		monitores.add(unMonitor);
	}
	
	public void removerMonitor(Monitor unMonitor) {
		monitores.remove(unMonitor);
	}
	
	public void setUltimoUsuario(Usuario unUsuario) {
		ultimoUsuario = unUsuario;
	}
	
	public void setUltimaConsulta(List<Receta> unaConsulta) {
		ultimaConsulta = unaConsulta;
	}
	
	public Usuario getUltimoUsuario() {
		return ultimoUsuario;
	}
	
	public List<Receta> getUltimaConsulta() {
		return ultimaConsulta;
	}
	
	public void consultarRecetas(Usuario unUsuario, Filtro unFiltro) {
		List<Receta> ultimaConsulta = unUsuario.filtrarRecetas(unFiltro);
		this.setUltimoUsuario(unUsuario);
		this.setUltimaConsulta(ultimaConsulta);
		this.notificar();
		
	}

	private void notificar() {
		monitores.forEach(monitor -> monitor.actualizar());
	}
}
