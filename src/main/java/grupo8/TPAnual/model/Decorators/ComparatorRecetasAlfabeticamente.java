package grupo8.TPAnual.model.Decorators;

import grupo8.TPAnual.model.Dominio.Receta;

import java.util.Comparator;

public class ComparatorRecetasAlfabeticamente implements Comparator<Receta> {
	
	@Override
	public int compare(Receta receta1, Receta receta2) {
		return receta1.getNombre().compareToIgnoreCase(receta2.getNombre());
	}

}
