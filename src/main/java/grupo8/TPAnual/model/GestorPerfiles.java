package grupo8.TPAnual.model;

import grupo8.TPAnual.exceptions.UsuarioNoEstaEnListaDePendientesException;

import java.util.ArrayList;
import java.util.List;

public class GestorPerfiles {

	public static List<Usuario> solicitudesPendientesDeUsuarios = new ArrayList<Usuario>();

	public static void seCreoNuevoPerfil(Usuario usuario) {
		solicitudesPendientesDeUsuarios.add(usuario);
	}

	public static void aceptarPerfil(Usuario usuario) {
		chequearYRemoverUsuario(usuario);
		RepoUsuarios.add(usuario);
	}
	
	public static String rechazarPerfil(Usuario usuario, String motivo) {
		chequearYRemoverUsuario(usuario);
		return motivo;
	}

	private static void chequearYRemoverUsuario(Usuario usuario) {
		if (!solicitudesPendientesDeUsuarios.contains(usuario)) {
			throw new UsuarioNoEstaEnListaDePendientesException("El usuario ingresado no se encuentra en la lista de solicitudes pendientes");
		}
		solicitudesPendientesDeUsuarios.remove(usuario);
	}
}
