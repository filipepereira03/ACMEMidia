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
