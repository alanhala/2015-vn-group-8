package grupo8.TPAnual.model;

public class Hipertenso implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		return usuario.tienePreferenciasAlimenticias();
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return usuario.tieneRutinaIntensiva();
	}
	
	public boolean esInadecuadaParaUnaReceta(Receta receta) {
		return receta.tieneSalOCaldo();

	}

}
