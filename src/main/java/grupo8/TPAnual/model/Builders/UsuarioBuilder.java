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

	public void setPeso(Double nuevoPeso) {
		peso = nuevoPeso;
	}
	
	public void setAltura(Double nuevaAltura) {
		altura = nuevaAltura;
	}
	
	public void setNombre(String nuevoNombre) {
		nombre = nuevoNombre;
	}
	
	public void setSexo(String nuevoSexo) {
		sexo = nuevoSexo;
	}
	
	public void setFechaDeNacimiento(LocalDate nuevaFecha) {
		fechaDeNacimiento = nuevaFecha;
	}
	
	public void agregarPreferenciaAlimenticia(String nuevaPreferencia) {
		preferenciasAlimenticias.add(nuevaPreferencia);
	}
	
	public void agregarDisgustoAlimenticio(String nuevoDisgusto) {
		disgustosAlimenticios.add(nuevoDisgusto);
	}
	
	public void agregarCondicion(Condicion nuevaCondicion) {
		condiciones.add(nuevaCondicion);
	}
	
	public void agregarReceta(Receta nuevaReceta) {
		recetas.add(nuevaReceta);
	}
	
	public void setRutina(Rutina nuevaRutina) {
		rutina = nuevaRutina;
	}
	
	public void agregarGrupo(Grupo nuevoGrupo) {
		grupos.add(nuevoGrupo);
	}
	

}
