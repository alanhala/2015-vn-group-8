package grupo8.TPA;

import java.util.Arrays;
import java.util.List;

public class Vegano implements Condicion {

	public static final List<String> preferenciasProhibidas = Arrays.asList(
			"pollo", "carne", "chivito", "chori");

	@Override
	public boolean esValida(Usuario usuario) {

		if (usuario.preferenciasAlimenticias.stream().anyMatch(
				preferencia -> preferenciasProhibidas.contains(preferencia))) {
			return false;
		} else
			return true;
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		
		if (usuario.preferenciasAlimenticias.contains("frutas"))
			return true;
		else return false;
	}

}
