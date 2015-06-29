package grupo8.TPAnual.model.Commands;

import grupo8.TPAnual.model.Decorators.Filtro;
import grupo8.TPAnual.model.DependenciasLocales.Mail;
import grupo8.TPAnual.model.DependenciasLocales.MailSender;
import grupo8.TPAnual.model.Dominio.Receta;
import grupo8.TPAnual.model.Dominio.Usuario;

import java.util.List;

public class EnviarMail implements TratamientoDeConsultas {

	private Usuario usuario;
	private Filtro filtro;
	private List<Receta> recetasFiltradas;
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
