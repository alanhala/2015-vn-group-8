package grupo8.TPAnual.model;

public class Hipertenso implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		return usuario.tienePreferenciasAlimenticias();
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return usuario.tieneRutinaActiva() && usuario.tieneRutinaConEjercicioAdicional();
	}
	
	public boolean esInadecuadaUnaReceta(Receta receta) {
		return receta.tieneSalOCaldo();

	}

}
