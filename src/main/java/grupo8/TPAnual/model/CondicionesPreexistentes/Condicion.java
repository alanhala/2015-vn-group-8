package grupo8.TPAnual.model.CondicionesPreexistentes;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

public interface Condicion {
	
	public void esValida(Usuario usuario);
	public boolean esSubsanada(Usuario usuario);
	public boolean esInadecuadaParaUnaReceta(Receta receta);

}
