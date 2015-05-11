import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;



public class MeuFiltro {
	public ArrayList<String> filtro = new ArrayList<String>();
	//indice é o resultado e cada nó da lista é uma condicao
	public Map<Integer,Regras> mapaDeRegras = new HashMap<Integer,Regras>();
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
			
			mapaDeRegras.put(i, R);
			
										
			i++;			
		}
								
		i++;
	}

	
	public MeuFiltro(ArrayList<String> lista){		
		geradorDeNos(lista);
		geraListaDeRegras(lista);
	
	}
}
