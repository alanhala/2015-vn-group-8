package grupo8.TPAnual.model.CondicionesPreexistentes;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="condiciones")

public abstract class Condicion {

	@Id
	@GeneratedValue
	public long id;
	
	public void esValida(Usuario usuario) {
	}

	public boolean esSubsanada(Usuario usuario) {
		return false;
	}

	public boolean esInadecuadaParaUnaReceta(Receta receta) {
		return false;
	}

}
