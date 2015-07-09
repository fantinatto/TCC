import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {
	public String nome;
	ArrayList<String> lista = new ArrayList<String>();
	
	
	public ArrayList<String> getLista() {
		return lista;
	}

	protected void carregaArquivo(String Arquivo) {
		int value=0;
				
		try {
			// abre arquivo para leitura
			// FileReader arquivoAberto = new FileReader(this.caminhoDoArquivo);
			FileReader fr = new FileReader(Arquivo);
			BufferedReader br = new BufferedReader(fr);
			
			
			String linha = br.readLine();
			
			while (linha != null) {
				lista.add(linha.toString());
				//System.out.println(value++);
				linha = br.readLine();					
			}
			
			br.close();
			fr.close();
			
		} catch (IOException ex) {

		}
	}

	public void Arquivo(String Arquivo) {
		carregaArquivo(Arquivo);
	}
}
