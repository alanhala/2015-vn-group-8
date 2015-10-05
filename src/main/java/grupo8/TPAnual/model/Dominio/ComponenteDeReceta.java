package grupo8.TPAnual.model.Dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COMPONENTES_DE_RECETAS")
public class ComponenteDeReceta {
	
	@Id
	@GeneratedValue
	private Long componenteID;
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
