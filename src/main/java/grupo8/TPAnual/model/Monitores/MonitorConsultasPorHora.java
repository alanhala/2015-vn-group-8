package grupo8.TPAnual.model.Monitores;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;



public class MonitorConsultasPorHora implements Monitor {

	private Integer[] consultasPorHora;
	
	public MonitorConsultasPorHora() {
		consultasPorHora = new Integer[24];
		Arrays.fill(consultasPorHora, 0);
		
	}
	
	@Override
	public void actualizar(Usuario usuario, List<Receta> consulta) {
		consultasPorHora[Calendar.HOUR_OF_DAY] ++;
	}
	
	public Integer cantidadDeConsultasPorHora(Integer hora) {
		return consultasPorHora[hora];
	}

}
