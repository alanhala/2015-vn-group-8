package grupo8.TPAnual.model.Monitores;

import java.util.Arrays;
import java.util.Calendar;



public class MonitorConsultasPorHora implements Monitor {

	private GestorDeConsultas gestor;
	private Integer[] consultasPorHora;
	
	public MonitorConsultasPorHora(GestorDeConsultas unGestor) {
		gestor = unGestor;
		consultasPorHora = new Integer[24];
		Arrays.fill(consultasPorHora, 0);
		
	}
	
	@Override
	public void actualizar() {
		consultasPorHora[Calendar.HOUR_OF_DAY] ++;
	}
	
	public Integer cantidadDeConsultasPorHora(Integer hora) {
		return consultasPorHora[hora];
	}

}
