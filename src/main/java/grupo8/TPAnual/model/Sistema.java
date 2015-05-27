package grupo8.TPAnual.model;

import java.util.List;
import java.util.stream.Collectors;

public class Sistema {
	
	private List<Receta> Recetas;
	
	public void agregar(Receta receta){
		Recetas.add(receta);
	}
	
	public void quitar(Receta receta){
		Recetas.remove(receta);
	}
	
	

}
