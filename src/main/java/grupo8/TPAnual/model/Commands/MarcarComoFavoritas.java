package grupo8.TPAnual.model.Commands;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.List;

public class MarcarComoFavoritas implements TratamientoDeConsultas {

	// Datos de la consulta

	private Usuario usuario;
	private List<Receta> recetasFiltradas;
	
	public MarcarComoFavoritas(){
		//Constructor vacio
	}

	public MarcarComoFavoritas(Usuario usuario,
			List<Receta> recetasFiltradas) {
		this.usuario = usuario;
		this.recetasFiltradas = recetasFiltradas;
	}

	// Este metodo se encarga de crear el objeto y enviarlo a tareas pendientes
	// en el menu
	@Override
	public void agregarAccionARealizar(Usuario usuario, Filtro filtro,
			List<Receta> recetasFiltradas) {
		usuario.getGestorDeConsultas().agregarAccionARealizar(
				new MarcarComoFavoritas(usuario, recetasFiltradas));

	}

	@Override
	public void ejecutar() {
		recetasFiltradas.forEach(receta -> usuario.agregarAFavoritos(receta));
	}

}
