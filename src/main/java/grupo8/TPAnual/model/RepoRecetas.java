package grupo8.TPAnual.model;

import java.util.ArrayList;
import java.util.List;


public class RepoRecetas implements RepositorioDeRecetas {

	private static RepoRecetas instance;
	private List<Receta> recetas = new ArrayList<Receta>();
	
	private RepoRecetas(){
		
	}
	
	public static RepoRecetas getInstance(){
		if (instance == null){
			instance = new RepoRecetas();
		}
		return instance;
	}

	public void agregar(Receta receta) {
		recetas.add(receta);
	}
	
	public boolean tieneUnaReceta(Receta receta){
		return recetas.contains(receta);
	}

	public void quitar(Receta receta) {
		recetas.remove(receta);
	}
	
	public List<Receta> listarRecetas(){
		return recetas;
	}
		
}