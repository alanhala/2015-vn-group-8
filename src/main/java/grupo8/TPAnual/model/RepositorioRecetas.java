package grupo8.TPAnual.model;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.stream.Collectors;
>>>>>>> b9d31b52cc4ca14388da3f54ee26a58e046b4e2a

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
<<<<<<< HEAD
}
=======

	public List<Receta> filtrarRecetasDe(Usuario usuario, Filtro filtro){
		return filtro.filtrarRecetasDe(usuario);
	}
}
>>>>>>> b9d31b52cc4ca14388da3f54ee26a58e046b4e2a
