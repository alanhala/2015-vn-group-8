package grupo8.TPAnual.model.Repositorios;

import grupo8.TPAnual.exceptions.UsuarioNoEstaEnElRepositorioException;
import grupo8.TPAnual.exceptions.UsuarioNoEstaEnListaDePendientesException;
import grupo8.TPAnual.model.Dominio.Usuario;
import grupo8.TPAnual.model.Monitores.GestorDeConsultas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepoUsuarios {

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Usuario> solicitudesPendientesDeUsuarios = new ArrayList<Usuario>();
	private HashMap<Usuario, String> solicitudesRechazadas;
	private GestorDeConsultas gestorDeConsultas;

	public RepoUsuarios(GestorDeConsultas gestorDeConsultas) {
		this.gestorDeConsultas = gestorDeConsultas;
	}

	public RepoUsuarios() {
		this.gestorDeConsultas = new GestorDeConsultas();
	}

	public void add(Usuario usuario) {
		usuario.setGestorDeConsultas(this.gestorDeConsultas);
		usuarios.add(usuario);
	}// TODO preguntar si no hay que validar que sea un usuario que esta
		// pendiente

	public void remove(Usuario usuario) {
		usuarios.remove(usuario);
	}

	public void update(Usuario usuario) {
		try {
			this.get(usuario);

		} catch (NoSuchElementException exception) {
			throw new UsuarioNoEstaEnElRepositorioException(
					"El usuario no se encuentra en el repositorio");
		}

		usuarios.remove(this.get(usuario));
		usuarios.add(usuario);
	}

	public Usuario get(Usuario usuario) {
		return usuarios.stream().filter(u -> (u.tieneMismoNombreQue(usuario)))
				.findFirst().get();
	}

	public List<Usuario> list(Usuario usuario) {

		Stream<Usuario> usuariosFiltrados = usuarios.stream().filter(
				u -> u.nombreContieneNombreDe(usuario)
						&& u.tieneLasCondicionesDe(usuario));

		return usuariosFiltrados.collect(Collectors.toList());
	}

	public void agregarAPendienteDeAprobacion(Usuario usuario) {
		solicitudesPendientesDeUsuarios.add(usuario);
	}

	public void aceptarPerfil(Usuario usuario) {
		chequearYRemoverUsuario(usuario);
		usuarios.add(usuario);
	}

	public void rechazarPerfil(Usuario usuario, String motivo) {
		chequearYRemoverUsuario(usuario);
		solicitudesRechazadas.put(usuario, motivo);
	}

	private void chequearYRemoverUsuario(Usuario usuario) {
		if (!solicitudesPendientesDeUsuarios.contains(usuario)) {
			throw new UsuarioNoEstaEnListaDePendientesException(
					"El usuario ingresado no se encuentra en la lista de solicitudes pendientes");
		}
		solicitudesPendientesDeUsuarios.remove(usuario);
	}

	public List<Usuario> getSolicitudesPendientesDeUsuarios() {
		return solicitudesPendientesDeUsuarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

}
