import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;


class ValueComparator  implements Comparator<Regras> {  
	
		Map<String,LinkedList<Regras>> base;
	    public ValueComparator(Map<String,LinkedList<Regras>>  base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(String id, int a, int b) {
	        if (base.get(id).get(a).itens >= base.get(id).get(b).itens) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }

		@Override
		public int compare(Regras arg0, Regras arg1) {
			// TODO Auto-generated method stub
			return 0;
		}
} 
