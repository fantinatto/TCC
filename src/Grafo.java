import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import edu.uci.ics.jung.graph.SparseMultigraph;

//Graph<V, E> where V is the type of the vertices
// and E is the type of the edges
public class Grafo {
	public SparseMultigraph<String, MyLink> g;
	public LinkedList<String> regrasFiltradas = new LinkedList<String>();
	static int edgeCount = 0;
	public MeuFiltro mf;
	String[] regras;
	
	public LinkedList<String> getRegrasFiltradas() {
		return regrasFiltradas;
	}

	public void setRegrasFiltradas(LinkedList<String> regrasFiltradas) {
		this.regrasFiltradas = regrasFiltradas;
	}

	// para cada antescendente
	public void geraGrafoGeneralizado(LinkedList<Regras> lista) {
		g = new SparseMultigraph<String, MyLink>();
		int i = 0,
		// itens da ultimo da lista
		numTotalItens = lista.getLast().itens;
		// vetor de string com m�ximo de itens na lista
		regras = new String[numTotalItens];

		
		// A0
		while (i < lista.size()) {
			regras = lista.get(i).regra;
			// adicionou vertice?
			
			if (!addVertices(1, lista.get(i).itens)) {
				lista.remove(i);
			} 
			else {				
				i++;				
			}
		}
		
		i = 0;
		
		while (i < lista.size()) {			
			int z = 0;
			String aux = new String(lista.get(i).Id + " <-");
			System.out.println();
			
			while (z < +lista.get(i).regra.length) {
				aux = aux + " " + lista.get(i).regra[z];
				//System.out.printf(lista.get(i).regra[z]);
				z++;
			}
			aux = aux + " (" + lista.get(i).confianca + " , " + lista.get(i).peso + " )"; 
			regrasFiltradas.add(aux);
			i++;
		}
		
	}

	// verifica se existe um item dentro do grafo
	public boolean existeRegra(int cont, int itens) {
		boolean existe = true;
		int contem = 0;

		for (; cont <= itens; cont++) {
			if (this.g.containsVertex(this.regras[cont])) {
				contem++;
			}
		}		
		if (contem > 0)
			existe = false;
		return existe;
	}

	public boolean addVertices(int cont, int itens) {
		boolean adicionou = false;

		if (existeRegra(cont, itens)) {
			adicionou = true;
			// add todos os vertices
			for (; cont <= itens; cont++) {
				this.g.addVertex(this.regras[cont]);
			}
			
		} 
		else {
			adicionou = false;
		}
		
		return adicionou;
	}



	public void geradorArvores(ArrayList<String> lista) {
		mf = new MeuFiltro(lista);

		// percorre todos os antecedentes
		Set<String> chaves = mf.mapaDeRegras.keySet();
		for (String chave : chaves) {
			if (chave != null)
				System.out.println("\n" + chave + ":");
			geraGrafoGeneralizado(mf.mapaDeRegras.get(chave));
		}
	}

}
