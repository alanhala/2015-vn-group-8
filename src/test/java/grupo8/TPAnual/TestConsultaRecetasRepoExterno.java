package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import grupo8.TPAnual.model.Adapters.BuscadorAdapter;
import grupo8.TPAnual.model.Repositorios.RepoRecetasAdapter;
import grupo8.TPAnual.model.Dominio.Receta;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import queComemos.entrega3.dominio.Dificultad;
import queComemos.entrega3.repositorio.BusquedaRecetas;
import queComemos.entrega3.repositorio.RepoRecetas;

public class TestConsultaRecetasRepoExterno {

	private RepoRecetas repositorioExternoMock, repoExterno;
	private RepoRecetasAdapter repoRecetasAdapter;
	private BuscadorAdapter buscadorAdapter;

	@Before
	public void setup() {

		repositorioExternoMock = mock(RepoRecetas.class);
		repoExterno = new RepoRecetas();
		

		List<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("lechuga");
		palabrasClave.add("croutons");
		palabrasClave.add("parmesano");

		buscadorAdapter = new BuscadorAdapter("ensalada caesar",
				Dificultad.FACIL, palabrasClave);
	}

	@Test
	public void sePuedenListarLasRecetas() {
		repoRecetasAdapter = new RepoRecetasAdapter(repositorioExternoMock);
		repoRecetasAdapter.setAdapter(buscadorAdapter);
		
		repoRecetasAdapter.getRecetas();
		verify(repositorioExternoMock).getRecetas(any(BusquedaRecetas.class));
	}
	
	@Test
	public void busquedaDevuelveRecetaEnsaladaCaesar(){
		repoRecetasAdapter = new RepoRecetasAdapter(repoExterno);
		repoRecetasAdapter.setAdapter(buscadorAdapter);
		Receta recetaObtenida = repoRecetasAdapter.listarRecetas().get(0);
		
		assertEquals("ensalada caesar", recetaObtenida.getNombre());
		
	}
	

}
