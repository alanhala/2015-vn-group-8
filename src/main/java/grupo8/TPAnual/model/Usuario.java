package grupo8.TPAnual.model;

import java.time.LocalDate;
import java.util.List;

public class Usuario {

	private Double peso;
	private Double altura;
	private String nombre;
	private String sexo;
	private LocalDate fechaDeNacimiento;
	private List<String> preferenciasAlimenticias;
	private List<String> disgustosAlimenticios;
	private List<Condicion> condiciones;
	private List<Receta> recetas;
	private Rutina rutina;

	public Usuario(Double unPeso, Double unaAltura) {
		peso = unPeso;
		altura = unaAltura;
		
		if(!this.esValido())
		{
			throw new UsuarioInvalidoException("Usuario invalido, por favor, ingrese los datos correctamente");
		}
	}

	public double calcularIMC() {
		return peso / Math.pow(altura, 2);
	}

	public boolean esValido() {
		return (this.tieneCamposObligatorios() && this.tieneNombreValido()
				&& this.fechaNacimientoValida() && this
					.tieneCondicionesValidas()); // falta verificar la condicion
	}

	public boolean sigueRutinaSaludable() {
		return ((this.tieneIMCEntre(18.00, 30.00) && !(condiciones.isEmpty())) || this
				.condicionesSonSubsanadas());
	}

	private boolean condicionesSonSubsanadas() {
		return condiciones.stream().allMatch(
				condicion -> condicion.esSubsanada(this));
	}

	private boolean tieneIMCEntre(double limiteInferior, double limiteSuperior) {
		return this.calcularIMC() >= limiteInferior
				&& this.calcularIMC() <= limiteSuperior;
	}

	private boolean tieneCondicionesValidas() {
		return condiciones.stream().allMatch(
				condicion -> condicion.esValida(this));
	}

	public boolean tieneCamposObligatorios() {
		return (nombre != null && peso != null && altura != null
				&& fechaDeNacimiento != null && rutina != null);
	}

	public boolean tieneNombreValido() {
		return nombre.length() > 4;
	}

	public boolean fechaNacimientoValida() {
		LocalDate today = LocalDate.now();
		return (fechaDeNacimiento.isBefore(today));

	}

	public boolean tieneSexo() {
		return sexo != null || !(sexo.isEmpty());
	}

	public boolean tienePreferenciasAlimenticias() {
		return !(preferenciasAlimenticias.isEmpty());
	}

	public boolean noTienePrefenciasProhibidas(
			List<String> preferenciasProhibidas) {
		return preferenciasAlimenticias.stream().anyMatch(
				preferencia -> preferenciasProhibidas.contains(preferencia));
	}

	public boolean leGusta(String comida) {
		return preferenciasAlimenticias.contains(comida);
	}

	public boolean tieneRutinaActiva() {
		return rutina.esActiva();
	}

	public boolean tieneRutinaConEjercicioAdicional() {
		return rutina.esConEjercicioAdicional();
	}

	public boolean pesoMenorOIgualA(Double unPeso) {
		return peso <= unPeso;
	}

}
