import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
 * * Classname: EliminaRedundancia 
 * * * Version information: 1.0 
 * * * 13/07/2015 13:03 
 * * * author: Vin�cius Fantinatto de Medeiros 
 * * Copyright notice: 
 * 1 - Devido o problema de Redundancica dentro de um arquivo de regras de Associa��o
 * foi criado um m�todo denominado "encontraRedundancia" que inicia a com o valor de itemset 1
 * encontrando todas as regras que podem ser eliminadas com o item pr�-selecionado
 * existe nele um loop onde � incrementado o etemset, at� que n�o exista mais redundancia
 * 2 - Devido a alta complexidade e o n�mero de compara��es, foi utilizado expre��es regulares
 *  ("((\\w+) 	--> aqui ele pega todas as palavras
 *  (\\s+))		--> aqui ele encontra os espa�os que nela existem 	
 *  {"+itens+"}	--> aqui � setada a quantidade de itens selecionados para a compara��o
 *  (\\()")		--> deve terminar com ( ...assim evita que compare os numeros do suporte 
 * 
 * 
 */

public class EliminaRedundancia {
	public boolean []vet;
	public String mkdfile;
			
	/*Encontra redundancia itemset*/		
	public int redundanciaItemset(int itens) throws IOException{
		int numRegras = 0;
		FileReader txtFile;
		BufferedReader txtBuffer = null;
		String curLine = null;
		Map<String,Integer> mapPalavras = new HashMap<String,Integer>();
		
		try {
			txtFile = new FileReader(this.mkdfile);
			txtBuffer = new BufferedReader(txtFile);
			curLine = txtBuffer.readLine();	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		/*Pecorre todo o arquivo e salva em mapPalavras os antesscessores encontrados*/	
		while(curLine != null){
			String minusculo = curLine.toLowerCase();	//caracter em minusculo
			minusculo = Normalizer.normalize(minusculo, Normalizer.Form.NFD);
			minusculo = minusculo.replaceAll("[^\\p{ASCII}]", "");	//elimina palavras com acento (n�o letras)
			Pattern p = Pattern.compile("((\\w+)(\\s+)){"+itens+"}(\\()"); //expre��o regular
			Matcher m = p.matcher(minusculo);			
			
			/*percorre linha at� achar o token, caso n�o exista sai do while*/
			while(m.find()){				
				String token = m.group();					
				final Integer freq = mapPalavras.get(token);					
				System.out.println(token);
				
				if(freq != null){
					this.vet[numRegras] = true;
					mapPalavras.put(token,numRegras);												
				}
				else{	
					numRegras++;
					mapPalavras.put(token,numRegras);
				}					
			}			
			curLine = txtBuffer.readLine();	//pr�xima linha
		}					
		txtBuffer.close();		
		return numRegras;			
	}
	
	public void organizaVetorRedundancia(){		
		
	}
	
	public void escreveArquivoSemRedundancia(){		
		
	}
	
	//passa por parametro int itemset e String caminho
	public void main(int tam, String dir) throws IOException{		
		int itemset = 1;
		//this.mkdfile = "C:/Temp/grentx/groupedRules/artes_abaixomedia.txt";
		this.mkdfile = dir; 
		this.vet = new boolean[tam];
		
	}	
}

/*
 * 
 * Artigo explicando m�todo Static: 
 * * (http://www.guj.com.br/articles/121)
 * * (http://www.javaprogressivo.net/2012/10/static-Usando-membros-estaticos-em-Java.html)
 *Site auxilia o uso de express�o regular:  
 * * (http://regexr.com/)
 * 
 * 
 */ 
		 