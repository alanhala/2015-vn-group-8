package grupo8.TPAnual.model;


public class Diabetico implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		
		if(usuario.tieneSexo() || usuario.tienePreferenciasAlimenticias())
		{
			return true;
		}
		else return false; //TODO ACA IRIA LA EXCEPCION
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return usuario.pesoMenorOIgualA(70.00) || usuario.tieneRutinaIntensiva() || usuario.tieneRutinaSemiIntenisva();
	}

	public boolean esInadecuadaParaUnaReceta(Receta receta){
		return receta.tieneMasDe100GramosDeAzucar();
	}
}
