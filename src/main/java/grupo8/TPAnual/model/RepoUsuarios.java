package grupo8.TPAnual.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
	
	public static Optional<Usuario> get(Usuario usuario) {
		return usuarios.stream().filter(u -> (u.getNombre() == usuario.getNombre())).findFirst();
	}
	
	public static Stream<Usuario> list(Usuario usuario) {
		
		Stream<Usuario> usuariosFiltrados = usuarios.stream().
				filter(u -> u.getNombre().contains(usuario.getNombre()));
		
		if (!usuario.getCondiciones().isEmpty()) {
			usuariosFiltrados.filter(u -> 
				u.getCondiciones().contains(usuario.getCondiciones()));
		}
		return usuariosFiltrados;
	}
}
