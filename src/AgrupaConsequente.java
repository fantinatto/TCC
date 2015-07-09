import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgrupaConsequente {
	
	public static void main(String []args) throws Exception {
	String curLine;
	Map<String,Integer> mapPalavras;
	int numTotalRegras = 0;
	mapPalavras = new HashMap<String,Integer>();
		
	/*
	if (args.length != 1) { 
		System.err.println("ERRO: eh preciso especificar o nome do arquivo"); 
		System.err.println("ERRO: classe AgrupaConsequente!"); 
		System.exit(1); 
	}
	*/
	
	FileReader txtFile = new FileReader("C:/Users/Vinicius/Desktop/fantinatto/academico/TCC/Base de Regras/loja4");
	BufferedReader txtBuffer = new BufferedReader(txtFile);
	curLine = txtBuffer.readLine();
	
		while(curLine != null){
			numTotalRegras++;
			String minusculo = curLine.toLowerCase();
			//pega apenas token no começo da string
			Pattern p = Pattern.compile ("(\\A\\w+)|(\\A\\w+_)");
			Matcher m = p.matcher(minusculo);
			System.out.println();
			
			while(m.find()){				
				String token = m.group();				
				final Integer freq = mapPalavras.get(token);					
				
				if(freq != null){	
					mapPalavras.put(token,freq+1);				 		
					
					FileWriter fw = new FileWriter( "C:\\Temp\\grentx\\groupedRules\\" + token + ".txt", true );
					BufferedWriter bw = new BufferedWriter( fw );
					//escreve o conteúdo no arquivo					
					bw.write("#"+freq + " " +curLine.split("<- ")[1]);					
					//quebra de linha
					bw.newLine();					
					//fecha os recursos
					bw.close();
					fw.close();
										
				}
				else{	
					FileWriter arq = new FileWriter("C:\\Temp\\grentx\\" + token + ".txt");
					arq.close();					
					mapPalavras.put(token,1);
				}
			}
		
			curLine = txtBuffer.readLine();
		}
		
		txtBuffer.close();

		for (Map.Entry<String,Integer> entry: mapPalavras.entrySet()){
			System.out.println(entry.getKey() + "\tfreq=" + entry.getValue());
		}		
		
	}
}
	

