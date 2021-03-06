import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consequente {
	public static Map<String,Integer> mapConsequente;
	
	public static void AgrupaConsequente() throws IOException{
		String curLine;		
		int numTotalRegras = 0;
		
		mapConsequente = new HashMap<String,Integer>();						
		//FileReader txtFile = new FileReader("C:/Users/Vinicius/Desktop/fantinatto/academico/TCC/Base de Regras/loja4");
		//FileReader txtFile = new FileReader("C:/Users/Vinicius/Desktop/fantinatto/academico/TCC/alg/dataminingSE/materias.txt");
		FileReader txtFile = new FileReader("C:/Users/Vinicius/Desktop/fantinatto/academico/TCC/alg/dataminingSE/loja5");
		BufferedReader txtBuffer = new BufferedReader(txtFile);
		curLine = txtBuffer.readLine();
		
			while(curLine != null){
				numTotalRegras++;				
				String minusculo = curLine.toLowerCase(); 	// normaliza em minusculo e sem acento	
				minusculo = Normalizer.normalize(minusculo, Normalizer.Form.NFD);
				minusculo = minusculo.replaceAll("[^\\p{ASCII}]", "");				
				//Pattern p = Pattern.compile ("(\\A\\w+)|(\\A\\w+_)");
				Pattern p = Pattern.compile ("(\\A\\w+)|(\\A\\w+_)"); //pega apenas token no come�o da string
				Matcher m = p.matcher(minusculo);				
							
				while(m.find()){				
					String token = m.group();				
					final Integer freq = mapConsequente.get(token);					
					
					if(freq != null){	
						mapConsequente.put(token,freq+1);							
						FileWriter fw = new FileWriter( "C:\\Temp\\grentx\\groupedRules\\" + token + ".txt", true );
						BufferedWriter bw = new BufferedWriter( fw );																
						bw.write("#@"+curLine.split("<- ")[1].replaceAll("[^\\p{ASCII}]", "")); /*
															*escreve o conte�do no arquivo	
															* *"#"+freq = numera as linhas em cada arquivo 
															* *((\s+)(\w+)){}
															*/
						bw.newLine();	//quebra de linha	
						bw.close(); //fecha os recursos
						fw.close();											
					}
					else{	
						FileWriter arq = new FileWriter("C:\\Temp\\grentx\\groupedRules\\" + token + ".txt");
						BufferedWriter bw = new BufferedWriter(arq);					
						bw.write("#@"+curLine.split("<- ")[1].replaceAll("[^\\p{ASCII}]", ""));
						bw.newLine();	//quebra de linha	
						bw.close(); //fecha os recursos
						arq.close();						
						mapConsequente.put(token,1);
					}
				}			
				curLine = txtBuffer.readLine();
			}			
			txtBuffer.close();			
	}
	
	public static void main(String[] args) throws Exception {
		TreeColapse TC = new TreeColapse();
		TC.main(null);
		
		
		taxonomia txx = new taxonomia();
		txx.abreTaxonomia();
		
		
		
		FileWriter logfile = new FileWriter( "C:\\Temp\\grentx\\log.txt", true );
		BufferedWriter bufLogFile = new BufferedWriter(logfile);
		int itemset;
		long startTime = System.currentTimeMillis();
		long estimatedTime = System.currentTimeMillis() - startTime;
    
		String finalFile = "C:\\Temp\\grentx\\regras.txt";
		String dirFile = "C:/Temp/grentx/groupedRules/";
		
		//assim temos o nome de todos os arquivos criados junto a quantidade de linhas de cada uma  mapConsequente
		bufLogFile.write(System.currentTimeMillis() - startTime + "  Inicia Agupa consequente");
		bufLogFile.newLine();
		
		AgrupaConsequente();
		
		bufLogFile.write(System.currentTimeMillis() - startTime + "  Final Agupa consequente");
		bufLogFile.newLine();
		
		//cria arquivo final
		FileWriter fw = new FileWriter(finalFile , true );	
		fw.close();
		
		
		//percorre todos os arquivos Consequentes			 
		for (Map.Entry<String,Integer> entry: mapConsequente.entrySet()){
			int existemRegras = 0;
			itemset = 1;
			bufLogFile.write(System.currentTimeMillis() - startTime +"  " +entry.getKey());
			bufLogFile.newLine();
								
			EliminaRedundancia er = new EliminaRedundancia();
			
			//ajustar criando metodo main em Elimina Redundandia com as 3
			er.setNomeRegra(entry.getKey());
			er.setArquivoFinal(finalFile);
			er.alocaArray(entry.getValue());
			er.setMkdfile(dirFile+entry.getKey()+".txt");			
			
			//para usar todos os itemset
			//pensar em outro tipo de loop
			//se n�o existir itemset com 2 mas existir com 3 vai dar merda
			do{
				existemRegras = er.redundanciaItemset(itemset);
				if(existemRegras > 0)
					er.organizaVetorRedundancia();
				itemset++;
				
			}while(existemRegras > 0 || itemset < 3);
			er.escreveArquivoSemRedundancia();
		}
		//ultima linha log
		bufLogFile.write(System.currentTimeMillis() - startTime + "  ##FIM##");
		bufLogFile.newLine();		
		bufLogFile.close();
		logfile.close();
		//fecha log
	}
}
		
/*
 * *for (Map.Entry<String,Integer> entry: mapConsequente.entrySet()){
				System.out.println(entry.getKey() + "\tfreq=" + entry.getValue());
			}
*/

/*
 * 
 * Artigo explicando API de I/O: 
 * * (http://www.guj.com.br/articles/118)
 * 
 * 
 */ 
		