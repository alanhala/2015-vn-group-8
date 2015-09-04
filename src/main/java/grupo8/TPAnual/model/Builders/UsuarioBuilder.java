package grupo8.TPAnual.model.Builders;

import grupo8.TPAnual.model.CondicionesPreexistentes.Condicion;
import grupo8.TPAnual.model.Dominio.Grupo;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Rutina;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Monitores.GestorDeConsultas;
import grupo8.TPAnual.model.Repositorios.RepositorioDeRecetas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioBuilder {

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
	private Usuario unUsuario;

	public UsuarioBuilder() {
		preferenciasAlimenticias = new ArrayList<String>();
		disgustosAlimenticios = new ArrayList<String>();
		condiciones = new ArrayList<Condicion>();
		recetas = new ArrayList<Receta>();
		grupos = new ArrayList<Grupo>();
		recetasFavoritas = new ArrayList<Receta>();
	}

	public Usuario build() {
		unUsuario = new Usuario(peso, altura, nombre, sexo, fechaDeNacimiento,
				preferenciasAlimenticias, disgustosAlimenticios, condiciones,
				recetas, rutina, grupos);
		return unUsuario;
	}

	public UsuarioBuilder setPeso(Double nuevoPeso) {
		peso = nuevoPeso;
		return this;
	}
	
	public UsuarioBuilder setAltura(Double nuevaAltura) {
		altura = nuevaAltura;
		return this;
	}
	
	public UsuarioBuilder setNombre(String nuevoNombre) {
		nombre = nuevoNombre;
		return this;
	}
	
	public UsuarioBuilder setSexo(String nuevoSexo) {
		sexo = nuevoSexo;
		return this;
	}
	
	public UsuarioBuilder setFechaDeNacimiento(LocalDate nuevaFecha) {
		fechaDeNacimiento = nuevaFecha;
		return this;
	}
	
	public UsuarioBuilder agregarPreferenciaAlimenticia(String nuevaPreferencia) {
		preferenciasAlimenticias.add(nuevaPreferencia);
		return this;
	}
	
	public UsuarioBuilder agregarDisgustoAlimenticio(String nuevoDisgusto) {
		disgustosAlimenticios.add(nuevoDisgusto);
		return this;
	}
	
	public UsuarioBuilder agregarCondicion(Condicion nuevaCondicion) {
		condiciones.add(nuevaCondicion);
		return this;
	}
	
	public UsuarioBuilder agregarReceta(Receta nuevaReceta) {
		recetas.add(nuevaReceta);
		return this;
	}
	
	public UsuarioBuilder setRutina(Rutina nuevaRutina) {
		rutina = nuevaRutina;
		return this;
	}
	
	public UsuarioBuilder agregarGrupo(Grupo nuevoGrupo) {
		grupos.add(nuevoGrupo);
		return this;
	}
	
	

}
