package grupo8.TPAnual.model.CondicionesPreexistentes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import grupo8.TPAnual.exceptions.DiabeticoInvalidoException;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;


@Entity
@DiscriminatorValue("d")

public class Diabetico extends Condicion {

	@Override
	public void esValida(Usuario usuario) {
		
		if(!(usuario.tieneSexo() || usuario.tienePreferenciasAlimenticias()))
			throw new DiabeticoInvalidoException("El usuario debe tener sexo o preferencias alimenticias para ser un diabetico valido");
		
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return usuario.pesoMenorOIgualA(70.00) || usuario.tieneRutinaIntensiva() || usuario.tieneRutinaSemiIntenisva();
	}

	public boolean esInadecuadaParaUnaReceta(Receta receta){
		return receta.tieneMasDe100GramosDeAzucar();
	}
}
