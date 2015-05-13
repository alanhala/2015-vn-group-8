package grupo8.TPAnual.model;

import grupo8.TPAnual.exceptions.DiabeticoInvalidoException;

public class Diabetico implements Condicion {

	@Override
	public boolean esValida(Usuario usuario) {
		
		if(usuario.tieneSexo() || usuario.tienePreferenciasAlimenticias())
		{
			return true;
		}
		
		throw new DiabeticoInvalidoException("El usuario debe tener sexo y/o preferencias alimenticias");
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return usuario.pesoMenorOIgualA(70.00) || usuario.tieneRutinaIntensiva() || usuario.tieneRutinaSemiIntenisva();
	}

	public boolean esInadecuadaParaUnaReceta(Receta receta){
		return receta.tieneMasDe100GramosDeAzucar();
	}
}
