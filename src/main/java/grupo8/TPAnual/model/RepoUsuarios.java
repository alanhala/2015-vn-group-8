package grupo8.TPAnual.model;

import grupo8.TPAnual.exceptions.UsuarioNoEstaEnListaDePendientesException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepoUsuarios {
	
	private static RepoUsuarios instance;
	private  List<Usuario> usuarios = new ArrayList<Usuario>();
	private  List<Usuario> solicitudesPendientesDeUsuarios = new ArrayList<Usuario>();
	
	private RepoUsuarios(){

	}
	
	public static RepoUsuarios getInstance(){
		if (instance == null){
			instance = new RepoUsuarios();
		}
		return instance;
	}
	
	public void add(Usuario usuario) {
		usuarios.add(usuario);		
	}

	public void remove(Usuario usuario) {
		usuarios.remove(usuario);
	}
	
	public void update(Usuario usuario) {
		//TODO
	}
	
	public Usuario get(Usuario usuario) {
		return usuarios.stream().filter(u -> (u.tieneMismoNombreQue(usuario))).findFirst().get();
	}
	
	public List<Usuario> list(Usuario usuario) {
		
		Stream<Usuario> usuariosFiltrados = usuarios.stream().
				filter(u -> u.nombreContieneNombreDe(usuario) && u.tieneLasCondicionesDe(usuario));
		
		return usuariosFiltrados.collect(Collectors.toList());
	}
	
	public void seCreoNuevoPerfil(Usuario usuario) {
		solicitudesPendientesDeUsuarios.add(usuario);
	}
	
	public void aceptarPerfil(Usuario usuario) {
		chequearYRemoverUsuario(usuario);
		usuarios.add(usuario);
	}
	
	public String rechazarPerfil(Usuario usuario, String motivo) {
		chequearYRemoverUsuario(usuario);
		return motivo;
	}

	private void chequearYRemoverUsuario(Usuario usuario) {
		if (!solicitudesPendientesDeUsuarios.contains(usuario)) {
			throw new UsuarioNoEstaEnListaDePendientesException("El usuario ingresado no se encuentra en la lista de solicitudes pendientes");
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
