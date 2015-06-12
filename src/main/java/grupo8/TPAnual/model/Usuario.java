package grupo8.TPAnual.model;

import grupo8.TPAnual.exceptions.FechaDeNacimientoDeUsuarioInvalidaException;
import grupo8.TPAnual.exceptions.NombreDeUsuarioInvalidoException;
import grupo8.TPAnual.exceptions.UsuarioSinAlturaException;
import grupo8.TPAnual.exceptions.UsuarioSinFechaDeNacimientoException;
import grupo8.TPAnual.exceptions.UsuarioSinPesoException;
import grupo8.TPAnual.exceptions.UsuarioSinNombreException;
import grupo8.TPAnual.exceptions.UsuarioSinRutinaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Usuario implements Sugerible {

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
	private List<Grupo> grupos;
	private List<Receta> recetasFavoritas;
	private RepositorioDeRecetas repositorio;

	public enum Rutina {
		LEVE, NADA, MEDIANO, INTENSIVO, SEMIINTENSIVO
	}

	public Usuario(Double peso, Double altura, String nombre, String sexo,
			LocalDate fechaDeNacimiento, List<String> preferenciasAlimenticias,
			List<String> disgustosAlimenticios, List<Condicion> condiciones,
			List<Receta> recetas, Rutina rutina, List<Grupo> grupos) {
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
		this.grupos = grupos;
		this.recetasFavoritas = new ArrayList<Receta>();
		
		GestorPerfiles.seCreoNuevoPerfil(this);
	}

	public Usuario(Double peso, Double altura, String nombre,
			LocalDate fechaDeNacimiento, Rutina rutina) {
		this.peso = peso;
		this.altura = altura;
		this.nombre = nombre;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.rutina = rutina;
		this.preferenciasAlimenticias = new ArrayList<String>();
		this.disgustosAlimenticios = new ArrayList<String>();
		this.condiciones = new ArrayList<Condicion>();
		this.recetas = new ArrayList<Receta>();
		this.grupos = new ArrayList<Grupo>();
		this.recetasFavoritas = new ArrayList<Receta>();
		
		GestorPerfiles.seCreoNuevoPerfil(this);
	}

	public double calcularIMC() {
		return peso / Math.pow(altura, 2);
	}

	public List<Receta> getRecetas() {
		return Collections.unmodifiableList(recetas);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void esValido() {
		this.tieneCamposObligatorios();
		this.tieneNombreValido();
		this.tieneFechaNacimientoValida();
		this.tieneCondicionesValidas();
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

	public void tieneCondicionesValidas() {
		condiciones.forEach(condicion -> condicion.esValida(this));
	}

	public void tieneCamposObligatorios() {
		this.tieneNombre();
		this.tienePeso();
		this.tieneAltura();
		this.tieneFechaDeNacimiento();
		this.tieneRutina();
	}

	public void tieneNombre() {
		if (nombre == null)
			throw new UsuarioSinNombreException("El usuario debe tener nombre");
	}

	public void tienePeso() {
		if (peso == null)
			throw new UsuarioSinPesoException("El usuario debe tener un peso");
	}

	public void tieneAltura() {

		if (altura == null)
			throw new UsuarioSinAlturaException(
					"El usuario debe tener una altura");
	}

	public void tieneFechaDeNacimiento() {
		if (fechaDeNacimiento == null)
			throw new UsuarioSinFechaDeNacimientoException(
					"El usuario debe tener una fecha de nacimiento");
	}

	public void tieneRutina() {
		if (rutina == null)
			throw new UsuarioSinRutinaException(
					"El usuario debe tener una rutina");
	}

	public void tieneNombreValido() {
		if (nombre.length() <= 4)
			throw new NombreDeUsuarioInvalidoException(
					"El nombre del usuario debe tener 4 o mas caracteres");
	}

	public void tieneFechaNacimientoValida() {
		LocalDate today = LocalDate.now();
		if (!fechaDeNacimiento.isBefore(today))
			throw new FechaDeNacimientoDeUsuarioInvalidaException(
					"La fecha de nacimiento del usuario debe ser anterior a la del dia de hoy");
	}

	public boolean tieneSexo() { // jaja
		return (sexo != null || (!sexo.isEmpty()));
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

	public boolean puedeVerOModificar(Receta receta) {
		return receta.puedeSerVistaOModificadaPor(this);
	}

	public boolean tenesUnaReceta(Receta unaReceta) {
		return recetas.contains(unaReceta);
	}

	public void modificarRecetaPublica(Receta unaReceta) {
		unaReceta.clonar(this);
	}

	public boolean compartisGrupoCon(Usuario usuario) {
		return grupos.stream().anyMatch(
				grupo -> grupo.perteneceAlGrupo(usuario));
	}

	public void agregarAUnGrupo(Grupo grupo) {
		grupos.add(grupo);
		grupo.agregarUsuario(this);
	}

	public List<String> getDisgustosAlimenticios() {
		return Collections.unmodifiableList(disgustosAlimenticios);
	}

	public List<Condicion> getCondiciones() {
		return Collections.unmodifiableList(condiciones);
	}

	public boolean leDisgusta(Receta unaReceta) {
		return unaReceta
				.tieneEstosIngredientes(this.getDisgustosAlimenticios());
	}

	@Override
	public boolean seLePuedeSugerir(Receta unaReceta) {
		return !this.leDisgusta(unaReceta)
				&& unaReceta.cumpleCondicionesPara(this);
	}

	public void agregarAFavoritos(Receta receta) {
		recetasFavoritas.add(receta);
	}

	public boolean tieneSobrePeso() {
		return this.calcularIMC() >= 25;
	}

	public Set<Receta> getRecetasGrupalesYPropias() {
		Set<Receta> recetasAccesibles = new HashSet<Receta>();
		grupos.forEach(grupo -> recetasAccesibles.addAll(grupo
				.getRecetasDelGrupo()));
		recetasAccesibles.addAll(this.recetas);
		return recetasAccesibles;
	}
	
	public List<Receta> getRecetasAccesibles() {
		List<Receta> recetasFiltradas = new ArrayList<Receta>();
		recetasFiltradas.addAll(repositorio.listarRecetas());
		recetasFiltradas.addAll(this.getRecetasGrupalesYPropias());
		return recetasFiltradas;
	}
	
	public List<Receta> filtrarRecetas(Filtro filtro){
		List<Receta> recetasAFiltrar = this.getRecetasAccesibles();
		return filtro.filtrarRecetasDe(this,recetasAFiltrar);
	}

	public Boolean tieneMismoNombreQue(Usuario usuario) {
		return this.getNombre().equals(usuario.getNombre());
	}

	public Boolean nombreContieneNombreDe(Usuario usuario) {
		return this.getNombre().contains(usuario.getNombre());
	}

	public Boolean tieneLasCondicionesDe(Usuario usuario) {
		return usuario.condiciones.stream().allMatch(c -> this.condiciones.contains(c));
	}
	
	public void agregarRepositorio(RepositorioDeRecetas repositorio){
		this.repositorio = repositorio;
	}

}