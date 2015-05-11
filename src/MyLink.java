import java.util.ArrayList;


public class MyLink extends Arvore{
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
	    
	
	
	public void geradorArvores(ArrayList<String> lista){
		MeuFiltro mf = new MeuFiltro(lista);
		mf.geradorDeNos(lista);
		criaArvore(mf.filtro);
		
	}

}
