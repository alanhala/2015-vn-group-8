package grupo8.TPAnual.model.Repositorios;

import grupo8.TPAnual.model.Dominio.Receta;

import java.util.ArrayList;
import java.util.List;

public class RepoRecetas implements RepositorioDeRecetas {
	
	private List<Receta> recetas;

	public RepoRecetas() {
		this.recetas = new ArrayList<Receta>();
	}

	public void agregar(Receta receta) {
		recetas.add(receta);
	}

	public boolean tieneUnaReceta(Receta receta) {
		return recetas.contains(receta);
	}

	public void quitar(Receta receta) {
		recetas.remove(receta);
	}

	public List<Receta> listarRecetas() {
		return recetas;
	}

}

//TODO preguntar si esta bien implementado sin el singleton