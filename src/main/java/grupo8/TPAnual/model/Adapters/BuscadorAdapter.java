package grupo8.TPAnual.model.Adapters;

import java.util.ArrayList;
import java.util.List;

import queComemos.entrega3.dominio.Dificultad;
import queComemos.entrega3.repositorio.BusquedaRecetas;

public class BuscadorAdapter {
	
	private String nombre;
	private Dificultad dificultad;
	private List<String> palabrasClave = new ArrayList<String>();
	private BusquedaRecetas buscador;
	
	public BuscadorAdapter(String nombre, Dificultad dificultad, List<String> palabrasClave){
		this.nombre = nombre;
		this.dificultad = dificultad;
		this.palabrasClave = palabrasClave;
	}
	
	public BusquedaRecetas getBuscador(){
		buscador = new BusquedaRecetas();
		buscador.setDificultad(dificultad);
		buscador.setNombre(nombre);
		palabrasClave.forEach(palabra -> buscador.agregarPalabraClave(palabra));
		return buscador;
	}	
	

}