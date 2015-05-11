import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {
	public String nome;
	ArrayList<String> lista = new ArrayList<String>();
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	protected void carregaArquivo(String Arquivo){		
	
		try {
            //abre arquivo para leitura
            //FileReader arquivoAberto = new FileReader(this.caminhoDoArquivo);
            FileReader fr = new FileReader(Arquivo);
            BufferedReader br = new BufferedReader(fr);            
            String linha = br.readLine();                
            
            while(linha != null){            	
            	lista.add(linha.toString());
            	
	            linha = br.readLine();
	            
            }            
            br.close();
            fr.close();	            
	 } 
	 catch (IOException ex) {
		 
	 }		
	}
		
	public void Arquivo(String Arquivo){	
		
		carregaArquivo(Arquivo);
		
	}
	
}
