package grupo8.TPAnual.model.Dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import Persistencia.PersistentEntity;

@Entity
@Table(name="ComponentesDeReceta")
public class ComponenteDeReceta extends PersistentEntity {
	
	private String nombre;
	private Double cantidad;
	private Double calorias;
	
	public ComponenteDeReceta(){
		//Lo necesito para Hibernate
	}
	
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
