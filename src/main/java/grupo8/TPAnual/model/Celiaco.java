package grupo8.TPAnual.model;

public class Celiaco implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		return true;
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return true;
	}

}
