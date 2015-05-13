import java.util.ArrayList;
import java.util.LinkedList;

import edu.uci.ics.jung.graph.SparseMultigraph;

//Graph<V, E> where V is the type of the vertices
// and E is the type of the edges
public class Grafo {
	public  SparseMultigraph<String, MyLink> g = new  SparseMultigraph<String, MyLink>();
	static int edgeCount = 0;
	public MeuFiltro mf;
	String[] regras;
	
	public void criaArvore(ArrayList<String> lista){							
		
		//grafo inicial	
		for(int i = 0; i < lista.size(); i++){
			g.addVertex(lista.get(i));		
			
		}
	
		//cria Edges (grafo inicial)
		for(int i = 0; i < g.getVertexCount(); i++){
			for(int j = 0; j < g.getVertexCount(); j++){
				if((g.findEdge(lista.get(i), lista.get(j))) == null){					
					g.addEdge(new MyLink(1), lista.get(i), lista.get(j));
				}
			}
		}
		
		System.out.println("The graph g = " + g.toString());		

	}
	
	
	
	//para cada antescendente
	public void geraGrafoGeneralizado(LinkedList<Regras> lista){
		int cont = 0, 
				i = 0,
				numTotalItens = lista.getLast().itens,
				itens = 1;
		
		
		//vetor de string com máximo de itens na lista
		regras = new String[numTotalItens];
		
		//insere na string todas as regras com máximo de 1 item na lista
		
		//A0
		while(lista.get(i).itens <= 1){	
			regras[i] = lista.get(i).regra;
			i++;
		}
			
		//A1
		cont = addVertices(cont, i);		
		//A2
		addArestas(cont, itens);
		
		//B1 (percorre lista)
		while(i < lista.size()){
			//primeira posicao de regras vazia
			cont = 1;
			itens = lista.get(i).itens;
			regras = lista.get(i).regra.split(" ");
			//B1.2
			while(cont <= itens){
				//verifica se existe vertice
				if(!g.containsVertex(" "+regras[cont]+" ")){
					g.addVertex(this.regras[cont]);
					cont++;
				}
				else{
					cont++;	
				}
			}
			cont = 1;
			//B1.2
			
			i++;
		}	
		
		//se não existe ligação conecta com peso qtd de itens
		System.out.println(g);				
	}
	
	@SuppressWarnings("unused")
	public void caminhoExistente(int cont, int itens){
		int i, j, existe = 0;
		MyLink x;
		
		for(i = 1; i < itens; i++){
			for(j = 1; j < itens; j++){	
				x = new MyLink(g.findEdge(this.regras[i], this.regras[j]));
				
				if(x != null){
					existe++;
				}
				else{	
					addArestas(1, itens);				
				}
				
			}
		}
		
		
	}
	
	public int addVertices(int cont, int itens){
		//add todos os vertices 
		for(; cont < itens; cont++){
			//se vertice não existe
			if(!g.containsVertex(this.regras[cont])){
				//add vertice
				g.addVertex(this.regras[cont]);	
			}								
		}
		return cont;
	}
	
	public void addArestas(int cont, int itens){		
		int k, l;
		for(k = 0; k < cont; k++){
			for(l = 0; l < cont; l++){
				//return null if no such edge exists (or either vertex is not present)
				if(g.findEdge(regras[k], regras[l]) == null){
					if(k != l){
						g.addEdge(new MyLink(itens), regras[k], regras[l]);
					}
				}
			}
		}		
	}
	
	public class MyLink {
		double capacity;
	    double weight;
	    int id;
	    	    
	    public MyLink(double weight) {
	        this.id = edgeCount++;
	        this.weight = weight;
	        //this.capacity = capacity;
	    } 

	    public MyLink(MyLink findEdge) {
			// TODO Auto-generated constructor stub
		}

		public String toString() {
	        return "E"+id;
	    }	    
	}
	
	
	public void geradorArvores(ArrayList<String> lista){
		mf = new MeuFiltro(lista);
		////////////////////////
		//corrigir
		//key com espaço no finas
		geraGrafoGeneralizado(mf.mapaDeRegras.get("S3 "));
		
	}
	
}
