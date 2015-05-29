package grupo8.TPAnual.model;

import java.util.ArrayList;
import java.util.List;

public class RepositorioRecetas {

	public static List<Receta> recetas = new ArrayList<Receta>();

	public static void agregar(Receta receta) {
		recetas.add(receta);
	}

	public static boolean tieneUnaReceta(Receta receta) {
		return recetas.contains(receta);
	}

	public void quitar(Receta receta) {
		recetas.remove(receta);
	}

	public List<Receta> listarRecetas() {
		return recetas;
	}

	public List<Receta> filtrarRecetasDe(Usuario usuario) {
		List<Receta> recetasFiltradas = new ArrayList<Receta>();
		recetasFiltradas.addAll(this.listarRecetas());
		recetasFiltradas.addAll(usuario.getRecetasAccesibles());
		return recetasFiltradas;
	}

	public boolean seLePuedeSugerirUnaReceta(Receta receta, Sugerible sugerible) {
		return sugerible.seLePuedeSugerir(receta);
	}

}