public class Regras {
	public String Id;
	public String[] regra;
	public double peso;
	public double confianca;
	public int itens;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getConfianca() {
		return confianca;
	}

	public void setConfianca(double confianca) {
		this.confianca = confianca;
	}

	public int getItens() {
		return itens;
	}

	public void setItens(int itens) {
		this.itens = itens;
	}
	
	
	public void insereRegras(String I, String[] R, double P, double C, int T) {
		this.Id = I;
		this.regra = R;
		this.peso = P;
		this.confianca = C;
		this.itens = T;
	}
}
