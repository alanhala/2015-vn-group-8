package grupo8.TPAnual.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
		//TODO
	}
	
	public static Usuario get(Usuario usuario) {
		return usuarios.stream().filter(u -> (u.tieneMismoNombreQue(usuario))).findFirst().get();
	}
	
	public static List<Usuario> list(Usuario usuario) {
		
		Stream<Usuario> usuariosFiltrados = usuarios.stream().
				filter(u -> u.nombreContieneNombreDe(usuario) && u.tieneLasCondicionesDe(usuario));
		
		return usuariosFiltrados.collect(Collectors.toList());
	}
}
