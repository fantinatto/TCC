import java.util.ArrayList;
import edu.uci.ics.jung.graph.SparseMultigraph;

//Graph<V, E> where V is the type of the vertices
// and E is the type of the edges
public class Arvore {
	public  SparseMultigraph<String, MyLink> g = new  SparseMultigraph<String, MyLink>();
	static int edgeCount = 0;
	
	public void criaArvore(ArrayList<String> lista){							
		
		//grafo inicial	
		for(int i = 0; i < lista.size(); i++){
			g.addVertex(lista.get(i));		
			
		}
	
		//cria Edges (grafo inicial)
		for(int i = 0; i < g.getVertexCount(); i++){
			for(int j = 0; j < g.getVertexCount(); j++){
				if((g.findEdge(lista.get(i), lista.get(j))) == null){					
					g.addEdge(new MyLink(1, 100), lista.get(i), lista.get(j));
				}
			}
		}
		
		System.out.println("The graph g = " + g.toString());		

	}
	public void addFilhos(){
						
	}	
	
	public class MyLink {
		double capacity;
	    double weight;
	    int id;
	    
	    
	    public MyLink(double weight, double capacity) {
	        this.id = edgeCount++;
	        this.weight = weight;
	        this.capacity = capacity;
	    } 

	    public String toString() {
	        return "E"+id;
	    }
	    
	}
	
	public void geradorArvores(ArrayList<String> lista){
		MeuFiltro mf = new MeuFiltro(lista);
		mf.geradorDeNos(lista);
		criaArvore(mf.filtro);
		
	}
	
}
