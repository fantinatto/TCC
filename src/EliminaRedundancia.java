import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
 * * Classname: EliminaRedundancia 
 * * * Version information: 1.0 
 * * * 13/07/2015 13:03 
 * * * author: Vinícius Fantinatto de Medeiros 
 * * Copyright notice: 
 * 1 - Devido o problema de Redundancica dentro de um arquivo de regras de Associação
 * foi criado um método denominado "encontraRedundancia" que inicia a com o valor de itemset 1
 * encontrando todas as regras que podem ser eliminadas com o item pré-selecionado
 * existe nele um loop onde é incrementado o etemset, até que não exista mais redundancia
 * 2 - Devido a alta complexidade e o número de comparações, foi utilizado expreções regulares
 *  ("((\\w+) 	--> aqui ele pega todas as palavras
 *  (\\s+))		--> aqui ele encontra os espaços que nela existem 	
 *  {"+itens+"}	--> aqui é setada a quantidade de itens selecionados para a comparação
 *  (\\()")		--> deve terminar com ( ...assim evita que compare os numeros do suporte 
 * 
 * 
 */

public class EliminaRedundancia {
	public boolean[] Array1; // vetor de itensets selecionados
	public boolean[] Array2; // vetor de regras eliminadas
	public String mkdfile;
	public String nomeRegra;
	public String vetItemsetX = "";
	String arquivoFinal;
	Map<String, Integer> mapPalavras;

	public String getNomeRegra() {
		return nomeRegra;
	}

	public void setNomeRegra(String nomeRegra) {
		this.nomeRegra = nomeRegra;
	}

	public String getArquivoFinal() {
		return arquivoFinal;
	}

	public void setArquivoFinal(String arquivoFinal) {
		this.arquivoFinal = arquivoFinal;
	}

	public void alocaArray(int tvet) {
		this.Array1 = new boolean[tvet];
		this.Array2 = new boolean[tvet];
	}

	public String getMkdfile() {
		return mkdfile;
	}

	public void setMkdfile(String mkdfile) {
		this.mkdfile = mkdfile;
	}

	// P1
	/* Encontra redundancia itemset */
	public int redundanciaItemset(int itens) throws IOException {
		int numRegras = 0, linha = 0;
		FileReader txtFile;
		BufferedReader txtBuffer = null;
		String curLine = null;
		mapPalavras = new HashMap<String, Integer>();

		try {
			txtFile = new FileReader(this.mkdfile);
			txtBuffer = new BufferedReader(txtFile);
			curLine = txtBuffer.readLine();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Pecorre todo o arquivo e salva em mapPalavras os antesscessores
		 * encontrados
		 */
		while (curLine != null) {
			if (this.Array2[linha] != true) { // verifica se já não foi
												// eliminado
				if (this.Array1[linha] != true) { // verifica se já não foi
													// inserido
					String minusculo = curLine.toLowerCase(); // caracter em
																// minusculo
					// minusculo = Normalizer.normalize(minusculo,
					// Normalizer.Form.NFD);
					// minusculo = minusculo.replaceAll("[^\\p{ASCII}]", "");
					// //elimina palavras com acento (não letras)
					Pattern p = Pattern.compile("(#@((\\w+)(\\s+)){" + itens
							+ "})(\\()"); // expreção regular
					Matcher m = p.matcher(minusculo);
					/*
					 * percorre linha até achar o token, caso não exista sai do
					 * while
					 */
					if (m.find()) {
						this.Array1[linha] = true;
						String token = m.group();
						token = token.substring(0, token.length() - 2);
						final Integer freq = mapPalavras.get(token);
						// System.out.println(token);
						mapPalavras.put(token.split("#@")[1], numRegras);
						numRegras++;
					}
				}
			}
			linha++;
			curLine = txtBuffer.readLine(); // próxima linha
		}
		txtBuffer.close();
		if (this.mapPalavras.size() > 0)
			insereRegrasNoVetor();
		return numRegras;
	}

	// p1.1
	public void insereRegrasNoVetor() {

		// insere todos os itemsets
		for (Map.Entry<String, Integer> entry : this.mapPalavras.entrySet()) {
			// se existir mais de 1 item
			if (entry.getKey().split(" ").length > 1) {
				this.vetItemsetX = this.vetItemsetX.concat("(");
				for (int i = 0; i < entry.getKey().split(" ").length; i++) {
					this.vetItemsetX = this.vetItemsetX.concat("("
							+ entry.getKey().split(" ")[i] + ")");
					this.vetItemsetX = this.vetItemsetX
							.concat("((\\w)|(\\s))*");
				}
				this.vetItemsetX = this.vetItemsetX.substring(0,
						this.vetItemsetX.length() - 12);
				this.vetItemsetX = this.vetItemsetX.concat(")");
				this.vetItemsetX = this.vetItemsetX.concat("|");
			} else {
				this.vetItemsetX = this.vetItemsetX.concat("("
						+ entry.getKey().substring(0,
								entry.getKey().length() - 1) + ")|");
			}

		}
		this.vetItemsetX = this.vetItemsetX.substring(0,
				this.vetItemsetX.length() - 1);

	}

	// P2
	public void organizaVetorRedundancia() throws IOException {
		int linha = 0;
		FileReader txtFile = null;
		BufferedReader txtBuffer = null;
		String curLine = null;

		try {
			txtFile = new FileReader(this.mkdfile);
			txtBuffer = new BufferedReader(txtFile);
			curLine = txtBuffer.readLine();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Pecorre todo o arquivo e salva em mapPalavras os antesscessores
		 * encontrados
		 */
		while (curLine != null) {
			if (this.Array1[linha] != true) {
				if (this.Array2[linha] != true) {
					String minusculo = curLine.toLowerCase(); // caracter em
																// minusculo
					// minusculo = Normalizer.normalize(minusculo,
					// Normalizer.Form.NFD);
					// minusculo = minusculo.replaceAll("[^\\p{ASCII}]", "");
					// //elimina palavras com acento (não letras)
					Pattern p = Pattern.compile("(" + vetItemsetX + ")");
					Matcher m = p.matcher(minusculo);
					/*
					 * percorre linha até achar o token, caso não exista sai do
					 * while
					 */
					if (m.find()) {
						this.Array2[linha] = true;
						// String token = m.group();
						// System.out.println(token);
					}
				}
			}
			linha++;
			curLine = txtBuffer.readLine(); // próxima linha
		}
		txtBuffer.close();
		txtFile.close();
		this.vetItemsetX = this.vetItemsetX.substring(0, 0);
	}

	// P3
	public void escreveArquivoSemRedundancia() throws IOException {
		int i = 0;
		FileReader txtFile = null;
		BufferedReader txtBuffer = null;
		String curLine = null;

		try {
			txtFile = new FileReader(this.mkdfile);
			txtBuffer = new BufferedReader(txtFile);
			curLine = txtBuffer.readLine();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileWriter fw = new FileWriter(this.arquivoFinal, true);
		BufferedWriter bw = new BufferedWriter(fw);

		/*
		 * Pecorre todo o arquivo e salva em mapPalavras os antesscessores
		 * encontrados
		 */
		while (curLine != null) {
			if (this.Array2[i] == false) {
				// System.out.println(curLine);
				bw.write(this.nomeRegra + " <- " + curLine.split("#@")[1]);
				bw.newLine(); // quebra de linha
			}
			i++;
			curLine = txtBuffer.readLine(); // próxima linha
		}
		txtFile.close();
		bw.close(); // fecha os recursos
		fw.close();
	}

}

/*
 * 
 * Artigo explicando método Static: * (http://www.guj.com.br/articles/121) *
 * (http
 * ://www.javaprogressivo.net/2012/10/static-Usando-membros-estaticos-em-Java
 * .html)Site auxilia o uso de expressão regular: * (http://regexr.com/)
 */
