package grupo8.TPAnual.model;

import grupo8.TPAnual.exceptions.UsuarioSinAlturaException;
import grupo8.TPAnual.exceptions.UsuarioSinFechaDeNacimientoException;
import grupo8.TPAnual.exceptions.UsuarioSinPesoException;
import grupo8.TPAnual.exceptions.UsuarioInvalidoException;
import grupo8.TPAnual.exceptions.UsuarioSinNombreException;

import java.time.LocalDate;
import java.util.List;

public class Usuario {

	private static List<Receta> recetasPublicas;
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

	public enum Rutina {
		LEVE, NADA, MEDIANO, INTENSIVO, SEMIINTENSIVO
	}

	public Usuario(Double peso, Double altura, String nombre, String sexo,
			LocalDate fechaDeNacimiento, List<String> preferenciasAlimenticias,
			List<String> disgustosAlimenticios, List<Condicion> condiciones,
			List<Receta> recetas, Rutina rutina) {
		super();
		this.peso = peso;
		this.altura = altura;
		this.nombre = nombre;
		this.sexo = sexo;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.preferenciasAlimenticias = preferenciasAlimenticias;
		this.disgustosAlimenticios = disgustosAlimenticios;
		this.condiciones = condiciones;
		this.recetas = recetas;
		this.rutina = rutina;
	}

	public Usuario(Double peso, Double altura, String nombre,
			LocalDate fechaDeNacimiento, Rutina rutina) {
		super();
		this.peso = peso;
		this.altura = altura;
		this.nombre = nombre;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.rutina = rutina;

	}

	public double calcularIMC() {
		return peso / Math.pow(altura, 2);
	}

	public boolean esValido() {
		return (this.tieneCamposObligatorios() && this.tieneNombreValido()
				&& this.fechaNacimientoValida() && this
					.tieneCondicionesValidas());
	}

	public boolean sigueRutinaSaludable() {
		return ((this.tieneIMCEntre(18.00, 30.00) && condiciones.isEmpty()) || this
				.condicionesSonSubsanadas());
	}

	public boolean condicionesSonSubsanadas() {
		return condiciones.stream().allMatch(
				condicion -> condicion.esSubsanada(this));
	}

	public boolean tieneIMCEntre(double limiteInferior, double limiteSuperior) {
		return this.calcularIMC() >= limiteInferior
				&& this.calcularIMC() <= limiteSuperior;
	}

	public boolean tieneCondicionesValidas() {
		return condiciones.stream().allMatch(
				condicion -> condicion.esValida(this));
	}

	public boolean tieneCamposObligatorios() {
		return (this.tieneNombre() && this.tienePeso() && this.tieneAltura()
				&& this.tieneFechaDeNacimiento() && this.tieneRutina());
	}
	
	public boolean tieneNombre() {	
		if(nombre!=null)
		{
			return true;
		}
		
		throw new UsuarioSinNombreException("El usuario debe tener nombre");
	}
	
	public boolean tienePeso() {
		
		if(peso!=null)
		{
			return true;
		}
		
		throw new UsuarioSinPesoException("El usuario debe tener un peso");
	}
	
	public boolean tieneAltura() {
		
		if(altura!=null)
		{
			return true;
		}
		
		throw new UsuarioSinAlturaException("El usuario debe tener una altura");
	}
	
	public boolean tieneFechaDeNacimiento() {
		
		if(fechaDeNacimiento!=null)
		{
			return true;
		}
		
		throw new UsuarioSinFechaDeNacimientoException("El usuario debe tener una fecha de nacimiento");
	}
	
	public boolean tieneRutina() {
		
		if(rutina!=null)
		{
			return true;
		}
		
		throw new NoTieneRutinaException("El usuario debe tener una rutina");
	}

	public boolean tieneNombreValido() {
		return nombre.length() > 4;
	}

	public boolean fechaNacimientoValida() {
		LocalDate today = LocalDate.now();
		return (fechaDeNacimiento.isBefore(today));
	}

	public boolean tieneSexo() { // jaja
		return sexo != null || !(sexo.isEmpty());
	}

	public boolean tienePreferenciasAlimenticias() {
		return !(preferenciasAlimenticias.isEmpty());
	}

	public boolean tienePreferenciasProhibidas(
			List<String> preferenciasProhibidas) {
		return preferenciasAlimenticias.stream().anyMatch(
				preferencia -> preferenciasProhibidas.contains(preferencia));
	}

	public boolean leGusta(String comida) {
		return preferenciasAlimenticias.contains(comida);
	}

	public boolean tieneRutinaIntensiva() {
		return rutina.equals(Rutina.INTENSIVO);
	}

	public boolean tieneRutinaSemiIntenisva() {
		return rutina.equals(Rutina.SEMIINTENSIVO);
	}

	public boolean pesoMenorOIgualA(Double unPeso) {
		return peso <= unPeso;
	}

	public void agregarUnaReceta(Receta receta) {
		recetas.add(receta);
	}

	public boolean puedeVer(Receta receta) {
		return receta.puedeSerVistaPor(this);
	}
	
	public boolean puedeModificar(Receta receta){
		return receta.puedeSerModificadaPor(this);
	}
	
	public boolean tenesUnaReceta(Receta unaReceta){
		return recetas.contains(unaReceta);
	}
	
	public void modificarRecetaPublica(Receta unaReceta)
	{
			this.recetas.add(unaReceta.clonar(this));
	}
	
}
