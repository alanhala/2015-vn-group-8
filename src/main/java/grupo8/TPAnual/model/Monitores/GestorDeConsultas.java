package grupo8.TPAnual.model.Monitores;

import grupo8.TPAnual.model.Commands.TratamientoDeConsultas;
import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import Persistencia.PersistentEntity;

@Entity
@Table(name="GestoresDeConsultas")
public class GestorDeConsultas extends PersistentEntity {
	
	@Transient
	private List<Monitor> monitores;
	
	@Transient
	private List<TratamientoDeConsultas> accionesARealizar;

	public GestorDeConsultas() {
		monitores = new ArrayList<Monitor>();
		accionesARealizar = new ArrayList<TratamientoDeConsultas>();
	}
	
	public void ejecutarAcciones() {
		accionesARealizar.forEach(accion -> accion.ejecutar());
		accionesARealizar.clear();
	}
	
	public void agregarAccionARealizar(TratamientoDeConsultas unaTarea) {
		accionesARealizar.add(unaTarea);
	}

	public void agregarMonitor(Monitor unMonitor) {
		monitores.add(unMonitor);
	}

	public void removerMonitor(Monitor unMonitor) {
		monitores.remove(unMonitor);
	}

	public void notificar(Usuario usuario, List<Receta> consulta) {
		monitores.forEach(monitor -> monitor.actualizar(usuario, consulta));
	}

	public List<TratamientoDeConsultas> getAccionesARealizar() {
		return accionesARealizar;
	}
	
	//Metodo auxiliar para tests
	public boolean hayAccionesARealizar() {
		return !accionesARealizar.isEmpty();
	}
}