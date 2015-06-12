package grupo8.TPAnual.model.Monitores;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.List;

public interface Monitor {
	
	public void actualizar(Usuario usuario, List<Receta> consulta);
	
}
