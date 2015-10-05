package grupo8.TPAnual.model.Commands;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;

@Entity
@DiscriminatorValue("LC")
public class LogConsulta extends TratamientoDeConsultas {
	
	@Id
	@GeneratedValue
	private Long id;

	@Transient //TODO Ver si hay que persistir esto
	private static Log log;
	
	@OneToMany
	@JoinColumn(name = "log_consulta_id")
	private List<Receta> recetasConsultadas;

	public LogConsulta(List<Receta> recetasConsultadas) {
		this.recetasConsultadas = recetasConsultadas;
	}

	@Override
	public void agregarAccionARealizar(Usuario usuario, Filtro filtro,
			List<Receta> recetasFiltradas) {
		usuario.getGestorDeConsultas().agregarAccionARealizar(
				new LogConsulta(recetasConsultadas));

	}

	@Override
	public void ejecutar() {
		//Ven, ac�... meten en vez de en el usuario el checkeo ac�, y siempre agregan un log. 
		//Cuando me vean cuentenme que les parece.
		log.info(recetasConsultadas);
	}
}
