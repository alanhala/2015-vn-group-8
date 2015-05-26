package grupo8.TPAnual.model;

import grupo8.TPAnual.exceptions.HipertensoInvalidoException;


public class Hipertenso implements Condicion {

	@Override
	public void esValida(Usuario usuario) {
		if(!usuario.tienePreferenciasAlimenticias())
			throw new HipertensoInvalidoException("El usuario debe tener preferencias alimenticias para ser un hipertenso valido");
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return usuario.tieneRutinaIntensiva();
	}
	
	public boolean esInadecuadaParaUnaReceta(Receta receta) {
		return receta.tieneSalOCaldo();

	}

}
