// Nessa classe eu coloquei todos os métodos com operações matemáticas que antigamente estavam na ACME

package dados;

import java.util.Collection;
import java.util.ArrayList;

public class Midiateca implements Iterador {

	private int contador;

	private int index;

	private ArrayList<Midia> midia;

	public Midiateca() {
		this.contador = 0;
		this.index = 0;
		this.midia = new ArrayList<>();
	}

	public ArrayList<Midia> getMidia() {
		return midia;
	}

	public boolean cadastraMidia(Midia jogo) {
		this.midia.add(jogo);
		contador++;
		return true;
	}

	public Midia consultaPorCodigo(int codigo) {
		for (Midia m : midia) {
			if (m.getCodigo() == codigo) {
				return m;
			}
		}
		return null;
	}

	public ArrayList<Video> filtraQualidade (int qualidade) {
		ArrayList<Video> videos = new ArrayList<>();
		for (Midia m : midia) {
			if (m instanceof Video && ((Video) m).getQualidade() == qualidade) {
				videos.add((Video) m);
			}
		}
		return videos;
	}

	public Musica encontraMaiorDuracao() {
		Musica maiorDuracao = null;
		for (Midia m : midia) {
			if (m instanceof Musica musicaAtual) {
                if (maiorDuracao == null || musicaAtual.getDuracao() > maiorDuracao.getDuracao()) {
					maiorDuracao = musicaAtual;
				}
			}
		}
		return maiorDuracao;
	}

	public ArrayList<Midia> consultaPorCategoria(Categoria categoria) {
		ArrayList<Midia> midiasCat = new ArrayList<>();
		for (Midia m : midia) {
			if (m.getCategoria().equals(categoria)) {
				midiasCat.add(m);
			}
		}
		return midiasCat;
	}

	public boolean removeMidia(int codigo) {
		for (Midia m : midia) {
			if (m.getCodigo() == codigo) {
				midia.remove(m);
				contador--;
				return true;
			}
		}
		return false;
	}

	public double calculaSomatorio() {
		double somatorio = 0;
		for (Midia m : midia) {
			somatorio += m.calculaLocacao();
		}
		return somatorio;
	}

	public double calculaMediaLocacaoMusicas() {
		double totalLoc = 0;
		int quantidade = 0;
		for (Midia m : midia) {
			if (m instanceof Musica) {
				totalLoc += m.calculaLocacao();
				quantidade++;
			}
		}

		if (quantidade == 0) {
			return 0;
		}

		return totalLoc / quantidade;
	}

	public Musica encontraMaisProxMedia() {
		double mediaLoc = calculaMediaLocacaoMusicas();
		Musica musicaMaisProxima = null;
		double menorDiferenca = Double.MAX_VALUE;

		for (Midia m : midia) {
			if (m instanceof Musica musica) {
                double diferenca = Math.abs(musica.calculaLocacao() - mediaLoc);
				if (diferenca < menorDiferenca) {
					menorDiferenca = diferenca;
					musicaMaisProxima = musica;
				}
			}
		}
		return musicaMaisProxima;
	}

	public Midia encontrarMidiaMaisNova() {
		if (midia.isEmpty()) {
			return null;
		}

		Midia midiaNova = midia.getFirst();
		for (Midia m : midia) {
			if (m.getAno() > midiaNova.getAno()) {
				midiaNova = m;
			}
		}
		return midiaNova;
	}


	/**
	 * @see dados.Iterador#reset()
	 */
	public void reset() {
		index = 0;
	}


	/**
	 * @see dados.Iterador#hasNext()
	 */
	public boolean hasNext() {
		return index < midia.size();
	}


	/**
	 * @see dados.Iterador#next()
	 */
	public Object next() {
		if (this.hasNext()) {
			Midia midiaA = midia.get(index);
			index++;
			return midiaA;
		}
		return null;
	}
}
