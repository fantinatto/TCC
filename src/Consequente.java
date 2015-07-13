import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consequente {
	public Map<String,Integer> mapConsequente;
	
	public void AgrupaConsequente() throws IOException{
		String curLine;		
		int numTotalRegras = 0;
		this.mapConsequente = new HashMap<String,Integer>();	
						
		//FileReader txtFile = new FileReader("C:/Users/Vinicius/Desktop/fantinatto/academico/TCC/Base de Regras/loja4");
		FileReader txtFile = new FileReader("C:/Users/Vinicius/Desktop/fantinatto/academico/TCC/alg/dataminingSE/materias.txt");
		BufferedReader txtBuffer = new BufferedReader(txtFile);
		curLine = txtBuffer.readLine();
	
			while(curLine != null){
				numTotalRegras++;
				
				String minusculo = curLine.toLowerCase(); 	// normaliza em minusculo e sem acento	
				minusculo = Normalizer.normalize(minusculo, Normalizer.Form.NFD);
				minusculo = minusculo.replaceAll("[^\\p{ASCII}]", "");
				//Pattern p = Pattern.compile ("(\\A\\w+)|(\\A\\w+_)");
				Pattern p = Pattern.compile ("(\\A\\w+)|(\\A\\w+_)"); //pega apenas token no começo da string
				Matcher m = p.matcher(minusculo);				
							
				while(m.find()){				
					String token = m.group();				
					final Integer freq = this.mapConsequente.get(token);					
					
					if(freq != null){	
						this.mapConsequente.put(token,freq+1);						
						FileWriter fw = new FileWriter( "C:\\Temp\\grentx\\groupedRules\\" + token + ".txt", true );
						BufferedWriter bw = new BufferedWriter( fw );
																
						bw.write(curLine.split("<- ")[1]); /*
															*escreve o conteúdo no arquivo	
															* *"#"+freq = numera as linhas em cada arquivo 
															* *((\s+)(\w+)){}
															*/
						bw.newLine();	//quebra de linha	
						bw.close(); //fecha os recursos
						fw.close();
											
					}
					else{	
						FileWriter arq = new FileWriter("C:\\Temp\\grentx\\groupedRules\\" + token + ".txt");
						arq.close();					
						this.mapConsequente.put(token,1);
					}
				}			
				curLine = txtBuffer.readLine();
			}			
			txtBuffer.close();			
	}
	
	public void main(String []args) throws Exception {
		int itemset;
		String dirFile = "C:/Temp/grentx/groupedRules/";
		//assim temos o nome de todos os arquivos criados junto a quantidade de linhas de cada uma  mapConsequente
		AgrupaConsequente();
		//percorre todos os arquivos Consequentes			 
		for (Map.Entry<String,Integer> entry: this.mapConsequente.entrySet()){
			itemset = 1;
						
			EliminaRedundancia er = new EliminaRedundancia();
			er.main(entry.getValue(), dirFile+entry.getKey());
			//para usar todos os itemset
			while(er.redundanciaItemset(itemset) < 0){
				
				itemset++;
			}
			
		}	
		

	}
}
		
/*
 * *for (Map.Entry<String,Integer> entry: mapConsequente.entrySet()){
				System.out.println(entry.getKey() + "\tfreq=" + entry.getValue());
			}
*/
		