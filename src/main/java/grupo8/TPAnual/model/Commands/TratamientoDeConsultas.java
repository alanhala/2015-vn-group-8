package grupo8.TPAnual.model.Commands;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import Persistencia.PersistentEntity;
import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

@Entity
@Table(name = "TratamientosDeConsultas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DiscriminatorTratamientoDeConsulta")
public abstract class TratamientoDeConsultas extends PersistentEntity{

	public void agregarAccionARealizar(Usuario usuario, Filtro filtro,
			List<Receta> recetasFiltradas) {
	}
	
	public void ejecutar() {
	}
}