import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EliminaRedundancia {
	public static void main() throws Exception {
		String curLine;
		List<String> regrasT = new ArrayList<String>();
		Map<String,Integer> mapPalavras;
		int index = 0 ;
		
		int numTotalRegras = 0;
		mapPalavras = new HashMap<String,Integer>();
		
		
		/*
		if (args.length != 1) { 
			System.err.println("ERRO: eh preciso especificar o nome do arquivo"); 
			System.err.println("ERRO: classe AgrupaConsequente!"); 
			System.exit(1); 
		}
		*/
				
		FileReader txtFile = new FileReader("C:/Temp/grentx/ aparencia_loja2.txt");
		BufferedReader txtBuffer = new BufferedReader(txtFile);
		curLine = txtBuffer.readLine();			
		regrasT.add(curLine.substring(2, curLine.lastIndexOf('(')));
			while(curLine != null){
				
				numTotalRegras++;
				String minusculo = curLine.toLowerCase();
				//pega apenas token no começo da string
				Pattern p = Pattern.compile("d.+" + regrasT.get(index));
				Matcher m = p.matcher(minusculo);
				System.out.println();
				
				while(m.find()){				
					String token = m.group();				
					final Integer freq = mapPalavras.get(token);					
					
					if(freq != null){	
						mapPalavras.put(token,freq+1);				
						
						
											
					}
					else{	
						
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