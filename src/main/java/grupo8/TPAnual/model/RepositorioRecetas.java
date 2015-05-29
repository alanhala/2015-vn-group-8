package grupo8.TPAnual.model;

import java.util.ArrayList;
import java.util.List;

public class RepositorioRecetas {

	private static List<Receta> recetas = new ArrayList<Receta>();

	public static void agregar(Receta receta) {
		recetas.add(receta);
	}
	
	public static boolean tieneUnaReceta(Receta receta){
		return recetas.contains(receta);
	}

	public void quitar(Receta receta) {
		recetas.remove(receta);
	}
	
	public List<Receta> listarRecetas(){
		return recetas;
	}

	public List<Receta> filtrarRecetasDe(Usuario usuario, Filtro filtro){
		return filtro.filtrarRecetasDe(usuario);
	}
}