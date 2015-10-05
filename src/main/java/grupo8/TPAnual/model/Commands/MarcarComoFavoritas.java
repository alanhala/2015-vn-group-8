package grupo8.TPAnual.model.Commands;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("MF")
public class MarcarComoFavoritas extends TratamientoDeConsultas {
	
	// Datos de la consulta

	@OneToOne
	private Usuario usuario;
	
	@OneToMany
	@JoinColumn(name = "marcar_como_favoritas_id")
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
		//TODO Que pasa ac� cuando la receta ya estaba en la lista de favoritos? 
		//Hay duplicados? Me parece que pifiaron en el tipo de la lista de favoritos :) revisenlo.
	}

}
