package grupo8.TPAnual.model;

import grupo8.TPAnual.exceptions.HipertensoInvalidoException;

public class Hipertenso implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		if(usuario.tienePreferenciasAlimenticias())
		{
			return true;
		}
		
		throw new HipertensoInvalidoException("El usuario debe tener preferencias alimenticias");
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return usuario.tieneRutinaIntensiva();
	}
	
	public boolean esInadecuadaParaUnaReceta(Receta receta) {
		return receta.tieneSalOCaldo();

	}

}
