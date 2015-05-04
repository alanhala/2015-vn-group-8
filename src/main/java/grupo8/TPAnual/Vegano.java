package grupo8.TPAnual;

import java.util.Arrays;
import java.util.List;

public class Vegano implements Condicion {

	public static final List<String> preferenciasProhibidas = Arrays.asList(
			"pollo", "carne", "chivito", "chori");

	@Override
	public boolean esValida(Usuario usuario) {

		return (usuario.preferenciasAlimenticias.stream().anyMatch(
				preferencia -> preferenciasProhibidas.contains(preferencia)));
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		
		return (usuario.preferenciasAlimenticias.contains("frutas"));
	}

}
