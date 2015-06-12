package grupo8.TPAnual.model.Repositorios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import grupo8.TPAnual.model.Adapters.BuscadorAdapter;
import grupo8.TPAnual.model.Adapters.RecetaAdapter;
import grupo8.TPAnual.model.Dominio.Receta;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import queComemos.entrega3.repositorio.BusquedaRecetas;
import queComemos.entrega3.repositorio.RepoRecetas;

public class RepoRecetasAdapter implements RepositorioDeRecetas {

	private RepoRecetas repoRecetas;
	private BuscadorAdapter adapter;
	
	public RepoRecetasAdapter(RepoRecetas repo){
		this.repoRecetas = repo;
	}

	public List<RecetaAdapter> getRecetas() {
		BusquedaRecetas buscador = adapter.getBuscador();		
		String resultJson = repoRecetas.getRecetas(buscador);
		final Type tipoListaRecetasAdapter = new TypeToken<List<RecetaAdapter>>() {
		}.getType();
		List<RecetaAdapter> recetasBuscadas = new Gson().fromJson(resultJson,
				tipoListaRecetasAdapter);
		return recetasBuscadas;
	}

	public List<Receta> listarRecetas() {
		List<RecetaAdapter> recetasAModificar = this.getRecetas();
		List<Receta> recetasModificadas = new ArrayList<Receta>();
		recetasAModificar.forEach(recetaAModificar -> recetasModificadas.add(recetaAModificar.getReceta()));
		return recetasModificadas;
		
	}

	public void setAdapter(BuscadorAdapter adapter) {
		this.adapter = adapter;
	}

	public BuscadorAdapter getAdapter() {
		return adapter;
	}
	
	 
}