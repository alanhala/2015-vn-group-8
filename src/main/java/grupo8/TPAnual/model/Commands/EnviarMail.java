package grupo8.TPAnual.model.Commands;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.DependenciasLocales.Mail;
import grupo8.TPAnual.model.DependenciasLocales.MailSender;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("EM")
public class EnviarMail extends TratamientoDeConsultas {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private Usuario usuario;
	
	@OneToOne
	private Filtro filtro;
	
	@OneToMany
	@JoinColumn(name = "enviar_mail_id")
	private List<Receta> recetasFiltradas;
	
	@Transient //TODO Ver si hay que persistir esto
	private static MailSender mailSender; // Dependencia
	
	public EnviarMail(){
		//Constructor vacio
	}

	public EnviarMail(Usuario usuario, Filtro filtro,
			List<Receta> recetasFiltradas) {
		this.usuario = usuario;
		this.filtro = filtro;
		this.recetasFiltradas = recetasFiltradas;
	}

	@Override
	public void agregarAccionARealizar(Usuario usuario, Filtro filtro,
			List<Receta> recetasFiltradas) {
		usuario.getGestorDeConsultas().agregarAccionARealizar(
				new EnviarMail(usuario, filtro, recetasFiltradas));
	}

	@Override
	public void ejecutar() {
		mailSender.enviarMail(usuario, new Mail(filtro, recetasFiltradas));
	}

	public static void setMailSender(MailSender mailSender) {
		EnviarMail.mailSender = mailSender;
	}
}
