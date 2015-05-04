package grupo8.TPA;

public class Diabetico implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		return !(usuario.sexo.isEmpty() || usuario.sexo == null || usuario.preferenciasAlimenticias.isEmpty());
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return (usuario.peso() < 70 || usuario.rutina.esActiva);
	}

}
