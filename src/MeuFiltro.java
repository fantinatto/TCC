import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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

	// indice é o resultado e cada nó da lista é uma condicao
	public Map<String, LinkedList<Regras>> mapaDeRegras = new HashMap<String, LinkedList<Regras>>();

	public Map<String, LinkedList<Regras>> getMapaDeRegras() {
		return mapaDeRegras;
	}

	public void setMapaDeRegras(Map<String, LinkedList<Regras>> mapaDeRegras) {
		this.mapaDeRegras = mapaDeRegras;
	}

	public TreeMap<Integer, Regras> sorted_map = new TreeMap<Integer, Regras>();
	public Regras R;

	public void geradorDeNos(ArrayList<String> lista) {
		int i = 0;
		String aux;

		// add "entao" na lista
		while (i < lista.size()) {
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

	public void geraListaDeRegras(ArrayList<String> lista) {
		int i = 0;
		String[] espaco, token, aux;

		while (i < lista.size()) {
			R = new Regras();
			token = lista.get(i).split("(<-)|(\\()|(\\)|(,))");
			aux = token[1].split(" ");
			espaco = lista.get(i).split("(<-)|(\\()|(\\)|(,))");
			espaco[0] = espaco[0].replace(" ", "");
			// espaco[1] = espaco[1].replace(" ", ";");
			espaco[2] = espaco[2].replace(" ", "");
			espaco[3] = espaco[3].replace(" ", "");

			if (espaco[1].length() > 0) {
				R.insereRegras(espaco[0], aux, Double.parseDouble(espaco[2]),
						Double.parseDouble(espaco[3]), aux.length - 1);

				// se já existe add na lista
				if (mapaDeRegras.containsKey(R.Id)) {
					mapaDeRegras.get(R.Id).add(R);
					Collections.sort(mapaDeRegras.get(R.Id),
							new MeuComparador());

				}
				// caso não exista aloca lista de regras e insere no hashmap :.
				// mapaDeRegras
				else {
					RLL = new LinkedList<Regras>();
					RLL.push(R);
					mapaDeRegras.put(R.Id, RLL);
				}

			}
			i++;
		}
		i++;
	}

	public MeuFiltro(ArrayList<String> lista) {
		// geradorDeNos(lista);
		geraListaDeRegras(lista);

	}
}
