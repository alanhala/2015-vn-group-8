package grupo8.TPAnual.model.Monitores;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MonitorConsultasDeVeganosPorRecetasDificiles implements Monitor {

	Set<Usuario> veganos;
	
	public MonitorConsultasDeVeganosPorRecetasDificiles() {
		veganos = new HashSet<Usuario>();
	}
	
	@Override
	public void actualizar(Usuario usuario, List<Receta> consulta) {
		if(usuario.esVegano() && consulta.stream().anyMatch(receta -> receta.esDificil())) {
			veganos.add(usuario);
		}
	}
	
	public Integer cantidadDeVeganosConRecetasDificiles() {
		return veganos.size();
	}

}
