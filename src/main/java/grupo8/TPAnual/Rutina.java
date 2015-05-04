package grupo8.TPAnual;

public class Rutina {

	private boolean esActiva;
	private int cantidadDeEjercicio;
	private boolean esConEjercicioAdicional;
	
	public Rutina(boolean activa, int unaCantidad, boolean ejAdicional) {
		esActiva = activa;
		cantidadDeEjercicio = unaCantidad;
		esConEjercicioAdicional = ejAdicional;
	}
	
	
	public int cantidadDeEjercicio() {
		return cantidadDeEjercicio;
	}
	
	public boolean esActiva() {
		return esActiva;
	}
	
	public boolean esConEjercicioAdicional() {
		return esConEjercicioAdicional;
	}
	
	
}
