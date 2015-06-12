package grupo8.TPAnual.model.CondicionesPreexistentes;

import grupo8.TPAnual.exceptions.VeganoInvalidoException;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.Arrays;
import java.util.List;

public class Vegano implements Condicion {

	public static final List<String> preferenciasProhibidas = Arrays.asList(
			"pollo", "carne", "chivito", "chori");

	@Override
	public void esValida(Usuario usuario) {

		if (usuario.tienePreferenciasProhibidas(preferenciasProhibidas))
			throw new VeganoInvalidoException(
					"El usuario no debe tener pollo, carne, chivito o chori entre sus preferencias alimenticias para ser un vegano valido");
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {

		return usuario.leGusta("frutas");
	}

	public boolean esInadecuadaParaUnaReceta(Receta receta) {
		return receta.tieneEstosIngredientes(preferenciasProhibidas);
	}
}
