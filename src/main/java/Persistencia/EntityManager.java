package Persistencia;

import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class EntityManager implements WithGlobalEntityManager {
	
	public void registrar(PersistentEntity object){
		entityManager().persist(object);
	}
	
	public Receta obtenerReceta(Long id){
		return entityManager().find(Receta.class, id);
	}
	
	public Usuario obtenerUsuario(Long id){
		return entityManager().find(Usuario.class, id);
	}

}
