package grupo8.TPAnual.model;

public interface Condicion {
	
	public void esValida(Usuario usuario);
	public boolean esSubsanada(Usuario usuario);
	public boolean esInadecuadaParaUnaReceta(Receta receta);

}
