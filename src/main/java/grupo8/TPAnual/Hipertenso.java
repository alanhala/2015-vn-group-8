package grupo8.TPAnual;

public class Hipertenso implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		return usuario.tienePreferenciasAlimenticias();
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return usuario.tieneRutinaActiva() && usuario.tieneRutinaConEjercicioAdicional();

	}

}
