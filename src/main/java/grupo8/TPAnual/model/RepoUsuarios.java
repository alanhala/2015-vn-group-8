package grupo8.TPAnual.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RepoUsuarios {

	public static List<Usuario> usuarios= new ArrayList<Usuario>();
	
	public static void add(Usuario usuario) {
		usuarios.add(usuario);		
	}

	public static void remove(Usuario usuario) {
		usuarios.remove(usuario);
	}
	
	public static void update(Usuario usuario) {
		//vacio
	}
	
	public static Stream<Usuario> get(Usuario usuario) {
		return usuarios.stream().filter(u -> (u.tieneMismoNombreQue(usuario))).limit(1);
	}
	
	public static Stream<Usuario> list(Usuario usuario) {
		
		Stream<Usuario> usuariosFiltrados = usuarios.stream().
				filter(u -> u.nombreContieneNombreDe(usuario));
		
		if (!usuario.getCondiciones().isEmpty()) {
			usuariosFiltrados.filter(u -> 
				u.tieneLasCondicionesDe(usuario));
		}

		return usuariosFiltrados;
	}
}
