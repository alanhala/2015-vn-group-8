package grupo8.TPA;

public class Hipertenso implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		return !(usuario.preferenciasAlimenticias.isEmpty());
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return (usuario.rutina.esActiva() && usuario.rutina.esConEjercicioAdicional());

	}

}
