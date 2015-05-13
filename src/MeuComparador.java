import java.util.Comparator;


public class MeuComparador implements Comparator<Regras> {    

	@Override
	public int compare(Regras o1, Regras o2){  
		Regras c1 = (Regras) o1;  
		Regras c2 = (Regras) o2;  
	  
	    //float resultado = c1.getVolorDeValidacao() - c2.getVolorDeValidacao();
	    
	     return  (c1.getItens() > c2.getItens() ? 1: 
	             (c1.getItens() == c2.getItens() ? 0: -1));
	  }
} 
