package grupo8.TPAnual.model;

import java.util.Arrays;
import java.util.List;

public class Vegano implements Condicion {

	public static final List<String> preferenciasProhibidas = Arrays.asList(
			"pollo", "carne", "chivito", "chori");

	@Override
	public boolean esValida(Usuario usuario) {

		return !(usuario.tienePreferenciasProhibidas(preferenciasProhibidas));
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {

		return usuario.leGusta("frutas");
	}

	public boolean esInadecuadaUnaReceta(Receta receta){
		return receta.tieneEstosIngredientes(preferenciasProhibidas);
	}
}
