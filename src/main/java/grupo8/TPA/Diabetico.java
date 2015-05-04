package grupo8.TPA;

public class Diabetico implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		if (usuario.sexo.isEmpty() || usuario.sexo == null || usuario.preferenciasAlimenticias.isEmpty())
			return false;
		else return true;
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		if(usuario.peso() < 70 || usuario.rutina.esActiva) //TODO Arreglar lo de la rutina
			return true;
		else return false;
	}

}
