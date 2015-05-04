package grupo8.TPAnual;

public class Usuario {

	private Double peso;
	private Double altura;

	public Usuario(Double unPeso, Double unaAltura) {
		peso = unPeso;
		altura = unaAltura;
	}

	public Double calcularIMC() {
		return peso / Math.pow(altura, 2); // me dio paja buscar una funcion que
											// sea el cuadrado de un numero
	}

}
