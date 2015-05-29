package grupo8.TPAnual.model;

import java.util.List;

public class RepositorioRecetas {

	private List<Receta> recetas;

	public void agregar(Receta receta) {
		recetas.add(receta);
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