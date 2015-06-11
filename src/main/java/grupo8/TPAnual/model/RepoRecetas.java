package grupo8.TPAnual.model;

import java.util.ArrayList;
import java.util.List;

//TODO Convertir esto en un singleton
public class RepoRecetas implements RepositorioDeRecetas {

	public static List<Receta> recetas = new ArrayList<Receta>();

	public static void agregar(Receta receta) {
		recetas.add(receta);
	}
	
	public static boolean tieneUnaReceta(Receta receta){
		return recetas.contains(receta);
	}

	public static void quitar(Receta receta) {
		recetas.remove(receta);
	}
	
	public List<Receta> listarRecetas(){
		return recetas;
	}
		
}