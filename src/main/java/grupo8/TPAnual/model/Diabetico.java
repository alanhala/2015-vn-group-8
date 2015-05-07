package grupo8.TPAnual.model;

public class Diabetico implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		return usuario.tieneSexo() || usuario.tienePreferenciasAlimenticias();
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return usuario.pesoMenorOIgualA(70.00) || usuario.tieneRutinaActiva();
	}

}