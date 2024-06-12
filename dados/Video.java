package dados;

import java.text.DecimalFormat;

public class Video extends Midia {

	private int qualidade;
	DecimalFormat df = new DecimalFormat("#.00");

	public Video(int codigo, String titulo, int ano, Categoria categoria, int qualidade) {
        super(codigo, titulo, ano, categoria);
		this.qualidade = qualidade;

    }

	@Override
	public double calculaLocacao() {
		double valorLoc = 0.0;

		if (getAno() == 2024) {
			valorLoc = 20.00;
		} else if (getAno() >= 2000 && getAno() <= 2023) {
			valorLoc = 15.00;
		} else {
			valorLoc = 10.00;
		}
		return valorLoc;
	}

	@Override
	public String toString() {
        return  getCodigo() +
                ", " + getTitulo() +
                ", " + getAno() +
                ", " + getCategoria().getNome() +
                ", " + qualidade;
	}

	public int getQualidade() {
		return qualidade;
	}

	public String toStringDados() {
		return super.toStringDados() + ", " +qualidade+ ", " + df.format(calculaLocacao());
	}
}
