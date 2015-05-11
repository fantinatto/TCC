import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.collections15.keyvalue.MultiKey;
import org.apache.commons.collections15.map.MultiKeyMap;



public class MeuFiltro {
	public ArrayList<String> filtro = new ArrayList<String>();
	public LinkedList<Regras> RLL;
	//indice é o resultado e cada nó da lista é uma condicao
	//public Map<Integer,Regras> mapaDeRegras = new HashMap<Integer,Regras>();
	public Map<String,LinkedList<Regras>> mapaDeRegras = new HashMap<String,LinkedList<Regras>>();
	//public Map<MultiKey<Integer, String>,Regras> MKM = new HashMap<MultiKey<Integer, String>,Regras>();
	public TreeMap<Integer,Regras> sorted_map = new TreeMap<Integer,Regras>();
	public Regras R;
	
	public void geradorDeNos(ArrayList<String> lista){
		int i = 0;
		String aux;
		
		//add "entao" na lista
		while(i < lista.size()){			
			aux = lista.get(i);
			StringTokenizer st = new StringTokenizer(aux);			 
		    if (st.hasMoreTokens()) 
		    	filtro.add(st.nextToken());		    	
		    i++;
		}
		
		Set<String> hs = new HashSet<>();
		hs.addAll(filtro);
		filtro.clear();
		filtro.addAll(hs);
		
		i = 0;
	}
	
	
	public void geraListaDeRegras(ArrayList<String> lista){
		int i = 0;	
		String[] aux, token, token2;	
		
	
		while(i < lista.size()){
			R = new Regras();
			token = lista.get(i).split("(<-)|(\\()|(\\))");
			aux = token[2].toString().split(",");
			token2 = token[1].toString().split(" ");
			R.insereRegras(token[0], token[1], Double.parseDouble(aux[0]), Double.parseDouble(aux[1]), token2.length-1);
			
			//se já existe add na lista
			if(mapaDeRegras.containsKey(R.Id)){
				mapaDeRegras.get(R.Id).add(R);
				//RLL = new LinkedList<Regras>();
				//RLL.addAll(mapaDeRegras.get(R.Id));
				//RLL.push(R);
				
			}
			//caso não exista aloca lista de regras e insere no hashmap :. mapaDeRegras
			else{
				RLL = new LinkedList<Regras>();
				RLL.push(R);
				mapaDeRegras.put(R.Id, RLL);
			}
			
			//mapaDeRegras.put(i, R);	
			//sorted_map.put(R.itens, R);										
			i++;			
		}								
		i++;
	}
	
	
	public MeuFiltro(ArrayList<String> lista){		
		geradorDeNos(lista);
		geraListaDeRegras(lista);
		
	
	}
}
