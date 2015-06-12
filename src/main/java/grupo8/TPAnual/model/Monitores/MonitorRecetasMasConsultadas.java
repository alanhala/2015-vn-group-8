package grupo8.TPAnual.model.Monitores;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MonitorRecetasMasConsultadas implements Monitor {
	
	private Map<String, Integer> recetasConsultadas;
	
	
	public MonitorRecetasMasConsultadas() {
		recetasConsultadas = new HashMap<String, Integer>();
	}
	
	@Override
	public void actualizar(Usuario usuario, List<Receta> consulta) {
		consulta.forEach(receta -> this.agregarReceta(receta));
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
