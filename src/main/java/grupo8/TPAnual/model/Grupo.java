package grupo8.TPAnual.model;

import java.util.List;

public class Grupo {
	
	private String nombre;
	private List<Usuario> integrantes;
	private List<String> preferenciasAlimenticias;

	public Grupo(String nombre, List<Usuario> integrantes,
			List<String> preferenciasAlimenticias) {
		this.nombre = nombre;
		this.integrantes = integrantes;
		this.preferenciasAlimenticias = preferenciasAlimenticias;
	}

	public boolean perteneceAlGrupo(Usuario usuario) {
		return integrantes.contains(usuario);
	}

	public void agregarUsuario(Usuario usuario) {
		integrantes.add(usuario);
	}
	
	
	
}
