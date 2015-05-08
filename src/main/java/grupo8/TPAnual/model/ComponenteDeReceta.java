package grupo8.TPAnual.model;

public class ComponenteDeReceta {
	
	private String nombre;
	private Double cantidad;
	private Double calorias;
	
	//los warnings son porque los atributos no se usaron aun
	public ComponenteDeReceta(String nombre, Double cantidad, Double calorias) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.calorias = calorias;
	}
	
	public String nombre()
	{
		return nombre;
	}
	
	public double cantidad()
	{
		return cantidad;
	}

	public double calorias()
	{
		return calorias;
	}
}
