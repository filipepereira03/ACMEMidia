package dados;

import java.text.DecimalFormat;

public class Musica extends Midia {

	private double duracao;
	private DecimalFormat df = new DecimalFormat("#.##");

	public Musica(int codigo, String titulo, int ano, Categoria categoria, double duracao) {
		super(codigo, titulo, ano, categoria);
		this.duracao = duracao;
	}

	@Override
	public double calculaLocacao() {
		double valorPorMin = 0;

		switch (getCategoria()) {
			case ACA:
				valorPorMin = 0.9;
				break;
			case DRA:
				valorPorMin = 0.7;
				break;
			case FIC:
				valorPorMin = 0.5;
				break;
			case ROM:
				valorPorMin = 0.3;
				break;
			default:
				valorPorMin = 0;
				break;
		}
		return duracao * valorPorMin;
	}

	@Override
	public String toString() {
		return getCodigo() +
				", " + getTitulo() +
				", " + getAno() +
				", " + getCategoria().getNome() +
				", " + duracao;
	}

	public double getDuracao() {
		return duracao;
	}


	public String toStringDados() {
		return super.toStringDados() + ", " +duracao+ ", " +df.format(calculaLocacao());
	}
}
