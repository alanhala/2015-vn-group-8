package grupo8.TPAnual.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class MonitorRecetasMasConsultadas implements Monitor {
	
	private GestorDeConsultas gestor;
	private Map<String, Integer> recetasConsultadas;
	
	
	public MonitorRecetasMasConsultadas(GestorDeConsultas unGestor) {
		gestor = unGestor;
		recetasConsultadas = new HashMap<String, Integer>();
	}
	
	@Override
	public void actualizar() {
		gestor.getUltimaConsulta().forEach(receta -> this.agregarReceta(receta));
	}

	private void agregarReceta(Receta receta) {
		if(recetasConsultadas.containsKey(receta.getNombre())) {
			recetasConsultadas.put(receta.getNombre(), recetasConsultadas.get(receta.getNombre()) +1);
		} else recetasConsultadas.put(receta.getNombre(), 1);
	}

	public String recetaMasConsultada () {
		return Collections.max(recetasConsultadas.entrySet(), (entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).getKey();
	}


}
