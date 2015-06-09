package grupo8.TPAnual.model;

import java.util.Comparator;

public class ComparatorRecetasPorCalorias implements Comparator<Receta> {

	@Override
	public int compare(Receta receta1, Receta receta2) {
		return receta1.calorias().intValue() - receta2.calorias().intValue();
	}

}
