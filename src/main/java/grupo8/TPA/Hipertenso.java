package grupo8.TPA;

public class Hipertenso implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		if(usuario.preferenciasAlimenticias.isEmpty())
			return false;
		else return true;
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		if (usuario.rutina.esActivaConEjAdicional()) //TODO Arreglar lo de la rutina
			return true;
		else return false;
	}

}
