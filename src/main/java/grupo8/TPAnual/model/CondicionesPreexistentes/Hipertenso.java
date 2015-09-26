package grupo8.TPAnual.model.CondicionesPreexistentes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import grupo8.TPAnual.exceptions.HipertensoInvalidoException;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

@Entity
@DiscriminatorValue("h")

public class Hipertenso extends Condicion {

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
