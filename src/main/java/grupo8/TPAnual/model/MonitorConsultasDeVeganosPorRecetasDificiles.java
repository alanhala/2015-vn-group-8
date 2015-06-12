package grupo8.TPAnual.model;

import java.util.HashSet;
import java.util.Set;

public class MonitorConsultasDeVeganosPorRecetasDificiles implements Monitor {

	GestorDeConsultas gestor;
	Set<Usuario> veganos;
	
	public MonitorConsultasDeVeganosPorRecetasDificiles(GestorDeConsultas unGestor) {
		gestor = unGestor;
		veganos = new HashSet<Usuario>();
	}
	
	@Override
	public void actualizar() {
		Usuario ultimoUsuario = gestor.getUltimoUsuario();
		if(ultimoUsuario.esVegano()) {
			veganos.add(ultimoUsuario);
		}
	}
	
	public Integer cantidadDeVeganosConRecetasDificiles() {
		return veganos.size();
	}

}
