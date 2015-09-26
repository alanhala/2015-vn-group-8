package grupo8.TPAnual.model.CondicionesPreexistentes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;


@Entity
@DiscriminatorValue("c")

public class Celiaco extends Condicion {

	@Override
	public void esValida(Usuario usuario) {
		//No hace nada, el celiaco es siempre valido.
	}

	@Override
	public boolean esSubsanada(Usuario usuario) {
		return true;
	}
	
	public boolean esInadecuadaParaUnaReceta(Receta receta){
		return false;
	}
}
