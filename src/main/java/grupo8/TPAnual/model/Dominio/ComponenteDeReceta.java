package grupo8.TPAnual.model.Dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ComponentesDeReceta")
public class ComponenteDeReceta {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private Double cantidad;
	private Double calorias;
	
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
