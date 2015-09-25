package grupo8.TPAnual.model.Repositorios;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Recetario implements RepositorioDeRecetas {

	private List<Receta> recetas;

	public Recetario() {
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
	
	public List<Receta> listarRecetasAccesibles(Usuario usuario){
		List<Receta> recetasAccesibles = new ArrayList();
		recetasAccesibles.addAll(listarRecetas());
		recetasAccesibles.addAll(usuario.getRecetasGrupalesYPropias());
		return recetasAccesibles;
	}
	
	public List<Receta> filtrarRecetas(Usuario usuario, Filtro filtro){
		List<Receta> recetasAFiltrar = listarRecetasAccesibles(usuario);
		List<Receta> recetasFiltradas = filtro.filtrarRecetasDe(usuario, recetasAFiltrar);
		usuario.gestionarConsulta(recetasFiltradas, filtro);
		return recetasFiltradas;
	}

}
