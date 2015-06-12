package grupo8.TPAnual;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grupo8.TPAnual.model.Adapters.BuscadorAdapter;
import grupo8.TPAnual.model.Adapters.RecetaAdapter;
import grupo8.TPAnual.model.Adapters.RepoRecetasAdapter;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.internal.verification.AtLeast;
import org.mockito.internal.verification.Times;

import queComemos.entrega3.dominio.Dificultad;
import queComemos.entrega3.repositorio.BusquedaRecetas;
import queComemos.entrega3.repositorio.RepoRecetas;

public class TestConsultaRecetasRepoExterno {

	private RepoRecetas repositorioExterno;
	private RecetaAdapter recetaAdapter;
	private RepoRecetasAdapter repoRecetasAdapter;
	private BuscadorAdapter buscadorAdapter;
	private BusquedaRecetas unaBusqueda;

	@Before
	public void setup() {

		repositorioExterno = mock(RepoRecetas.class);
		repoRecetasAdapter = new RepoRecetasAdapter(repositorioExterno);

		List<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("lechuga");
		palabrasClave.add("croutons");
		palabrasClave.add("parmesano");

		buscadorAdapter = new BuscadorAdapter("ensalada caesar",
				Dificultad.FACIL, palabrasClave);

		repoRecetasAdapter.setAdapter(buscadorAdapter);
	}

	@Test
	public void sePuedenListarLasRecetas() {

		repoRecetasAdapter.getRecetas();
		verify(repositorioExterno).getRecetas(any(BusquedaRecetas.class));
	}

}
