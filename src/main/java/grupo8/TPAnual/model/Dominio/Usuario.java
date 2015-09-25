package grupo8.TPAnual.model.Dominio;

import grupo8.TPAnual.exceptions.FechaDeNacimientoDeUsuarioInvalidaException;
import grupo8.TPAnual.exceptions.NombreDeUsuarioInvalidoException;
import grupo8.TPAnual.exceptions.UsuarioSinAlturaException;
import grupo8.TPAnual.exceptions.UsuarioSinFechaDeNacimientoException;
import grupo8.TPAnual.exceptions.UsuarioSinPesoException;
import grupo8.TPAnual.exceptions.UsuarioSinNombreException;
import grupo8.TPAnual.exceptions.UsuarioSinRutinaException;
import grupo8.TPAnual.model.Commands.LogConsulta;
import grupo8.TPAnual.model.Commands.TratamientoDeConsultas;
import grupo8.TPAnual.model.CondicionesPreexistentes.Condicion;
import grupo8.TPAnual.model.CondicionesPreexistentes.Vegano;
import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.Monitores.GestorDeConsultas;
import grupo8.TPAnual.model.Dominio.Rutina;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Sugerible {

	@Id
	@GeneratedValue
	private Long id;
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
	private GestorDeConsultas gestorDeConsultas;
	private List<TratamientoDeConsultas> tratamientosDeConsultas;

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
		this.tratamientosDeConsultas = new ArrayList<TratamientoDeConsultas>();
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
		this.tratamientosDeConsultas = new ArrayList<TratamientoDeConsultas>();
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
			List
			<String> preferenciasProhibidas) {
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

	public void agregarReceta(Receta receta) {
		recetas.add(receta);
	}

	public boolean puedeVerOModificar(Receta receta) {
		return receta.esPublica() || this.tieneAcceso(receta);
	}

	public boolean tenesUnaReceta(Receta unaReceta) {
		return recetas.contains(unaReceta);
	}

	public void modificarRecetaPublica(Receta unaReceta) {
		this.agregarReceta(unaReceta.clonar());
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

	public List<Receta> getRecetasGrupalesYPropias() {
		Set<Receta> recetasAccesibles = new HashSet<Receta>();
		grupos.forEach(grupo -> recetasAccesibles.addAll(grupo
				.getRecetasDelGrupo()));
		recetasAccesibles.addAll(this.recetas);
		List<Receta> listadoDeRecetas = new ArrayList();
		listadoDeRecetas.addAll(recetasAccesibles);
		return listadoDeRecetas;
	}
	
	public void gestionarConsulta(List<Receta> recetasFiltradas, Filtro filtro){
		gestorDeConsultas.notificar(this, recetasFiltradas);
		this.tratarConsulta(filtro, recetasFiltradas);
	}
	
	// Este metodo crea las acciones correspondientes a realizar y las manda al
	// gestor de consultas
	private void tratarConsulta(Filtro filtro, List<Receta> recetasFiltradas) {

		if (recetasFiltradas.size() > 100) {
			gestorDeConsultas.agregarAccionARealizar(new LogConsulta(recetasFiltradas));
		}
		//TODO Porque hace el checkeo de cuantos resultados tienen ac�? 
		//No les parece mejor agregar siempre un log, 
		//y hacer que dentro del log se fije si tiene que loguear o no segun la cantidad de resultados? 

		this.tratamientosDeConsultas.forEach(t -> t.agregarAccionARealizar(
				this, filtro, recetasFiltradas));

	}

	public boolean tieneMismoNombreQue(Usuario usuario) {
		return this.getNombre().equals(usuario.getNombre());
	}

	public boolean nombreContieneNombreDe(Usuario usuario) {
		return this.getNombre().contains(usuario.getNombre());
	}

	public boolean tieneLasCondicionesDe(Usuario usuario) {
		return usuario.condiciones.stream().allMatch(c -> this.condiciones.contains(c));
	}

	public boolean esVegano() {
		return condiciones.stream().anyMatch(condicion -> Vegano.class.isInstance(condicion));
	}

	public boolean esHombre() {
		return sexo.equalsIgnoreCase("masculino");
	}
	
	public void setGestorDeConsultas(GestorDeConsultas gestorDeConsultas) {
		this.gestorDeConsultas = gestorDeConsultas;	
	}

	public List<Receta> getRecetasFavoritas() {
		return recetasFavoritas;
	}

	public GestorDeConsultas getGestorDeConsultas() {
		return gestorDeConsultas;
	}
	
	public void agregarTratamientoDeConsultas(TratamientoDeConsultas tratamiento) {
		tratamientosDeConsultas.add(tratamiento);
	}

	public boolean tieneAcceso(Receta receta){
		return this.tenesUnaReceta(receta) || this.comparteReceta(receta);
	}

	public boolean comparteReceta(Receta receta) {
		return grupos.stream().anyMatch(grupo -> grupo.algunUsuarioTiene(receta));
	}
	
}