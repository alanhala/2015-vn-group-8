package grupo8.TPAnual.model.Monitores;

import grupo8.TPAnual.model.Dominio.Receta;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MonitorRecetasSegunSexo implements Monitor {

	private GestorDeConsultas gestor;
	private Map<String, Integer> consultasHombres;
	private Map<String, Integer> consultasMujeres;
	
	public MonitorRecetasSegunSexo(GestorDeConsultas unGestor) {
		gestor = unGestor;
		consultasHombres = new HashMap<String, Integer>();
		consultasMujeres = new HashMap<String, Integer>();
	}
	
	@Override
	public void actualizar() {
		if(gestor.getUltimoUsuario().esHombre()) {
			gestor.getUltimaConsulta().forEach(receta -> this.agregarReceta(receta, consultasHombres));
		} else gestor.getUltimaConsulta().forEach(receta -> this.agregarReceta(receta, consultasMujeres));

	}

	private void agregarReceta(Receta receta, Map<String, Integer> consultas) {
		if(consultas.containsKey(receta.getNombre())) {
			consultas.put(receta.getNombre(), consultas.get(receta.getNombre()) +1);
		} else consultas.put(receta.getNombre(), 1);
	}
	
	public String recetaMasConsultadaPorHombres() {
		return Collections.max(consultasHombres.entrySet(), (entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).getKey();

	}
	
	public String recetaMasConsultadaPorMujeres() {
		return Collections.max(consultasMujeres.entrySet(), (entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).getKey();
	}

}
