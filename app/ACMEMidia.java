package app;

import dados.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class ACMEMidia {

	private Midiateca midiateca;
	private Scanner entrada = null;
	private PrintStream saidaPadrao = System.out;

	public ACMEMidia() {
		try {
			BufferedReader streamEntrada = new BufferedReader(new FileReader("entradaDadosExtras.txt"));
			entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
			PrintStream streamSaida = new PrintStream(new File("saida.txt"), Charset.forName("UTF-8"));
			System.setOut(streamSaida);             // Usa como saida um arquivo
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
		entrada.useLocale(Locale.ENGLISH);

		this.midiateca = new Midiateca();
	}

	private void restauraES() {
		System.setOut(saidaPadrao);
		entrada = new Scanner(System.in);
	}

	public void executa() {
		cadastraVideo();
		cadastraMusica();
		mostrarDadosMidia();
		mostrarDadosCategoria();
		mostrarDadosQualidade();
		mostrarDadosDuracao();
		removerMidia();
		mostrarSomatorio();
		musicaProximaMedia();
		midiaMaisNova();
	}

	/** 1: Método de cadastrar videos e adicionar na midiateca
	 * Utilizei o parseInt para treinar mesmo, mesmo sabendo que os dados já estão definidos no arquivo
	 * Para detectar um código repetido eu utilizo uma variável booleana já inicializada como 'false' e percorro a lista com um for
	 * Esse método eu fiquei um tempinho nele, considerando que foi o primeiro e eu estava meio enferrujado.
	 */
	private void cadastraVideo() {
		while (entrada.hasNextLine()) {
			int codigo = Integer.parseInt(entrada.nextLine());

			if (codigo == -1) {
				break;
			}

			String titulo = entrada.nextLine();
			int ano = Integer.parseInt(entrada.nextLine());
			Categoria categoria = Categoria.valueOf(entrada.nextLine().toUpperCase());
			int qualidade = Integer.parseInt(entrada.nextLine());

			boolean codigoRep = false;
			for (Midia m : midiateca.getMidia()) {
				if (m.getCodigo() == codigo) {
					System.out.println("1: Erro-video com código repetido: " + codigo);
					codigoRep = true;
					break;
				}
			}

			if (!codigoRep) {
				Video video = new Video(codigo, titulo, ano, categoria, qualidade);
				if (midiateca.cadastraMidia(video)) {
					System.out.println("1: " +video);
				}
			}
		}
	}

	/** 2. Método extremamente igual ao primeiro, mudando apenas as variáveis
	 * única coisa mais diferente foi a utilização de um parseDouble, de resto é muito similar
	 */
	private void cadastraMusica() {
		while (entrada.hasNextLine()) {
			int codigo = Integer.valueOf(entrada.nextLine());

			if (codigo == -1) {
				break;
			}

			String titulo = entrada.nextLine();
			int ano = Integer.parseInt(entrada.nextLine());
			Categoria categoria = Categoria.valueOf(entrada.nextLine().toUpperCase());
			double duracao = Double.parseDouble(entrada.nextLine());

			boolean codigoRep = false;
			for (Midia m : midiateca.getMidia()) {
				if (m.getCodigo() == codigo) {
					System.out.println("2: Erro-musica com código repetido: " + codigo);
					codigoRep = true;
					break;
				}
			}

			if (!codigoRep) {
				Musica musica = new Musica(codigo, titulo, ano, categoria, duracao);
				if (midiateca.cadastraMidia(musica)) {
					System.out.println("2: " +musica);
				}
			}
		}
	}

	/** 3. Um código mais enxuto e, no geral, parecido com os outros 2.
	 * o mais diferente desse método é a utilização de outra toString para diferenciar Video e Musica.
	 * implementei ela na classe midia e chamei ela como super nas subclasses adicionando os atributos diferentes de cada uma + a locação.
	 */
	private void mostrarDadosMidia() {
		int codigo = Integer.parseInt(entrada.nextLine());

		Midia midia = midiateca.consultaPorCodigo(codigo);

		if (midia == null) {
			System.out.println("3: Codigo inexistente.");
		} else {
			System.out.println("3: " +midia.toStringDados());
		}
	}

	/** 4. Um método mais trabalhoso e demorado
	 * Aqui começo o método como todos até agora, atributos e agora crio um arraylist que consulta todas as midias associadas a uma cat
	 * Faço a verificação se o array n está vazio
	 * Caso passe dessa verificação, eu crio uma stringBuilder iniciando ele como '4:' para manipular as informações
	 * Depois percorro um for e ele vai adicionando ao stringBuilder utilizando o append que puxa a minha toString
	 * Logo depois dou print em todos esse dados que foram obtidos no for
	 */
	private void mostrarDadosCategoria() {
		String catString = entrada.nextLine().toUpperCase();
		Categoria categoria;

		try {
			categoria = Categoria.valueOf(catString);
		} catch (IllegalArgumentException e) {
			System.out.println("4: Nenhuma mídia encontrada");
			return;
		}

		ArrayList<Midia> midias = midiateca.consultaPorCategoria(categoria);

		if (midias.isEmpty()) {
			System.out.println("4: Nenhuma midia encontrada");
		} else {
			StringBuilder dados = new StringBuilder();
			for (Midia m : midias) {
				dados.append("4: ").append(m.toStringDados()).append("\n");
			}

			if (!dados.isEmpty()) {
				dados.setLength(dados.length() - 1);
			}

			System.out.println(dados);
		}
	}

	/** 5: Aqui é a mesma lógica da 4 porém utilizo instanceOf
	 * (Midiateca) Dps de criar uma lista pra armazenar os videos com n qualidades
	 * (Midiateca) eu verifico se a midia atual é uma instancia de video (o que é necessário pois só videos tem qualidade)
	 * (Midiateca) e também forço a checagem da qualidade de m
	 * Transformei tudo isso acima em um método na classe midiateca, diferente da versão anterior
	 * Atendendo aos critérios, é adicionado um vídeo
 	 */
	private void mostrarDadosQualidade() {
		int qualidade = Integer.parseInt(entrada.nextLine());

		ArrayList<Video> videos = midiateca.filtraQualidade(qualidade);

		if (videos.isEmpty()) {
			System.out.println("5: Qualidade inexistente");
		} else {
			StringBuilder dados = new StringBuilder();

			for (Video v : videos) {
				DecimalFormat df = new DecimalFormat("#.00");
				dados.append("5: ").append(v.toStringDados()).append("\n");
			}

			if (!dados.isEmpty()) {
				dados.setLength(dados.length() - 1);
			}

			System.out.println(dados);
		}
	}

	/** 6. Esse método foi bem interessante de se fazer
	 * (Midiateca) Utilizo novamente o instanceOf para verificar se é uma instancia de musica o dado passado
	 * (Midiateca) Logo dps crio uma variável 'auxiliar' e converto ela para música de uma maneira segura, considerando que já foi verificada a instancia
	 * Transformei tudo isso acima em um método na classe midiateca, diferente da versão anterior
	 */
	private void mostrarDadosDuracao() {
		Musica maiorDuracao = midiateca.encontraMaiorDuracao();

		if (maiorDuracao == null) {
			System.out.println("6: Nenhuma musica encontrada.");
		} else {
			System.out.println("6: " + maiorDuracao.getTitulo() + ", " + maiorDuracao.getDuracao());
		}
	}

	/** 7. Código simples que apenas remove uma midia baseado no código
	 * Utilizo novamente minha toStringDados e ela printa os dados da midia removida e remove do sistema por completo
	 * testei esse método com outras entradas e ele continua funcionando
	 */

	private void removerMidia() {
		int codigo = Integer.parseInt(entrada.nextLine());

		Midia midia = midiateca.consultaPorCodigo(codigo);

		if (midia == null) {
		System.out.println("7: Codigo inexistente");
		} else {
			System.out.println("7: " +midia.toStringDados());

			midiateca.removeMidia(codigo);
		}
	}

	/** 8. Mostra o somatório das locações
	 * Considerando que o método acima remove uma midia, esse aqui é executado dps da remoção, retornando já atualizado o somatório
	 */

	private void mostrarSomatorio() {
		double somatorio = midiateca.calculaSomatorio();

		if (somatorio == 0) {
			System.out.println("8: Nenhuma midia encontrada.");
		} else {
			System.out.println("8: " + somatorio);
		}
	}

	// Métodos extras

	private void musicaProximaMedia() {
		Musica musicaMaisProx = midiateca.encontraMaisProxMedia();

		if (musicaMaisProx == null) {
			System.out.println("9: Nenhuma musica encontrada.");
		} else {
			double mediaLoc = midiateca.calculaMediaLocacaoMusicas();
			DecimalFormat df = new DecimalFormat("#.##");

			StringBuilder dados = new StringBuilder("9: ");
			dados.append(df.format(mediaLoc)).append(", ");
			dados.append(musicaMaisProx.toStringDados());
			System.out.println(dados);
		}
	}

	// 10: Método para achar a midia mais nova, só vou comparando as midias e vendo os anos com o getAno
	private void midiaMaisNova() {
		Midia midiaNova = midiateca.encontrarMidiaMaisNova();

		if (midiaNova == null) {
			System.out.println("10: Nenhuma midia encontrada");
		} else {
			System.out.println("10: " + midiaNova.getCodigo() + ", " + midiaNova.getTitulo() + ", " + midiaNova.getAno());
		}
	}
}


