package grupo8.TPAnual.model;

import java.util.Collections;
import java.util.List;

public class Grupo implements EnteAlQueSeLePuedeSugerirUnaReceta {

	private String nombre;
	private List<Usuario> integrantes;
	private List<String> preferenciasAlimenticias;

	public Grupo(String nombre, List<Usuario> integrantes,
			List<String> preferenciasAlimenticias) {
		this.nombre = nombre;
		this.integrantes = integrantes;
		this.preferenciasAlimenticias = preferenciasAlimenticias;
	}

	public List<String> getPreferenciasAlimenticias() {
		return Collections.unmodifiableList(preferenciasAlimenticias);
	}
	
	public List<Usuario> getIntegrantes() {
		return Collections.unmodifiableList(integrantes);
	}

	public boolean perteneceAlGrupo(Usuario usuario) {
		return integrantes.contains(usuario);
	}

	public void agregarUsuario(Usuario usuario) {
		integrantes.add(usuario);
	}


	public boolean leGusta(Receta unaReceta) {
		return unaReceta.tieneEstosIngredientes(this.getPreferenciasAlimenticias());
	}

	public boolean laRecetaEsApropiada(Receta unaReceta) {
		return this.getIntegrantes().stream().allMatch(
				unIntegrante -> unaReceta.cumpleCondicionesPara(unIntegrante));
	}

	@Override
	public boolean seLePuedeSugerir(Receta unaReceta) {
		return this.leGusta(unaReceta) && this.laRecetaEsApropiada(unaReceta);
	}

}
