package grupo8.TPAnual.model;


public class Hipertenso implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		if(usuario.tienePreferenciasAlimenticias())
		{
			return true;
		}
		
		else return false; //TODO ACA IRIA LA EXCEPCION
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return usuario.tieneRutinaIntensiva();
	}
	
	public boolean esInadecuadaParaUnaReceta(Receta receta) {
		return receta.tieneSalOCaldo();

	}

}
